package edu.ucam.client.handlers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

import edu.ucam.domain.Alumno;
import edu.ucam.domain.Asignatura;
import edu.ucam.domain.Matricula;
import edu.ucam.domain.Titulacion;
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
	public void listMatriculas(IRepository<Matricula> matRepo) throws RemoteException {
		ArrayList<Matricula> matriculas = matRepo.list();
		
		if (matriculas == null) {
			System.out.println("ArrayList de matriculas nulo!");
			return;
		}
		
		if(matriculas.isEmpty()) {
			System.out.println("No hay matrículas en la lista.");
			return;
		}
		
		System.out.println(">> MATRÍCULAS <<"); 
		for (Matricula m : matriculas) { 
			Alumno a = m.getAlumno();
			
			System.out.println("ID -> " + m.getId() + " | Alumno -> " + a.getNombre() + " " + a.getApellidos() + " (" + a.getDni() + ")");
			System.out.println(" Asignaturas (TOTAL: " + m.getAsignaturas().size() + "):");
			
			for (Asignatura as : m.getAsignaturas()) {
				System.out.println(" - " + as.getId() + " | " + as.getNombre()); 
				}
			}
		}
		
		
	public void listTIT(IRepository<Titulacion> titRepo) throws RemoteException { 
		ArrayList<Titulacion> titulaciones = titRepo.list();
		
		if (titulaciones == null) { 
			System.out.println("ArrayList de titulaciones nulo!");
			return;
		} 
		
		if (titulaciones.isEmpty()) { 
			System.out.println("No hay titulaciones en la lista.");
			return;
		} 
		
		System.out.println(">> TITULACIONES <<");
		for (Titulacion t : titulaciones) {
			System.out.println("ID -> " + t.getId() + " | Nombre -> " + t.getNombre() + " | Asignaturas -> " + 
		t.getAsignaturas().size() + " | Matrículas -> " + t.getMatriculas().size()); 
			
			// ----- Asignaturas ----- 
			if (!t.getAsignaturas().isEmpty()) { 
				System.out.println(" Asignaturas:");
				for (Asignatura as : t.getAsignaturas()) { 
					System.out.println(" - " + as.getId() + " | " + as.getNombre());
					}
				}
			
			// ----- Matrículas ----- 
			if (!t.getMatriculas().isEmpty()) {
				System.out.println(" Matrículas:");
				for (Matricula m : t.getMatriculas()) { 
					Alumno a = m.getAlumno();
					System.out.println(" - " + m.getId() + " | Alumno: " + a.getNombre() + " " + a.getApellidos());
					}
				}
			System.out.println();
		}
	}	
}

