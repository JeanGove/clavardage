package Controller;

//package clavardage;

import Database.Message;
import Database.User;
import Database.History;
import Interface.ChatPage;
import Network.Connector;
import Database.ActiveUserList;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
	protected User associatedUser;
	protected ActiveUserList userlist = new ActiveUserList();
	protected History history = new History();
	protected boolean allUserLoaded = false;
        public ChatPage chatPage;
        public InetAddress broadcast;
        public int exception = 0;
	/** Create a controller
	 * @param me User associated to the controller
	 */
	public Controller(User me) {
		this.associatedUser = me;
	}

	public Controller(){

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

	/**  
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
        
	
        public int checkUserAvailability(int id,String pseudo){
            return this.userlist.checkUserAvailability(pseudo,id);
        }
        
	/** Load an ActiveUserList
	 * 
	 * @param aul User list to load
	 * @return Is true if the operation succeed
	 */
	public boolean loadActiveUserList(ActiveUserList aul) {
            this.userlist = aul;
            return true;
	}

	public boolean hasUser(){
		return this.associatedUser != null;
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
                System.out.println(this.broadcast.getHostAddress());
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
	
	/** Establish a connection to an user while requested
	 * 
	 * @param dest User to link with a server-client TCP socket couple
	 */
	public Connector connectAsServer(int port) {
		try {
			ServerSocket server = new ServerSocket(port);
			Socket link = server.accept();
			System.out.println("ok");
			Connector con = new Connector(history, server, link, this);
                        return con;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                return null;
	}

	/** Request and Establish a connection to an user
	 * 
	 * @param dest User to link with a server-client TCP socket couple
	 */
        public Connector connectAsClient(User dest) {
            try {
                System.out.println("connectAsClient");
                    DatagramSocket dgramSocket= new DatagramSocket();

                    //criar um datagrama a enviar
                    String messageOut;
                    if(this.associatedUser != null) {
                        messageOut = "createChatServer|"+dest.getId()+"|"+dest.getPseudo()+"|"+this.getId();
                    }else{
                        messageOut = "createChatServer|"+dest.getId()+"|"+dest.getPseudo();
                    }

                    DatagramPacket outPacket= new DatagramPacket(messageOut.getBytes(), messageOut.length(),dest.getAddress(), 1025);
                    dgramSocket.send(outPacket); // para enviar

                    //System.out.println(messageOut);

                    byte[] buffer = new byte[256];
                    // para receber datagramas 
                    DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
                    System.out.println("ok");
                    // Para aceitar os datagramas 
                    dgramSocket.receive(inPacket);
                    // para recuperar a mensagem do buffer
                    //String messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
                    int port = inPacket.getPort();
                    System.out.println("ok");

                    dgramSocket.close();

                    InetAddress address = InetAddress.getLocalHost();
                    // criamos o socket onde vamos nos connectar ao serveur com o sue 
                    Socket link = new Socket(dest.getAddress(),port);

                    // Create a Connector thanks to received datas
                    Connector con = new Connector(history, link);
                    dest.connector = con;

                    return con;
                    //link.close() ;
            } catch(IOException e){
                    e.printStackTrace();
            }
            return null;

        }
	
	/** Send a message
	 * 
	 * @param message Message to send
	 * @param dest Destination user
	 */
	public void sendMessage(Message message, User dest) {
		//Créer le connector s'il est absent
		if(dest.connector == null) {	
                    this.connectAsClient(dest);
		}
		try {
                    dest.connector.out.writeObject(message);
                    this.history.add(message);
                    this.chatPage.setOnDialUser();
		} catch(IOException e){
                    e.printStackTrace();
		}
		
	}

	/** Add an user to the active user list
	 * 
	 * @param id ID of the user
	 * @param pseudo Pseudo of the user
	 */
	public void addUser(int id,String pseudo, InetAddress addr){
            User u = new User(pseudo,id, addr);
            if(this.checkUserAvailability(id, pseudo) == 0)
                this.userlist.addUser(u);

            
            if(this.chatPage != null)
                chatPage.refreshUserlist();
           // System.out.println(u.getPseudo() + " is added");
            //System.out.println(this.chatPage != null);
	}
        
        public void updateUser(int id,String nouveauPseudo){
            this.userlist.updateUser(id, nouveauPseudo);
            if(this.chatPage != null)
                chatPage.refreshUserlist();
        }
	
	/**
	 * Drop an user from the active user list
	 * @param id ID of the user
	 */
	public void removeUser(int id){
		User u = this.userlist.getUser(id);
                if(u != null){
                    if(u.fc != null)
                        u.fc.close();
                    else if(u.connector != null)
                        u.connector.close();
                    this.userlist.removeUser(u);
                }
                if(this.chatPage != null)
                    this.chatPage.refreshUserlist();
	}

	/**
	 * Get the list of all connected users
	 * @return an User list of all a connected users
	 */
	public ArrayList<User> getUserList(){
		return this.userlist.getUsers();
	}

	/**
	 * Get an active user with its ID
	 * @param ID ID of the user
	 * @return User associated to the ID
	 */
	public User getUserByID(int ID){
		User user = this.userlist.getUser(ID);
		return user;
	}

	/**
	 * Get the history associated to the Controller
	 * @return History associated to the Controller
	 */
	public History getHistory(){
		return this.history;
	}

	/** @deprecated 
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
	
	/** @deprecated
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

        public void setChatPage(ChatPage cp){
            this.chatPage = cp;
        }
        
        public void handleServerDisconnection(InetAddress addr){
            if(addr == this.broadcast){
                this.chatPage.handleRemoteServerDisconnection();
            }
        }
        
	/** @deprecated
         *
	 * Start a routine
	 */
	public void start() {
		//Initialize receiver thread
		
		//Initialize send thread if more than 2 CPU
		
	}
        
        public ActiveUserList getActiveUserList() {
            return this.userlist;
        }

	
	
}
