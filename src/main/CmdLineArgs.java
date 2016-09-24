// Created by Qinrui Wang, finished by 24/09/2016
// methods to handle command line options for starting servers

package main;

import org.kohsuke.args4j.Option;

public class CmdLineArgs {

	@Option(required = false, name = "-n", aliases = {"--name"}, usage = "serverid")
	private String serverid = "S3";
	
	@Option(required = false, name = "-l", usage = "servers_conf")
	private String servers_conf = "./servers_conf.txt";

	public String getConfig() {
		return servers_conf;
	}

	public String getServerID() {
		return serverid;
	}


	
}