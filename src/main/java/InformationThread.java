import java.io.IOException;
import java.lang.Thread;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import java.util.Iterator;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InformationThread extends Thread{
	/** The port whereon the informations will be received */
	public int port;
	/** The application's controller*/
	public Controller controller;
	/** The UDP socket used to receive all informations from the outside */
	private DatagramSocket ds;
	/** Broadcast address that will be used for this communication */
	public InetAddress broadcast;
	
	/**
     * Create a Information thread object and use it as an UDP server with a port and an associated controller
     * 
     * @param c The Controller associated to the current application
     * @param port port used by the application
     */
	public InformationThread(Controller c,int port) {
		this.port = port;
		this.controller = c;
	}
	
	/** Launch the information thread */
	public void run() {
		try {
			//Create a socket to send or receive UDP messages
			ds = new DatagramSocket(this.port);
			System.out.println("Notify connection on "+this.broadcast.getHostName());
			try{
			
				//Ask for system's users ID
				String message = "connect|"+this.controller.getPseudo()+"|"+this.controller.getId();
				DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),this.broadcast, this.port);
				//Send the message
				ds.send(outPacket);
			}catch(IOException e ){
				e.printStackTrace();
			}
			
			System.out.println("listening on port "+this.port);
		    //2. Creating the buffer to get the packet
		    byte[] buffer = new byte[256];
		    //3. Associate with the Datagram packet.
		    DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);

		    try {
			    for(int i=0;i<5;i++){
				//	System.out.println("waiting for packet");
					ds.receive(inPacket);

					int port = inPacket.getPort();
					InetAddress addr = inPacket.getAddress();
					String data = new String(inPacket.getData(),0,inPacket.getLength());
				
					new DataOperation(controller, port, addr, data, ds).start();
					
					System.out.print(".");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error");
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Get a broadcast address associated to the currently used interface
	 * @param interf The name of the used interface on the system
	 * @return The InetAddress linked to the broadcast address
	 */
	public InetAddress getBroadcastAddress(String interf){
		try{
			NetworkInterface en1 = NetworkInterface.getByName(interf);
			try{
				List<InterfaceAddress> list = en1.getInterfaceAddresses();
				Iterator<InterfaceAddress> it = list.iterator();

				while (it.hasNext()) {
					InterfaceAddress ia = it.next();
					if(ia.getBroadcast() !=  null){
						this.broadcast = ia.getBroadcast();
						return this.broadcast;
					}
				}
			}catch(NullPointerException ne){
				//In case of interface without any address
				System.out.println("ERR: No address defined on "+interf);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}

		
class DataOperation extends Thread{
	private	int port;
	private InetAddress addr; //Nécessite peut-être un clone
	private	String data;
	private DatagramSocket ds;
	private Controller c;
	
	    /**
     * Create a thread which have to treat all data received by UDP and do actions according to the content
     * 
     * @param c The Controller associated to the current application
     * @param port port of the sender
     * @param addr InetAdress object related to the sender
     * @param data Data received from the sender
	 * @param ds UDP socket associated to the current application
     */
	public DataOperation (Controller c, int port,InetAddress addr, String data, DatagramSocket ds){
		this.port = port;
		this.addr = addr;
		this.data = data;
		this.ds = ds;
		this.c = c;
	}

	/* Launch the thread */
	public void run(){
		

		String[] argv = data.split("\\|");

		if(argv[0].equals("connect")){
			//Get the ID and pseudo of the user requesting me for name and ID
			String pseudo = argv[1];
			int id = Integer.parseInt(argv[2]);
			//Check if it's not the own User object we are attempting to add
			if(id != this.c.getId()){
				c.addUser(id, pseudo, this.addr);
			}
			
			//Tell my ID to the newly connected machine
			String message = "whoIam|"+this.c.getPseudo()+"|"+this.c.getId();
			try{
				DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),this.addr, this.port);

				this.ds.send(outPacket);
			}catch(IOException e){
				e.printStackTrace();
			}       
		}
		else if(argv[0].equals("createChatServer")){					
			System.out.println("createChatServer");
		
			try{
				//Prepare the response 
				DatagramSocket datas = new DatagramSocket();
				String message = c.getId()+"|"+c.getPseudo();
				//Send a packet to inform about which port can be used
				DatagramPacket dp = new DatagramPacket(message.getBytes(), message.length(), this.addr, this.port);
				datas.send(dp);
				//Pick up the used port
				int newPort = datas.getPort();
				//Close the UDP socket
				datas.close();
				//Reopen the socket but in TCP
				c.connectAsServer(newPort);
			}catch(Exception e){
				Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
			}

		}
		else if(argv[0].equals("endChating")){
			System.out.println("endChating");
		}
		else if(argv[0].equals("disconnect")){
			//forme = disconnect|ID
			int id = Integer.parseInt(argv[1]);

			c.removeUser(id);
			System.out.println("disconnect");
		}
		else if(argv[0].equals("whoIam")){
			//forme = whoIam|Name|ID
			String pseudo = argv[1];
			int id = Integer.parseInt(argv[2]);

			//Check if it's not the own User object we are attempting to add
			if(id != this.c.getId()){
				c.addUser(id, pseudo, this.addr);
			}
			System.out.println("whoIam");
		}
		else{
			System.out.println("Unknown function : "+argv[0]);
		}
		
		//Just to log what is sent
		for(int i=1;i<argv.length;i++){
			System.out.println(argv[i]);
		}
	}
}
