import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//package clavardage;

public class Application {
	/**
	 * Main object
	 * @param args Arguments to lauch
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = args[0];
		int id = Integer.parseInt(args[1]);
                
                String interf;
                if(args.length < 2){
                    interf = "wlp2s0";
                }else{
                    interf = args[2];
                }
                
		//Temporaire
		User me = new User(name,id);
		// Stable
		Controller c = new Controller(me);
		
		//Port fixe pour la réception d'infos
		int port = 1025;
		InformationThread ith = new InformationThread(c,port); //Port à rendre dynamique
		//ith.getBroadcastAddress("eth0");
		ith.getBroadcastAddress(interf);
		ith.start();
		//Command line interface
		CLI cli = new CLI(c);
		cli.run();
	}

}

class CLI{
	private Controller controller;
	/** Command line interface */
	public CLI(Controller c){
		this.controller = c;
	}
	public void run(){
		String entered = "";
		while(!entered.equals("quit")){
			//Read the entered text
			byte[] b = new byte[256];
			try{
				System.in.read(b);
				entered = new String(b,0,new String(b).indexOf(0) -1);
				//	System.out.println(entered+" entered");
			}catch(IOException e){
				e.printStackTrace();
			}

			//Operate with it
			String command = entered.split(" ")[0];
                        System.out.println(command);
			System.out.print("[CLI] ");
			if (command.equals("list")) {
				System.out.println("List all users");
				ArrayList<User> users = controller.getUserList();
				Iterator ui = users.iterator();
				
				int id = 0;
				User u;
				while (ui.hasNext()) {
					u = (User) ui.next();
					System.out.println(id+":"+u.getPseudo()+" ("+u.getId()+")");
					id++;
				}

				if(id == 0){
					System.out.println("*******\nThe user list is empty\n*****");
				}

			} else if (command.equals("chat")) {
				int dest = Integer.parseInt(entered.split(" ")[1]);
				User u = this.controller.getUserList().get(dest);
                                System.out.println(dest);
				System.out.println("Start chat session with "+u.getPseudo());

				this.controller.connectAsClient(u);
                                System.out.println("connected");
                                
                        } else if(command.equals("send")){
                                //send 0 message
                                System.out.println(command);
                                
                                System.out.println("1");
                                int iddest = Integer.parseInt(entered.split(" ")[1]);
                                int idsource= controller.getId();
                                System.out.println(iddest + idsource + "2");
                                String content = entered.split("send [0-9]*")[1];
                                System.out.println(iddest + idsource + content+ "3");
                                User u = this.controller.getUserList().get(iddest);
                                Date date = new Date();
                                  System.out.println(iddest + idsource + content+ "4");
                                Message mess = new Message(date,idsource,iddest,content);
                                controller.sendMessage(mess,u );
                                System.out.println(iddest + idsource + content);



                        }else if(command.equals("quit")){
                        System.out.println("Exiting program");
			} else {
				System.out.println("ERROR: Not recognized command");
			}
		}

	}
}