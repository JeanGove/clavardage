import java.net.ServerSocket;

public class ServerTCP {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(1024);
			try {
				
		ss.accept();
			} catch (Exception e) {
				//TODO: handle exception
			}
			ss.close();
		} catch (Exception e) {
			//TODO: handle exception
		}
		



	}

}
