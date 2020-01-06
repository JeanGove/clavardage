//package clavardage;

import java.util.ArrayList;

public class ActiveUserList extends Thread {
	private ArrayList<User> userlist = new ArrayList<User>();

	
	/**
	 * Create a active user list
	 */
	public ActiveUserList() {
		
	}
        
        
        
	/**
	 * Add an user 
	 * @param u User to add
	 */
	public void addUser(User u) {
		this.userlist.add(u);
	}
	/**
	 * Drop an user 
	 * @param u User to drop
	 */
	public boolean removeUser(User u) {
		try {
			this.userlist.remove(u);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	/**
	 * Change user pseudo
	 * @param u User which we must change the pseudo
	 */
	public boolean updateUser(int id, String pseudo) {
           
		return false;
	}
	/**
	 * Test wether the pseudo is already used
	 * @param pseudo Pseudo to test
	 * @return Is true if the pseudo is free
	 */
	public boolean checkPseudoAvailability(String pseudo) {
            
            for(User u :this.userlist){
                if( u.getPseudo().equals(pseudo)){
                    return false;
                }
                
            }
              return true;
        }
		
	/**
	 * Get the User list
	 * @return Returns the User list
	 */
	public ArrayList<User> getUsers(){
		return userlist;
	}

	/**
	 * Get an User according to his id
	 * @param id ID of the user
	 * @return Returns the user if it exists
	 */
	public User getUser(int id){
		for (User user : userlist) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
}
