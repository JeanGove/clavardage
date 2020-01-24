/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Controller.Controller;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author corentin
 */
public class ServerInformationThread extends Network.InformationThread{
    public ServerController controller;
    
    public ServerInformationThread(ServerController c, int port){
        super(c,port);
        this.controller = c;
    }
    
    public boolean isLocal(InetAddress inet){
        int prefix = this.networkPrefix;
        int length = inet.getAddress().length;
        int BlocksOfFF = (int) Math.floor((prefix+7)/8);

        // Get the wildcard the netmask opposite
        byte[] wildcard = new byte[length];
        int i=wildcard.length;

        for(i = wildcard.length; i > BlocksOfFF;i--){
             wildcard[i-1] = (byte) 255;
        }
        double lastingd = Math.pow(2, (8*length-prefix) - 8*(length-BlocksOfFF)) - 1;
        int lasting = (lastingd < 0)? 0 :(int) Math.floor(lastingd);

        wildcard[i-1] = (byte) lasting;
        
        // Build a broadcast address
        byte[] broadcast = new byte[length];
        for(i=0;i<length;i++){
            broadcast[i] = (byte) (inet.getAddress()[i] | wildcard[i]);
        }
        
        //Check if the broadcast addresses match
        try {
            InetAddress inetBroadcast = InetAddress.getByAddress(broadcast);
            return inetBroadcast.equals(this.broadcast);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerInformationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**@overwrite*/
    public void operateDatas(DatagramPacket inPacket,DatagramSocket ds){
        int port = inPacket.getPort();
        InetAddress addr = inPacket.getAddress();
        String data = new String(inPacket.getData(),0,inPacket.getLength());
        //System.out.println("serverMode");
        new ServerDataOperation(this.controller, port, addr, data, ds,this.isLocal(addr)).start();
    }
}
