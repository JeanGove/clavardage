//package clavardage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;

public class Connector extends Thread {
	private ServerSocket server;
	private Socket sock;
	/** Input stream used for communication for the user */
	public ObjectInputStream in;
	/** Output stream used for communication for the user */
	public ObjectOutputStream out;
	private boolean active = true;
	private History history;
        private Controller c;

	/**
	 * Create a server connector object
	 * 
	 * @param sv Server socket
	 * @param sk Client socket
	 */
	public Connector(History h, ServerSocket sv, Socket sk,Controller c) {
		this.server = sv;
		this.sock = sk;
		this.instantiate();
		this.history = h;
                this.c = c;
	}

	/**
	 * Create a client connector object
	 * 
	 * @param sv Server socket
	 * @param sk Client socket
	 */
	public Connector(History h, Socket sk) {
		this.server = null;
		this.sock = sk;
		this.instantiate();

	}

	/**
	 * Create the input and output streams and link it to the out and in attributes
	 * 
	 * @return Is true if the stream are well-created
	 */
	private boolean instantiate() {
		try {
			this.out = new ObjectOutputStream(this.sock.getOutputStream());
			this.in = new ObjectInputStream(this.sock.getInputStream());
			// Launch the thread
			this.start();

			return true;
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Close the sockets
	 * 
	 * @return Is true if the operation succeed
	 */
	public boolean close() {
            try {
                    if (this.server != null) {
                            this.server.close();
                    } else {
                            this.sock.close();
                    }
                    // Disable the thread
                    this.active = false;

                    // Close data streams
                    this.in.close();
                    this.out.close();
            } catch (IOException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    return false;
            }
            return true;
	}

	/**
	 * Run the read thread
	 */
	public void run() {

            while (active) {
                try {
                    Message m = (Message) this.in.readObject();
                    //System.out.println(m.getContent());
                    this.history.add(m);
                    this.c.chatPage.setOnDialUser();
                    //yield();
                } catch (ClassNotFoundException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
	}
}
/*
class MessageReader extends Thread{
	private History history;
	private ObjectOutputStream out;

	public MessageReader(History h,ObjectOutputStream out){
		this.history = h;
		this.out = out;
	}
	
	public void run(){
		while(1 == 1){
			synchronized(){
			
			}
		}
	}
}*/