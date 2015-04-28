package server.events.tests;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class SimulationServerMain {
	public static void main(String[] args) {

		HttpServer server;
		try {
			server = HttpServer.create(new InetSocketAddress(9998), 0);
			server.createContext("/requests", new SimulationServer());
			server.setExecutor(null); // creates a default executor
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
