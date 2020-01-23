
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
        public String broadcastOfIPAddress;

	public static void main(String[] args) {
	InetAddress inet;
            try {
                inet = InetAddress.getLocalHost();
                InetAddress inetBrodcast = InetAddress.getByName("10.191.255.255");
                
                byte[] broadcastOfIPAddress = Main.getBroadcastOfIPAddress(inet,16);
                InetAddress inetb2 = InetAddress.getByAddress(broadcastOfIPAddress);
                System.out.println(inetBrodcast.equals(inetb2));

               
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        
        public static byte[] getBroadcastOfIPAddress(InetAddress inet, int prefix){
            
            int length = inet.getAddress().length;
            int BlocksOfFF = (int) Math.floor((prefix+7)/8);

            byte[] wildcard = new byte[length];
            int i=wildcard.length;

            for(i = wildcard.length; i > BlocksOfFF;i--){
                 wildcard[i-1] = (byte) 255;
            }
            double lastingd = Math.pow(2, (8*length-prefix) - 8*(length-BlocksOfFF)) - 1;
            int lasting = (lastingd < 0)? 0 :(int) Math.floor(lastingd);
            
            wildcard[i-1] = (byte) lasting;
            
            byte[] broadcast = new byte[length];
            for(i=0;i<length;i++){
                broadcast[i] = (byte) (inet.getAddress()[i] | wildcard[i]);
            }
            
            return broadcast;
        }
/*
        public boolean isLocal(InetAddress addr){
            //"10.188.0.0"
            
        }*/
}
