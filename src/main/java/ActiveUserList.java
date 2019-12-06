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
            
            for(User u :this.userlist){
                if( u.getPseudo().equals(pseudo)){
                    return false;
                }
                
            }
              return true;
        }
		

	public ArrayList<User> getUsers(){
		return userlist;
	}
}
