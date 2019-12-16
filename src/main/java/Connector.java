//package clavardage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector {
	private ServerSocket server;
	private Socket sock;
	/** Input stream used for communication for the user */
	public ObjectInputStream in;
	/** Output stream used for communication for the user */
	public ObjectOutputStream out; 
	
	/**
	 * Create a server connector object
	 * @param sv Server socket
	 * @param sk Client socket
	 */
	public Connector(ServerSocket sv, Socket sk) {
		this.server =sv;
		this.sock = sk;
		this.instantiate();
	}

		/**
	 * Create a client connector object
	 * @param sv Server socket
	 * @param sk Client socket
	 */
	public Connector( Socket sk) {
		this.server = null;
		this.sock = sk;
		this.instantiate();
	}

	/**
	 * Create the input and output streams and link it to the out and in attributes
	 * @return Is true if the stream are well-created
	 */
	private boolean instantiate(){
		try {
			this.out = new ObjectOutputStream(this.sock.getOutputStream());
			this.in = new ObjectInputStream(this.sock.getInputStream());
			return true;
		} catch (IOException e) {
			//TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Close the sockets
	 * @return Is true if the operation succeed
	 */
	public boolean close(){
		try {
			if(this.server != null){
				this.server.close();
			}else{
				this.sock.close();
			}
		} catch (IOException e) {
			//TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
