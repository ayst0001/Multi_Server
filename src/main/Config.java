// Created by Qinrui Wang, finished by 24/09/2016
// methods to handle config file and extract informations from config entries
package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Config {

	public static int getPort(String serverID, String servers_conf) {
		if (idFound(serverID, servers_conf)) {
			return extractPort(serverID, servers_conf);
		}
		else {
			System.out.println("ServerID not found in the config file, please double check.");
			return -1;
		}
	}

	private static boolean idFound(String serverID, String servers_conf) {
		Boolean found = false;
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(servers_conf));
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			while((line = br.readLine()) != null){
				String[] tokenizedLine = line.split("\\s");
				if (serverID.equals(tokenizedLine[0])){
					System.out.println("serverID Found!!");
					found = true;
					break;
				}
			}
		br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return found;
	}
	
	private static int extractPort(String serverID, String servers_conf) {
		int port = -1;
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(servers_conf));
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			while((line = br.readLine()) != null){
				String[] tokenizedLine = line.split("\\s");
				if (serverID.equals(tokenizedLine[0])){
					System.out.println("port extracted from ID!!");
					port = Integer.valueOf(tokenizedLine[2]);
					break;
				}
			}
		br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return port;
	}

}
