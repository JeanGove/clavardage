import java.io.*;
import java.net.*;

public class Main {

	public static void main(String[] args){
        try {
            //1. Creating a ServerSocket instance
            DatagramSocket ds = new DatagramSocket();
            //2.
            String message = "connect|1026";
            String message2 = "start|1027";
            String message3 = "unstart|1028";
            try{
                InetAddress host = InetAddress.getByName(args[0]);
                DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),host, 1025);
                                DatagramPacket outPacket2 = new DatagramPacket(message2.getBytes(), message2.length(),host, 1025);
                                                DatagramPacket outPacket3 = new DatagramPacket(message3.getBytes(), message3.length(),host, 1025);
            
                //3. Send the pkt
                ds.send(outPacket);
                ds.send(outPacket2);
                ds.send(outPacket3);
                int port = outPacket.getPort();
                //4
                ds.close();
                
            } catch(Exception e){
                e.printStackTrace();
            }
 
            ds.close();
        } catch(SocketException e){
            e.printStackTrace();
        }
    }

}
