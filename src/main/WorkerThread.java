package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.simple.JSONObject;

public class WorkerThread extends Thread {

	private Socket clientSocket;
	private int clientNum;
	
	public WorkerThread(Socket clientSocket, int clientNum) {
		this.clientSocket = clientSocket;
		this.clientNum = clientNum;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		
		try {
		//Get the input/output streams for reading/writing data from/to the socket
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));

		String clientMsg = null;
		while((clientMsg = in.readLine()) != null) {
			Server.respond(clientMsg);
//			JSONObject outJSON = new JSONObject();
//			outJSON.put("type", "message");
//			outJSON.put("content", "Testing respons");
//			System.out.println("Message from client testing " + clientNum + ": " + clientMsg);
//			out.write(outJSON.toString() + "\n");
//			out.flush();
//			System.out.println("Response sent");
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
