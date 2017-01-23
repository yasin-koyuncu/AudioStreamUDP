/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.audiostream;

import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author yasin93
 * First version code
 */
public class Caller {
    public static final int SIP_PORT = 5060;
	
	public static void main(String[] args) throws Exception {
		/*
		if(args.length != 1) {
			System.out.println("Usage: java Caller <callee's ip address>");
			System.exit(0);
		}
		*/
		String arg = "127.0.0.1"; // adressen till han som ringer!

		
		Scanner scan = new Scanner(System.in);
		AudioStreamUDP stream = null;
		String reply;
		try {
			// The AudioStream object will create a socket,
			// bound to a random port.
			stream = new AudioStreamUDP();
			int localPort = stream.getLocalPort();
			System.out.println("Bound to local port = " + localPort);
			
			// Set the address and port for the callee.
			System.out.println("What's the remote port number?");
			reply = scan.nextLine().trim();
			int remotePort = Integer.parseInt(reply);
			InetAddress address = InetAddress.getByName(arg);
			System.out.println(address + ", " + remotePort);
			stream.connectTo(address, remotePort);
			
			System.out.println("Press ENTER to start streaming");
			reply = scan.nextLine();
			stream.startStreaming();
			
			System.out.println("Press ENTER to stop streaming");
			reply = scan.nextLine();
			stream.stopStreaming();
		}
		finally {
			if(stream != null) stream.close();
		}
}
}
