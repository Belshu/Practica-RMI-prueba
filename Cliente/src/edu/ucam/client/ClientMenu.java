package edu.ucam.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientMenu {
	private IRepository repositorio;
	
	public ClientMenu(String url) throws MalformedURLException, RemoteException, NotBoundException{
		this.repositorio = (IRepository)Naming.lookup(url);
	}
	
	public void initializeMenu(Scanner S) {
		
	}
	
	public void answerManager(Scanner S) {
		
	}
	
	
}
