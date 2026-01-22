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
		LocateRegistry.createRegistry(ServerConfig.PORT); // 5000
		
		IContract contract = new ContractImp();
		IRepository <Asignatura> asigRepo = new AsignaturasRepository();
		IRepository<Matricula> matRepo = new MatriculasRepository(); 
		IRepository<Titulacion> titRepo = new TitulacionesRepository();
		
		Naming.rebind(ServerConfig.nameBindContract, contract);
		Naming.rebind(ServerConfig.nameBindAsigRepository, asigRepo);
		Naming.rebind(ServerConfig.nameBindMatRepository, matRepo); 
		Naming.rebind(ServerConfig.nameBindTitRepository, titRepo);
		
		System.out.print("Servidor abierto [ENTER para cerrar]: ");
		
		Scanner S = new Scanner(System.in);
		S.nextLine();
		
		System.exit(0);
	}
}
