package edu.ucam.client.handlers;

import java.rmi.RemoteException;
import java.util.Scanner;

import edu.ucam.domain.Asignatura;
import edu.ucam.interfaces.IRepository;

public class GetHandler {
	public Asignatura getAsig(IRepository<Asignatura> asigRepo, Scanner S) throws RemoteException{
		String id = null;
		
		do {
			System.out.print(">> ID: ");
			id = S.nextLine();
		} while(id.isEmpty());
		
		return asigRepo.get(id);
	}
	
}
