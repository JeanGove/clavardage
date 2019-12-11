//package clavardage;
import java.net.*;


public class User {
	
	private int id;
	private String pseudo;
	public InetAddress adress ;
	public int infoPort = 1025;
	public Connector connector = null;
	
	
	//construteur 
	public User (String pseudo, int id) {
		this.pseudo = pseudo;
		this.id =id;
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
	
	/**
	 * Set the pseudo
	 * @return Returns true if succeeded
	 */
	public boolean setPseudo(String pseudo) {
		this.pseudo = pseudo ;
		return true;
	}
}
