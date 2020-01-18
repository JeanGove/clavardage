package Network;

import Controller.Controller;
import java.io.IOException;
import java.lang.Thread;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
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
				String message = "connecting";
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
                    ds.setSoTimeout(5000);
		    try {
                        //for(int i=0;i<5;i++){
                        while( 1 == 1 ){
                        //	System.out.println("waiting for packet");
                            try{
                                ds.receive(inPacket);
                                int port = inPacket.getPort();
                                InetAddress addr = inPacket.getAddress();
                                String data = new String(inPacket.getData(),0,inPacket.getLength());

                                new DataOperation(controller, port, addr, data, ds).start();
                                //inPacket.setData(new byte[256]);

                                System.out.print(".");
                            }catch(SocketTimeoutException ste){

                            }
                        }
                    } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            System.out.println("Error");
                    }

		} catch (BindException e2){
                    String message = "Une instance de ClavardApp est déjà ouverte sur votre machine";
                    System.out.println("E: "+message);
                    this.controller.exception = 1;
                }catch (SocketException se){
                    se.printStackTrace();
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
                                            this.controller.broadcast = this.broadcast;
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
