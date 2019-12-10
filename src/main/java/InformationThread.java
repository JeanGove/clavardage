//package clavardage;
import java.io.IOException;
import java.lang.Thread;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class InformationThread extends Thread{
	public int port;
	public Controller controller;
	private DatagramSocket ds;
	
	public InformationThread(Controller c,int port) {
		this.port = port;
		this.controller = c;
	}
	
	public void run() {
		try {
			//Create a socket to send or receive UDP messages
			ds = new DatagramSocket(this.port);
			System.out.println("Notify connection");
			try{
				//Creating a broadcast host
				InetAddress host = InetAddress.getLocalHost();
			
				//Ask for system's users ID
				String message = "connect";
				DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),host, 1025+(this.port%2));
				//Send the message
				ds.send(outPacket);
			}catch(IOException e ){
				e.printStackTrace();
			}
			
			System.out.println("listening port on "+this.port);
		    //2. Creating the buffer to get the packet
		    byte[] buffer = new byte[256];
		    //3. Associate with the Datagram packet.
		    DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);

		    try {
			    for(int i=0;i<5;i++){
					ds.receive(inPacket);
					
					int port = inPacket.getPort();
					InetAddress addr = inPacket.getAddress();
					String data = new String(inPacket.getData(),0,inPacket.getLength());
				
					new DataOperation(controller, port, addr, data, ds).start();
					
					System.out.println("received");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

		
class DataOperation extends Thread{
	private	int port;
	private InetAddress addr; //Nécessite peut-être un clone
	private	String data;
	private DatagramSocket ds;
	private Controller c;
	
	public DataOperation (Controller c, int port,InetAddress addr, String data, DatagramSocket ds){
		this.port = port;
		this.addr = addr;
		this.data = data;
		this.ds = ds;
		this.c = c;
	}
	
	public void run(){
		
		//5. Accept address

				String[] argv = data.split("\\|");

				if(argv[0].equals("connect")){
						//Tell my ID to the newly connected machine
						String message = "whoIam|"+this.c.getPseudo()+"|"+this.c.getId();
						try{
							DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),this.addr, this.port);

							this.ds.send(outPacket);
						}catch(IOException e){
							e.printStackTrace();
						}       
				}
				else if(argv[0].equals("startChating")){
					//this.controller.createServer(clientAdress,clientPort);
					
					System.out.println("startChating");
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

					c.addUser(id, pseudo);
					System.out.println("whoIam");
				}
				else{
					System.out.println("unknown Function : "+argv[0]);
				}
				
				//Just to log what is sent
				for(int i=1;i<argv.length;i++){
					System.out.println(argv[i]);
				}
	}
}
