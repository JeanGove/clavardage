//package clavardage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector {
	private ServerSocket server;
	private Socket sock;
	public ObjectInputStream in;
	public ObjectOutputStream out;
	
	public Connector(ServerSocket sv, Socket sk) {
		this.server =sv;
		this.sock = sk;
	}

}
