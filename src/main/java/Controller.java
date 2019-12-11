//package clavardage;

import java.net.*;
import java.io.*;
import java.util.*;

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
	
	/** Create a Connector an link it to an user
	 * 
	 * @param dest User to link with a server-client TCP socket couple
	 */
	public void createConnector(User dest) {
		/*ServerSocket server;
		try {
			server = new ServerSocket(1025);
		
			Socket link;
			try {
				link = server.accept();
				
				Connector con = new Connector(server,link);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	/** Send a message
	 * 
	 * @param message Message to send
	 * @param dest Destination user
	 */
	public void sendMessage(Message message, User dest) {
		//Créer le connector s'il est absent
		if(dest.connector == null) {	
			this.createConnector(dest);
		}
		
		
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
