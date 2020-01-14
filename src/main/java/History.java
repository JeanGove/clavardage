//package clavardage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class History {
	private ArrayList<Message> messages = new ArrayList<Message>();
	private int limitNumberOfMessage = 50;
	private File sourcefile;
        private User userSource;
        private User userDest;
	
	/** Create a history object */
	public History(User userSource,User userDest) {
		this.userSource= userSource;
                this.userDest= userDest;
                sourcefile = new File("");
	}
	
	/**
     * Load all stored messages
     * 
     * @param destUserId ID of the user
	 * @return All message from the user with the number destUserId
     */
	public ArrayList<Message> load() throws FileNotFoundException, IOException, ClassNotFoundException {
            ArrayList<Message> historique = new ArrayList<Message>();
            
            ObjectInputStream mess =  new ObjectInputStream(new FileInputStream(this.sourcefile));
            historique =( ArrayList<Message>) mess.readObject();
            return historique;
	}
	
	/**Load all stored messages since a specific message id
     * 
     * @param destUserId ID of the user
	 * @param firstMessageloadedID ID of the first message to load
	 * @return All message from the user with the number destUserId and since the specified message id
     */	
	public ArrayList<Message> load(int destUserId, int firstMessageloadedID) throws FileNotFoundException, IOException, ClassNotFoundException{
            ArrayList<Message> historique = new ArrayList<Message>();
            ArrayList<Message> historiqueLimite= new ArrayList<Message>();
            
            ObjectInputStream mess =  new ObjectInputStream(new FileInputStream(this.sourcefile));
            historique =( ArrayList<Message>) mess.readObject();
            Iterator it = historique.iterator();
            
            while(it.hasNext()){
                int i =0;
                Message message = (Message)it.next();
                if(i >= firstMessageloadedID){
                    historiqueLimite.add(message);
                }
                
            }
            return historique;
            
	}
	
	
	/**Add a message to the list
	 * 
	 * @param message The message to add
	*/
	public void add(Message message) {
            
            if(limitNumberOfMessage < 50){
		this.messages.add(message);
            }
	}
        
        /**Send the history content on the database server
	 * @return Is true if the operation succeed
	*/
        
        public boolean push(ArrayList<Message>  message) throws IOException{
            
           ObjectOutputStream mess =  new ObjectOutputStream(new FileOutputStream(sourcefile)) ;
           mess.writeObject(message);
           
           return true;
            
        }
}
