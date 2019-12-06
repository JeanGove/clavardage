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
	//recuperer le pseudo
	public String getPseudo() {
		return pseudo;
	}
	//recuperer le id
	public int getId() {
		return id;
	}
	
	//
	public boolean setPseudo(String pseudo) {
		this.pseudo = pseudo ;
		return true;
	}
}
