package clientPKSM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	private Socket socket;
	private int port;
	private String host;

	public Client(String host) {
		this.port = 8081;
		this.host = host;
	}

	public Client(String host, int port) {
		this.port = port;
		this.host = host;
	}

	public void sendPKM(String PKMLocation) {
		try {

			this.socket = new Socket(this.host, this.port);
			File PKM = new File(PKMLocation);
			int count;
			byte[] buffer = new byte[8192];

			InputStream in = new FileInputStream(PKM);
			OutputStream out = socket.getOutputStream();
			System.out.println("Connection created! Sending "+PKMLocation+"...");
			while ((count = in.read(buffer)) > 0) {
				out.write(buffer, 0, count);
			}
			System.out.println(PKMLocation+" sent successfully!");
			out.close();
			in.close();
			socket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
