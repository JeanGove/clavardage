//package clavardage;

import java.util.ArrayList;

public class ActiveUserList {
	private ArrayList<User> userlist = new ArrayList<User>();
	
	public ActiveUserList() {
		
	}
	
	public void addUser(User u) {
		this.userlist.add(u);
	}
	public boolean removeUser(User u) {
		try {
			this.userlist.remove(u);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public boolean updateUser(int id, String pseudo) {
		return false;
	}
	public boolean checkPseudoAvailability(String pseudo) {
		return false;
	}
	public ArrayList<User> getUsers(){
		return userlist;
	}
	public User getUser(int id){
		for (User user : userlist) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
}
