/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Controller.Controller;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author corentin
 */
public class ServerDataOperation extends Network.DataOperation{
    private boolean isLocal;
    protected ServerController c;
    
    
    public ServerDataOperation(ServerController controller,int port,InetAddress addr,String data,DatagramSocket ds,boolean isLocal){
        super(controller,port,addr,data,ds);
        this.isLocal = isLocal;
        this.c = controller;
        System.out.println("serverMode");
    }

    
    @Override
    protected void onCreateChatServer(String[] argv) {
        int remoteID = Integer.parseInt(argv[1]);
        String remotePseudo = argv[2];
        //int id
        
        try{
            //Prepare the response
            DatagramSocket datas = new DatagramSocket();
            String message = remoteID+"|"+remotePseudo;
            //Send a packet to inform about which port can be used
            DatagramPacket dp = new DatagramPacket(message.getBytes(), message.length(), this.addr, this.port);
            datas.send(dp);
            //Pick up the used port
            int newPort = datas.getLocalPort();
            //Close the UDP socket
            datas.close();
            //Reopen the socket but in TCP
            c.linkUsers(newPort,remoteID);
        }catch(Exception e){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }



    @Override
    public void onConnected(String[] argv) {
        //Get the ID and pseudo of the user requesting me for name and ID
        String pseudo = argv[1];
        int id = Integer.parseInt(argv[2]);
        //Check if it's not the own User object we are attempting to add
        this.c.addUser(id, pseudo, this.addr);

    }
 
    @Override
    public void onDisconnect(String[] argv) {
        //Get the ID and pseudo of the user requesting me for name and ID
        int id = Integer.parseInt(argv[1]);
        //Check if it's not the own User object we are attempting to add
        if(isLocal){
            this.c.removeUser(id);
        }else{
            this.c.removeRemoteUser(id);
        }

    }
    
    public void onPseudoChange(String[] argv) {
        //Get the ID and pseudo of the user requesting me for name and ID
        String pseudo = argv[1];
        int id = Integer.parseInt(argv[2]);
        //Check if it's not the own User object we are attempting to add
        if(isLocal){
            this.c.updateUser(id, pseudo);
        }else{
            this.c.updateRemoteUser(id, pseudo);
        }
    }
        
    public void onRemoteRequest(String[] argv){
        try {
            //Get the ID and pseudo of the user requesting me for name and ID
            String pseudo = argv[1];
            int id = Integer.parseInt(argv[2]);
            //Prepare the response
            try ( DatagramSocket datas = new DatagramSocket()) {
                String message = "remoteReply|"+this.c.checkUserAvailability(id, pseudo);
                //Send a packet to inform about which port can be used
                DatagramPacket dp = new DatagramPacket(message.getBytes(), message.length(), this.addr, this.port);
                datas.send(dp);
            }
        } catch (SocketException ex) {
            Logger.getLogger(ServerDataOperation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerDataOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onRemoteEstablished(String[] argv){
        try {
            System.out.println("remoteEstablished");
            //Get the ID and pseudo of the user requesting me for name and ID
            String pseudo = argv[1];
            int id = Integer.parseInt(argv[2]);
            
            //Prepare the response
            DatagramSocket datas = new DatagramSocket();
            String message = "remoteEstablishedReply|"+this.c.checkUserAvailability(id, pseudo);
            //Send a packet to inform about which port can be used
            DatagramPacket dp = new DatagramPacket(message.getBytes(), message.length(), this.addr, this.port);
            datas.send(dp);
            
            if(this.c.checkUserAvailability(id, pseudo) == 0){
                //Add the user
                this.c.addRemoteUser(id, pseudo, this.addr);

                //Pick up the used port
                int newPort = datas.getLocalPort();
                //Close the UDP socket
                datas.close();

                //Create a TCP port to send the activeUserList
                ServerSocket serv = new ServerSocket(newPort);
                Socket link = serv.accept();

                ObjectOutputStream oos = new ObjectOutputStream(link.getOutputStream());
                oos.writeObject(this.c.getActiveUserList());
            }else{
                //Close the UDP socket
                datas.close();
            }
        } catch (SocketException ex) {
            Logger.getLogger(ServerDataOperation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerDataOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onChallengeServer(String[] argv){
        
    }

    public void onNewServer(String[] argv){
        
    }

    public void onDefaultServer(String[] argv){
        
    }

    /**Where we add function associated to a module, for example Server module         * 
     */
    public void treatNonGenericSignals(String data){
        String[] argv = data.split("\\|");

        if(argv[0].equals("challengeServer")){					
            System.out.println("challengeServer");
            this.onChallengeServer(argv);
        }
        else if(argv[0].equals("newServer")){
            System.out.println("newServer");
            this.onNewServer(argv);
        }
        else if(argv[0].equals("defaultServer")){
            System.out.println("DefaultServer");
            this.onDefaultServer(argv);
        }
        else if(argv[0].equals("remoteRequest")){
            System.out.println("RemoteRequest");
            this.onRemoteRequest(argv);
        }
        else if(argv[0].equals("remoteEstablished")){
            System.out.println("RemoteEstablished");
            this.onRemoteEstablished(argv);
        }
        else {
            System.out.println("Unknown function : "+argv[0]);
        }
    }
}
