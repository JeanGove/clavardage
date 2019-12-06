//package clavardage;

import java.net.*;
import java.io.*;
import java.util.*;

public class Controller {
	private User associatedUser;
	private ActiveUserList userlist = new ActiveUserList();
	
	public Controller(User me) {
		this.associatedUser = me;
	}
	
	public boolean changePseudo(String pseudo) {
		return false;
	}
	
	public boolean login(int id,String pseudo) {
		this.associatedUser = new User(pseudo,id);
		return false;
	}
	
	public boolean loadActiveUserList(ActiveUserList aul) {
		/*this.userList = aul
		return this.userlist != null;*/
		return false;
	}
	
	public boolean disconnect() {
		return false;
	}
	
	public boolean connect() {
		return false;
	}
	
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
	
	public void sendMessage(Message message, User dest) {
		//Créer le connector s'il est absent
		if(dest.connector == null) {	
			this.createConnector(dest);
		}
		
		
	}

	public void addUser(int id,String pseudo){
		User u = new User(pseudo,id);
		this.userlist.addUser(u);
	}
	
	public void receiveMessage(Message message, User dest) {
		
		
	}
	
	public String getPseudo(){
		return this.associatedUser.getPseudo();
	}
	
	public int getId(){
		return this.associatedUser.getId();
	}
	
	public void start() {
		//Initialize receiver thread
		
		//Initialize send thread if more than 2 CPU
		
	}
	
	
}
