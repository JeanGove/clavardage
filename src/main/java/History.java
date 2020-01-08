//package clavardage;

import java.io.File;
import java.util.ArrayList;

public class History {
	private ArrayList<Message> messages = new ArrayList<Message>();
	private int limitNumberOfMessage = 50;
	private File sourcefile;
	
	/** Create a history object */
	public History() {
		
	}
	
	/**
     * Load all stored messages
     * 
     * @param destUserId ID of the user
	 * @return All message from the user with the number destUserId
     */
	public ArrayList<Message> load(int destUserId) {
		ArrayList<Message> list = new ArrayList<Message>();
		for (Message message : this.messages) {
			if(message.getSourceId() == destUserId || message.getDestId() == destUserId){
				list.add(message);
			}
		}
		return list;
	}
	
	/**Load all stored messages since a specific message id
     * 
     * @param destUserId ID of the user
	 * @param firstMessageloadedID ID of the first message to load
	 * @return All message from the user with the number destUserId and since the specified message id
     */	
	public ArrayList<Message> load(int destUserId, int firstMessageloadedID) {
		return null;
	}
	
	/**Send the history content on the database server
	 * @return Is true if the operation succeed
	*/
	public boolean push() {
		//this.messages.get(0);
		return false;
	}
	
	/**Add a message to the list
	 * 
	 * @param message The message to add
	*/
	public void add(Message message) {
		/*
		//Limit the number of stored messages
		while (this.messages.size() > this.limitNumberOfMessage) {
			this.push();
		}*/
		this.messages.add(message);
	}
}
