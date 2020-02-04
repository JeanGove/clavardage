package Network;

//package clavardage;

import Database.Message;
import Database.History;
import Controller.Controller;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector extends Thread implements Serializable {
	private ServerSocket server;
	private Socket sock;
	/** Input stream used for communication for the user */
	public ObjectInputStream in;
	/** Output stream used for communication for the user */
	public ObjectOutputStream out;
	private boolean active = true;
	private History history;
        private Controller c;
        private Connector linked = null;
        private boolean ready = false;
        private ArrayList<Message> bufferedMessages;
        

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
        
        public void link(Connector c){
            this.linked = c;
        }

	/**
	 * Run the read thread
	 */
	public void run() {

            while (active) {
                try {
                    this.ready = true;
                    Message m = (Message) this.in.readObject();
                    //System.out.println(m.getContent());
                    if(this.linked == null){
                        this.history.add(m);
                    }else{
                        if(this.linked.isReady()){
                            for(Message bm: this.bufferedMessages){
                                this.linked.out.writeObject(bm);
                            }
                            this.linked.out.writeObject(m);
                        }else{
                            this.bufferedMessages.add(m);
                        }
                    }
                    if(this.c.chatPage != null)
                        this.c.chatPage.setOnDialUser();
                    //yield();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (EOFException | SocketException e1){ //While disconnected
                    System.out.println("disconnected !");
                    this.active = false;
                } catch (IOException ex) {
                    Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
	}
        
        public ArrayList<Message> getBufferedMessages(){
            ArrayList<Message> buffer = this.bufferedMessages;
            this.bufferedMessages.clear();
            return buffer;
        }
        
        public boolean isActive(){
            return this.active;
        }

        public boolean isReady() {
            return this.ready;
        }
}