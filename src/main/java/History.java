//package clavardage;

import java.io.File;
import java.util.ArrayList;

public class History {
	private ArrayList<Message> messages = new ArrayList<Message>();
	private int limitNumberOfMessage = 50;
	private File sourcefile;
	
	public History() {
		
	}
	
	public ArrayList<Message> load(int destUserId) {
		return null;
	}
	
	public ArrayList<Message> load(int destUserId, int firstMessageloadedID) {
		return null;
	}
	
	//Send the message on the server
	public boolean push() {
		return false;
	}
	
	public void add(Message message) {
		this.messages.add(message);
	}
}
