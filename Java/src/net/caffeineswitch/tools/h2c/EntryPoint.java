package net.caffeineswitch.tools.h2c;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class EntryPoint {
	public static void main(String args[]) throws IOException{
		int port =  (args.length >= 1)?Integer.parseInt(args[0]):80;
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

		server.createContext("/", new COMInputerHttpHandler());
		server.start();
	}
}
