/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Controller.Controller;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author corentin
 */
public class DataOperation extends Thread{
	protected int port;
	protected InetAddress addr; //Nécessite peut-être un clone
	protected String data;
	protected DatagramSocket ds;
	protected Controller c;
	
		/**
	 * Create a thread which have to treat all data received by UDP and do actions according to the content
	 * 
	 * @param c The Controller associated to the current application
	 * @param port port of the sender
	 * @param addr InetAdress object related to the sender
	 * @param data Data received from the sender
	 * @param ds UDP socket associated to the current application
	 */
	public DataOperation (Controller c, int port,InetAddress addr, String data, DatagramSocket ds){
		this.port = port;
		this.addr = addr;
		this.data = data;
		this.ds = ds;
		this.c = c;
	}

	/* Launch the thread */
	public void run(){
				
			String[] argv = data.split("\\|");
			System.out.println(argv[0]);

			if(argv[0].equals("connected")){
				this.onConnected(argv);
			}

			if(this.c.hasUser()){ //Check if the Controller is well defined
				if(argv[0].equals("connecting")){
					this.onConnecting(argv);
				}
				else if(argv[0].equals("createChatServer")){					
					System.out.println("createChatServer");
					this.onCreateChatServer(argv);
				}
				else if(argv[0].equals("endChating")){
					System.out.println("endChating");
					this.onChatEnding(argv);
				}
				else if(argv[0].equals("disconnect")){
					this.onDisconnect(argv);
					System.out.println("disconnect");
				}
				else if(argv[0].equals("changePseudo")){
					this.onPseudoChange(argv);
					System.out.println("changePseudo");
				}
				else if(argv[0].equals("whoIam")){ //depreciated
					this.onConnected(argv);
					System.out.println("whoIam");
				}
				else{
					this.treatNonGenericSignals(data);						
				}
			}
			//Just to log what is sent
			for(int i=1;i<argv.length;i++){
					System.out.println(argv[i]);
			}
	}
	//The method are separated to overwrite them if necessary	

	private void onChatEnding(String[] argv) {

	}

	private void onDisconnect(String[] argv) {
		//forme = disconnect|ID
		int id = Integer.parseInt(argv[1]);

		this.c.removeUser(id);
	}
	private void onPseudoChange(String[] argv) {
		//forme = changePseudo|ID|NewPseudo
		//Get informations from UDP message
		int id = Integer.parseInt(argv[1]);
		String newPseudo = argv[2];
		//Update the user
		this.c.updateUser(id, newPseudo);
	}

	private void onCreateChatServer(String[] argv) {
		try{
			//Prepare the response 
			DatagramSocket datas = new DatagramSocket();
			String message = c.getId()+"|"+c.getPseudo();
			//Send a packet to inform about which port can be used
			DatagramPacket dp = new DatagramPacket(message.getBytes(), message.length(), this.addr, this.port);
			datas.send(dp);
			//Pick up the used port
			int newPort = datas.getLocalPort();
			//Close the UDP socket
			datas.close();
			//Reopen the socket but in TCP
			c.connectAsServer(newPort);
		}catch(Exception e){
				Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private void onConnecting(String[] argv) {
		if(this.c.getPseudo() != null){
			//Tell my ID to the newly connected machine
			String message = "connected|"+this.c.getPseudo()+"|"+this.c.getId();
			try{
				DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),this.addr, this.port);

				this.ds.send(outPacket);
			}catch(IOException e){
					e.printStackTrace();
			}
		}
	}

	public void onConnected(String[] argv) {
		//Get the ID and pseudo of the user requesting me for name and ID
		String pseudo = argv[1];
		int id = Integer.parseInt(argv[2]);
		//Check if it's not the own User object we are attempting to add
		if(id != -1 && (!this.c.hasUser() || (this.c.hasUser() && id != this.c.getId() ))){
				this.c.addUser(id, pseudo, this.addr);
		}
	}
        
           /**Where we add function associated to a module, for example Server module         * 
     */
    public void treatNonGenericSignals(String data){
        String[] argv = data.split("\\|");       
        System.out.println("Unknown function : "+argv[0]);
    }
        
}
