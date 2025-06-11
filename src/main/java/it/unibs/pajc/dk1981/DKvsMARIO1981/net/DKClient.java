package it.unibs.pajc.dk1981.DKvsMARIO1981.net;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DKClient {
	private static final Logger log = LoggerFactory.getLogger(DKClient.class);
	
	public static void main(String[] args) {
		try {
			//TODO: controllare che esista
			InetAddress serverAddress = InetAddress.getByName(args[0]);

			try (Socket client = new Socket(serverAddress, DKServer.PORT)) {
				
				
			}
		} catch (IOException e) {
			log.error("{}: {}", args[0], e.getMessage(), e);
		}
	}
}
