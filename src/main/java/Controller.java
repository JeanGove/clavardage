//package clavardage;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
	private User associatedUser;
	private ActiveUserList userlist = new ActiveUserList();
	
	/** Create a controller
	 * @param me User associated to the controller
	 */
	public Controller(User me) {
		this.associatedUser = me;
	}
	
	/** Change own pseudo
	 * @param pseudo New pseudo to give
	 */
	public boolean changePseudo(String pseudo) {
            boolean rs= userlist.checkPseudoAvailability(pseudo);
            
            if(!rs){
                System.out.println("pseudo utilisé, choisir un autre");
                return false;
                
            }
            return true;
		
	}

	/** Try to log in to the system
	 * @param id Own id
	 * @param pseudo New pseudo to give
	 * @return Is true if the pseudo is available and then the combination od id and pseudo is possible
	 */
	public boolean login(int id,String pseudo) {
		this.associatedUser = new User(pseudo,id);
		return false;
	}
	
	/** Load an ActiveUserList
	 * 
	 * @param aul User list to load
	 * @return Is true if the operation succeed
	 */
	public boolean loadActiveUserList(ActiveUserList aul) {
		/*this.userList = aul
		return this.userlist != null;*/
		return false;
	}
	
	/** Log out and notify the system
	 * 
	 * @return Is true if the operation succeed
	 */
	public boolean disconnect() {
		return false;
	}
	
	/** Log in and ask for system user identifiants
	 * 
	 * @return Is true if the operation succeed
	*/
	public boolean connect() {
		return false;
	}
	
	/** Establish a connection to an user
	 * 
	 * @param dest User to link with a server-client TCP socket couple
	 */
	public void connectAsServer(int port) {

		    try {
                            ServerSocket server = new ServerSocket(port);
                            Socket link = server.accept();
                            Connector con = new Connector(server,link);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

        public void connectAsClient(User dest) {
            
   
                try {
        
                    DatagramSocket dgramSocket= new DatagramSocket(1025);

                    //criar um datagrama a enviar
                    String messageOut = "createChatServer";

                    DatagramPacket outPacket= new DatagramPacket(messageOut.getBytes(), messageOut.length(),dest.getAddress(), 1025);
                    dgramSocket.send(outPacket); // para enviar

                    System.out.println(messageOut);
                    byte[] buffer = new byte[256];
                    // para receber datagramas 
                    DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
                    // Para aceitar os datagramas 
                    dgramSocket.receive(inPacket);
                    // para recuperar a mensagem do buffer
                    String messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
                    int port = inPacket.getPort();
                    System.out.println(messageIn);
                    dgramSocket.close();

                    InetAddress address = InetAddress.getLocalHost();
                   // criamos o socket onde vamos nos connectar ao serveur com o sue 
                   Socket link = new Socket(address,port);

                   link.close() ;
            } catch(IOException e){}
             

        }
	
	/** Send a message
	 * 
	 * @param message Message to send
	 * @param dest Destination user
	 */
	public void sendMessage(Message message, User dest) {
		//Créer le connector s'il est absent
		if(dest.connector == null) {	
			
		}
                try {
                dest.connector.out.writeObject(message);
                
                 } catch(IOException e){}
		
	}

	/** Add an user to the active user list
	 * 
	 * @param id ID of the user
	 * @param pseudo Pseudo of the user
	 */
	public void addUser(int id,String pseudo){
		User u = new User(pseudo,id);
		this.userlist.addUser(u);
	}
	
	/**
	 * Drop an user from the active user list
	 * @param id ID of the user
	 */
	public void removeUser(int id){
		User u = this.userlist.getUser(id);
		this.userlist.removeUser(u);
	}

	/**
	 * Receive a message from someone
	 * @param message Message received
	 * @param dest User whom it came from
	 */
	public void receiveMessage(Message message, User dest) {
		try {
                    
                dest.connector.in.readObject();
                
                 } catch(IOException e){} catch (ClassNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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

	/**
	 * Start a routine
	 */
	public void start() {
		//Initialize receiver thread
		
		//Initialize send thread if more than 2 CPU
		
	}
	
	
}
