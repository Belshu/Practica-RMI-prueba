package edu.ucam.client.handlers;

import java.rmi.RemoteException;
import java.util.Scanner;

import edu.ucam.domain.Asignatura;
import edu.ucam.domain.Matricula;
import edu.ucam.domain.Titulacion;
import edu.ucam.interfaces.IRepository;

public class GetHandler {
	public Asignatura getAsig(IRepository<Asignatura> asigRepo, Scanner S) throws RemoteException{
		String id;
		
		do {
			System.out.print(">> ID: ");
			id = S.nextLine();
		} while(id.isEmpty());
		
		return asigRepo.get(id);
	}
	
	public Titulacion getTIT(IRepository<Titulacion> titRepo, Scanner S) throws RemoteException { 
		String id;
		
		do { 
			System.out.print(">> ID: ");
			id = S.nextLine(); 
			} while(id.isEmpty()); 
		
		return titRepo.get(id); 
	}
	
	public Matricula getMatricula(IRepository<Matricula> matriRepo, Scanner S) throws RemoteException { 
		String id;
		
		do { 
			System.out.print(">> ID: ");
			id = S.nextLine(); 
			} while(id.isEmpty());
		
		return matriRepo.get(id); 
	}
}
