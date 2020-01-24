package Tests;

import Database.ActiveUserList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author corentin
 */
public class RemoteConnection {
    public static void main(String[] args){
        InetAddress addr;
        try {
            addr = InetAddress.getByName(args[0]);
            int port = Integer.parseInt(args[1]);

            //Get the ID and pseudo of the user requesting me for name and ID
            String pseudo = "Phill du passé";
            int id = 17;
            try {
                //Prepare the response
                DatagramSocket datas = new DatagramSocket();
                String message = "remoteRequest|"+pseudo+"|"+id;

                //Send the request to the server
                DatagramPacket dp = new DatagramPacket(message.getBytes(), message.length(), addr, port);
                datas.send(dp);

                //Receive the reply from the server
                byte[] buffer = new byte[256];

                DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
                datas.setSoTimeout(5000);
                try {

                    datas.receive(inPacket);
                    System.out.print(".");
                    String data = new String(inPacket.getData(),0,inPacket.getLength());
                    
                            
                    //System.out.println(str.split("\\|")[1]);
                    int reply = Integer.parseInt(data.split("\\|")[1]);

                    switch (reply) {
                        case 0:
                            //Reply to the server
                            message = "remoteEstablished|"+pseudo+"|"+id;
                            //Send the reply to the server
                            dp = new DatagramPacket(message.getBytes(), message.length(), addr, port);
                            datas.send(dp);
                            //Wait for the last reply
                            datas.receive(inPacket);
                            System.out.println("REPLY: Connexion réussie");
                            //Create a TCP connection to receive the liste of active users
                            int newPort = inPacket.getPort();
                            Socket sock = new Socket(addr,newPort);
                            //Receive the list of active users
                            ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
                            ActiveUserList aul = (ActiveUserList) ois.readObject();
                            aul.initialize(null);
                            break;
                        case 1:
                            System.out.println("REPLY: Pseudo déjà utilisé");
                            break;
                        case 2:
                            System.out.println("REPLY: ID déjà utilisé");
                            break;
                        default:
                            System.out.println("ERR: Réponse incomprise"); 
                            break;
                    }
                }catch(SocketTimeoutException ste){
                    System.out.println("ERR: Réponse non reçue");   
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("ERR: Problème lors de la réception du paquet");    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RemoteConnection.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("ERR: L'objet reçu ne correspond pas à une classe connue"); 
                } 


            } catch (SocketException ex) {
                Logger.getLogger(RemoteConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERR: Problème d'ouverture de socket, port: "+port);
            } catch (IOException ex) {
                Logger.getLogger(RemoteConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERR: paquet non envoyé");
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(RemoteConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERR: nom d'hôte invalide");
        }
    }
}
