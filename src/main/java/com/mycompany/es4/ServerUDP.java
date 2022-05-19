/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.es4;

/**
 *
 * @author david
 */
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerUDP{

	public static void main(String[] args) {
            
		int port=2000;
                //data buffer
byte[]          buffer = new byte[256];
		//UDP socket
		DatagramSocket dSocket;
		//UDP request socket
		DatagramPacket outPacket;
		//UDP response socket
		DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
		String messageIn, messageOut;
		Date d;
		
		try {
			dSocket = new DatagramSocket(port);
			System.out.println("Apertura porta in corso!");   
                        
			while(true){
                            
				System.out.println("Server in ascolto sulla porta " + port + "!\n");
				dSocket.receive(inPacket);
				
				//recover the client socket 
				InetAddress clientAddress = inPacket.getAddress();
				int clientPort = inPacket.getPort();
				
				//print the message
				messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
				System.out.println("SONO IL CLIENT " + clientAddress + 
						":" + clientPort + "> " + messageIn);
				
                                //create the message containing the current date
				d = new Date();
				messageOut = d.toString();
				
				//create the packet to send to the client containing our message
				outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
				//sends the message
				dSocket.send(outPacket);
				System.out.println("Risposta inviata!");
			}
		} catch (SocketException ex) {
                    Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
                    Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}