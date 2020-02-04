/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Network.Connector;

/**
 *
 * @author corentin
 */
public class ForwardConnector{
    private Connector c1;
    private Connector c2;

    public ForwardConnector(Connector c1, Connector c2){
        this.c1 = c1;
        this.c2 = c2;
        /*
        c1.out = c2.in;
        c2.out = c1.in;*/
    }
    
    public void close(){
        this.c1.close();
        this.c2.close();
    }
    
    /*public void run(){
        while(c1.isActive() && c2.isActive()){
            
        }
    }*/
}
