package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class WorkerThread extends Thread {

	private Socket clientSocket;
	public WorkerThread(Socket clientSocket, int clientNum) {
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
		
		try {
		//Get the input/output streams for reading/writing data from/to the socket
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
		
		String clientMsg = null;
		while((clientMsg = in.readLine()) != null) {
			if (Server.respond(clientMsg)!= null){
				out.write(Server.respond(clientMsg).toString());
			}
			out.flush();
		}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(clientSocket != null) {
				try {
					clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
	
}
