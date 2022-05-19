/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.es4;

/**
 *
 * @author david
 */
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientUDP {

	public static void main(String[] args) {
            
		int port = 2000;
                //data buffer
		byte[] buffer = new byte[256];
		//server address
		InetAddress serverAddress;
		//UDP socket
		DatagramSocket dSocket;
		//UDP request socket
		DatagramPacket outPacket;
		//UDP response socket
		DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);	
		
		//request message
		String message="RICHIESTA DATA E ORA";
		//response
		String response;
				
		try {
		
                        //gets localhost address (just cause the server is hosted on the same computer)
			serverAddress = InetAddress.getLocalHost();
			
			System.out.println("Indirizzo del server trovato!");
			dSocket = new DatagramSocket();
			
			//prepare datagram to send
			outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);
			
			//send data
			dSocket.send(outPacket);
			
			//recieve response packet
			dSocket.receive(inPacket);
			
			//get the message from the recieved packet
			response = new String(inPacket.getData(), 0, inPacket.getLength());
			
                        //outputs the result of the comunication
			System.out.println("Connessione stabilita!");
			System.out.println("Data e ora del server: " + response);
			System.out.println("Connessione chiusa!");
			
			//close teh connection to the server
			dSocket.close();

		} catch (UnknownHostException ex) {
                    Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
                    Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
		}			
	}
}