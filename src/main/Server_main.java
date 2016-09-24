// Created by Qinrui Wang, finished by 24/09/2016
// main package to start servers
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Server_main {

	public static void main(String[] args) {
		//Object to store arguments for starting servers
		CmdLineArgs server_args = new CmdLineArgs();
		//Parser used to parse server initiating command
		CmdLineParser parser = new CmdLineParser(server_args);
try {
			//Parse the arguments
			parser.parseArgument(args);
			String servers_conf = server_args.getConfig();
			//start servers according to the id and servers_conf file
			Server_Start(server_args.getServerID(), servers_conf);
			//System.out.println("Host: " + server_args.getHost());
			//System.out.println("Port: " + server_args.getPort());
			
			
			
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			//Print the usage to help the user understand the arguments expected
			//by the program
			parser.printUsage(System.err);
		}
	}

	// methods for starting servers
	private static void Server_Start(String serverID, String servers_conf) {
		ServerSocket listeningSocket = null;
		try {
			//Create a server socket listening on the extracted port
			int port = Config.getPort(serverID, servers_conf);
			listeningSocket = new ServerSocket(port);
			
			int i = 0;
			//Listen for incoming connections for ever 
			while (true) {
				System.out.println("Server " + serverID + " is listening on port " + port + " for a connection");
				//Accept an incoming client connection request 
				Socket clientSocket = listeningSocket.accept(); //This method will block until a connection request is received
				i++;
				System.out.println("Client conection number " + i + " accepted:");
				System.out.println("Remote Port: " + clientSocket.getPort());
				System.out.println("Hostname: " + clientSocket.getInetAddress().getHostName());
				System.out.println("Local Port: " + clientSocket.getLocalPort());
				
				//Get the input/output streams for reading/writing data from/to the socket
//				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
//				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
				
				WorkerThread wt = new WorkerThread(clientSocket, i);
				wt.start(); 
				
				//Read the message from the client and reply
				//Notice that no other connection can be accepted and processed until the last line of 
				//code of this loop is executed, incoming connections have to wait until the current
				//one is processed unless...we use threads!
//				String clientMsg = in.readLine();
//				System.out.println("Message from client " + i + ": " + clientMsg);
//				out.write(clientMsg);
//				out.flush();
//				System.out.println("Exact Response sent");
				
				//Close the socket
//				clientSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(listeningSocket != null) {
				try {
					listeningSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
