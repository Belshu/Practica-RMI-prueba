package edu.ucam.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import edu.ucam.client.config.*;

public class MainClient {
	private final static Scanner S = new Scanner(System.in);
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		ClientMenu clientMenu = new ClientMenu(ClientConfig.nameBindContract); // AUTENTICAR
		
		if(clientMenu.autentication(S)) {
			
			// REPOSITORIOS
			clientMenu.initializeMenu(S, 
					ClientConfig.nameBindAsigRepository, ClientConfig.nameBindMatRepository, ClientConfig.nameBindTitRepository);
		} else {
			System.out.println("Autenticacion incorrecta!");
		}
	}
}
