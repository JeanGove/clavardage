import java.net.Socket;

public class ClientTCP {

	public static void main(String[] args) {
		String host = args[0];
try {
	Socket s = new Socket(host, 1024);

	//s.close();
} catch (Exception e) {
	//TODO: handle exception
}

	}

}
