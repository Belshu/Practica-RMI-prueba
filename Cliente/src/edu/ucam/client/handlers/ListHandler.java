package edu.ucam.client.handlers;

import java.rmi.RemoteException;
import java.util.ArrayList;

import edu.ucam.domain.Asignatura;
import edu.ucam.interfaces.IRepository;

public class ListHandler {
	public void listAsig(IRepository<Asignatura> asigRepo) throws RemoteException{
		ArrayList <Asignatura> asignaturas = asigRepo.list();
		
		if(asignaturas == null) {
			System.out.println("ArrayList de asignaturas nulo!");
			return;
		}
		
		if(asignaturas.isEmpty()) {
			System.out.println("No hay asignaturas en la lista.");
			return;
		}
		
		System.out.println(">> ASIGNATURAS <<");
		for(Asignatura asig : asignaturas) {
			System.out.println("ID -> " + asig.getId() + " | Nombre -> " + asig.getNombre()
			+ "| Creditos -> " + asig.getCreditos());
		}
	}
}
