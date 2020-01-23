/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Controller.Controller;
import Database.User;

/**
 *
 * @author corentin
 */
public class Server {
    String name = this.toString();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String interf;
        if(args.length < 1){
            interf = "wlp2s0";
        }else{
            interf = args[0];
        }        
       
        ServerController c = new ServerController();
        //Port fixe pour la réception d'infos
        int port = 1025;
        ServerInformationThread ith = new ServerInformationThread(c,port);
        ith.getBroadcastAddress(interf);
        ith.start();
    }
    
}
