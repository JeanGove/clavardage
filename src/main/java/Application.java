//package clavardage;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Temporaire
		User me = new User("Corentin",16);
		// Stable
		Controller c = new Controller(me);
		
		int port = Integer.parseInt(args[0]);
		InformationThread ith = new InformationThread(c,port); //Port à rendre dynamique
		ith.start();

	}

}
