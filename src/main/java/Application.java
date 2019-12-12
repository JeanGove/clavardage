//package clavardage;

public class Application {
	/**
	 * Main object
	 * @param args Arguments to lauch
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Temporaire
		User me = new User("Corentin",16);
		// Stable
		Controller c = new Controller(me);
		
		//Port fixe pour la réception d'infos
		int port = 1025;
		InformationThread ith = new InformationThread(c,port); //Port à rendre dynamique
		ith.getBroadcastAddress("wlp2s0");
		ith.start();

	}

}
