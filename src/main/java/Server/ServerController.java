/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Controller.Controller;
import Database.ActiveUserList;
import Database.History;
import Database.Message;
import Database.User;
import Interface.ChatPage;
import Network.Connector;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author corentin
 */
public class ServerController extends Controller{

    private ArrayList<User> remoteUsers = new ArrayList<User>();
    
    public ServerController(){
        super();
    }


    /** Change own pseudo
     * @param pseudo New pseudo to give
     */
    public int changePseudo(String pseudo) {
        boolean rs= userlist.checkPseudoAvailability(pseudo);

        if(!rs){
            System.out.println("pseudo utilisé, choisir un autre");
            return 1;

        }

        //Send an UDP message to all active users
        DatagramSocket dgramSocket;
        try {
            dgramSocket = new DatagramSocket();

            //Create the datagram to send
            String messageOut = "changePseudo|"+this.associatedUser.getId()+"|"+pseudo;

            try {
                DatagramPacket outPacket= new DatagramPacket(messageOut.getBytes(), messageOut.length(),this.broadcast, 1025);
                dgramSocket.send(outPacket); 
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                return 2;
            }
        } catch (SocketException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return 2;
        }
        this.associatedUser.setPseudo(pseudo);
        return 0;

    }

    /**@deprecated
     * Try to log in to the system
     * @param id Own id
     * @param pseudo New pseudo to give
     * @return Is true if the pseudo is available and then the combination od id and pseudo is possible
     */
    public int login(int id,String pseudo) {
            int returnedValue = this.userlist.checkUserAvailability(pseudo,id);
            if (returnedValue == 0) {
                    this.associatedUser = new User(pseudo,id);
                    this.connect();
            }
            return returnedValue;
    }

    public boolean hasUser(){
            return true;
    }

    /** Log out and notify the system
     * 
     * @return Is true if the operation succeed
     */
    public boolean disconnect() {
        ArrayList<User> userlist = this.getUserList();
        for(User u: userlist){
            //System.out.println(u.getPseudo());
           if(u.connector != null) 
               u.connector.close();
        }

        //Send an UDP message to all active users
        DatagramSocket dgramSocket;
        try {
            dgramSocket = new DatagramSocket();

            //Create the datagram to send
            String messageOut = "disconnect|"+this.associatedUser.getId();

            try {
                DatagramPacket outPacket= new DatagramPacket(messageOut.getBytes(), messageOut.length(),this.broadcast, 1025);
                dgramSocket.send(outPacket); 
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SocketException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    /** Log in and ask for system user identifiants
     * 
     * @return Is true if the operation succeed
    */
    public boolean connect() {

            return true;
    }


    /** Add an user to the active user list
     * 
     * @param id ID of the user
     * @param pseudo Pseudo of the user
     */
    public void addUser(int id,String pseudo, InetAddress addr){
        super.addUser(id, pseudo, addr);
        System.out.println(pseudo + " added");
        this.notifyRemoteUser("connected",pseudo, id);
    }
    
    public void addRemoteUser(int id,String pseudo, InetAddress addr){
        User u = new User(pseudo,id, addr);
        this.userlist.addUser(u);
        this.remoteUsers.add(u);
        this.notifyRemoteUser("connected",pseudo, id);
        this.notifyLocalUser("connected",pseudo, id);
    }

    public void updateUser(int id,String nouveauPseudo){
        super.updateUser(id, nouveauPseudo);
        this.notifyRemoteUser("pseudoChange",nouveauPseudo, id);
    }
    
    public void updateRemoteUser(int id,String nouveauPseudo){
        super.updateUser(id, nouveauPseudo);
        this.notifyRemoteUser("pseudoChange",nouveauPseudo, id);
        this.notifyLocalUser("pseudoChange",nouveauPseudo, id);
    }

    /**
     * Drop an user from the active user list
     * @param id ID of the user
     */
    public void removeUser(int id){
        super.removeUser(id);
        this.notifyRemoteUser("disconnect","", id);
    }
    /**
     * Drop a remote user from the active user list
     * @param id ID of the user
     */
    public void removeRemoteUser(int id){
        super.removeUser(id);
        this.notifyRemoteUser("disconnect","", id);
        this.notifyLocalUser("disconnect","", id);
    }
    
    public void linkUsers(int port,int ID){
        Connector con1 = this.connectAsServer(port);
        User user = this.getUserByID(ID);
        Connector con2 = this.connectAsClient(user);
        
        //Ensure message forwarding
        con1.link(con2);
        con2.link(con1);
       
    }
    
    public void notifyLocalUser(String content,String pseudo,int id){
          try {
            String message;
            DatagramSocket datas = new DatagramSocket();
            if(content.equals("connected"))
                message = content+"|"+pseudo+"|"+id;
            else
                message = content+"|"+id+"|"+pseudo;

            DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),this.broadcast, 1025);

            try {
                datas.send(outPacket);
            } catch (IOException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void notifyRemoteUser(String content,String pseudo,int id){
        try {
            String message;
            DatagramSocket datas = new DatagramSocket();
            if(content.equals("connected"))
                message = content+"|"+pseudo+"|"+id;
            else
                message = content+"|"+id+"|"+pseudo;
            for(User u: remoteUsers){
                DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),u.getAddress(), 1025);

                try {
                    datas.send(outPacket);
                } catch (IOException ex) {
                    Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get the pseudo of the user associated to the controller
     * @return pseudo of the user associated to the controller
     */
    public String getPseudo(){
            return this.associatedUser.getPseudo();
    }

    /**
     * Get the ID of the user associated to the controller
     * @return ID of the user associated to the controller
     */
    public int getId(){
            return this.associatedUser.getId();
    }

}
