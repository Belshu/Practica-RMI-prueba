package edu.ucam.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import edu.ucam.server.config.*;
import edu.ucam.server.service.*;
import edu.ucam.domain.*;
import edu.ucam.interfaces.*;
import edu.ucam.server.datas.*;

public class MainServer {
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(5000);
		
		IContract contract = new ContractImp();
		IRepository <Asignatura> asigRepo = new AsignaturasRepository();
		
		Naming.rebind(ServerConfig.nameBindContract, contract);
		Naming.rebind(ServerConfig.nameBindAsigRepository, asigRepo);
		
		System.out.print("Servidor abierto...");
		Scanner S = new Scanner(System.in);
		
		S.nextLine();
		
		System.exit(0);
	}
}
