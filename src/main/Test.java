// Created by Qinrui Wang, finished by 24/09/2016
// Methods created to test behavior for debugging
package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	public static void PrintConfig(String servers_conf) {
		//methods to print config file, the following should do the trick
		//Test.PrintConfig(server_args.getConfig());
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(servers_conf));
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			//File open error or something
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
