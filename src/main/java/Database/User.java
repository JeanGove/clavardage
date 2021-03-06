package Database;

//package clavardage;
import Network.Connector;
import Server.ForwardConnector;
import java.io.Serializable;
import java.net.*;



public class User implements Serializable{
	
	private int id = -1;
	private String pseudo;
	public InetAddress address ;
	public int infoPort = 1025;
	public Connector connector = null;
	public ForwardConnector fc = null;
	
	/**
	 * Create an user
	 * @param pseudo 
	 * @param id
	 */
	public User (String pseudo, int id) {
		this.pseudo = pseudo;
		this.id =id;
	}
		
	        /**
	 * Create an user
	 * @param pseudo 
	 * @param id
	 */
	public User (String pseudo, int id, InetAddress addr ) {
		this.pseudo = pseudo;
		this.id =id;
                this.address= addr;
	}
        

	/**
	 * Get the pseudo
	 * @return Returns a pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * Get the ID
	 * @return Returns an ID
	 */
	public int getId() {
		return id;
	}
	
    public InetAddress getAddress() {
		return address;
	}
	
	/**
	 * Set the pseudo
	 * @return Returns true if succeeded
	 */
	public boolean setPseudo(String pseudo) {
		this.pseudo = pseudo ;
		return true;
	}
}
