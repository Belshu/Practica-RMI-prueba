package edu.ucam.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import edu.ucam.client.config.*;

public class MainClient {
	private final static Scanner S = new Scanner(System.in);
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		ClientMenu clientMenu = new ClientMenu(ClientConfig.nameBindContract);
		
		if(clientMenu.autentication(S)) {
			clientMenu.initializeMenu(S, ClientConfig.nameBindAsigRepository);
		} else {
			System.out.println("Autenticacion incorrecta!");
		}
	}
}
