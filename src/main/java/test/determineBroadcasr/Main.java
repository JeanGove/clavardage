import java.net.InetAddress;
import java.net.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Iterator;


public class Main {

	public static void main(String[] args) {
		try{
			InetAddress broadcast;
			NetworkInterface en1 = NetworkInterface.getByName("wlp2s0");
			List<InterfaceAddress> list = en1.getInterfaceAddresses();
			Iterator<InterfaceAddress> it = list.iterator();

			while (it.hasNext()) {
				InterfaceAddress ia = it.next();
				if(ia.getBroadcast() !=  null){
					broadcast = ia.getBroadcast();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		/*
		try {
			
			InetAddress inet = InetAddress.getLocalHost();
			System.out.println(inet);			
			/*System.out.println(inet.getHostAddress());
			System.out.println(inet);
			System.out.println(inet.isAnyLocalAddress());

			InetAddress inet2 = InetAddress.getByName("193.168.1.1");
			System.out.println(inet2.isAnyLocalAddress());

			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
    while (en.hasMoreElements()) {
      NetworkInterface ni = en.nextElement();
      System.out.println(" Display Name = " + ni.getDisplayName());

      List<InterfaceAddress> list = ni.getInterfaceAddresses();
      Iterator<InterfaceAddress> it = list.iterator();

      while (it.hasNext()) {
        InterfaceAddress ia = it.next();
        System.out.println(" Broadcast = " + ia.getBroadcast());
	  }
	  
	  
	  
    }

		} catch (Exception e) {
			//TODO: handle exception
		}
		*/
	}

}
