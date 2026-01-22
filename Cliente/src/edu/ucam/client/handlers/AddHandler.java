package edu.ucam.client.handlers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ucam.domain.*;
import edu.ucam.interfaces.IRepository;

public class AddHandler {
	
	// --------------------------------------------------------AÑADIR ASIGNATURA
	public Asignatura addAsig(Scanner S) {
		Asignatura asig = new Asignatura();
		System.out.print(">> ID: ");
		String id = S.nextLine();
		asig.setId(id);
		
		System.out.print(">> Nombre: ");
		String name = S.nextLine();
		asig.setNombre(name);
		
		System.out.print(">> Creditos: ");
		String creditsStr = S.nextLine();
		int credits = -1;
		
		try {
			credits = Integer.parseInt(creditsStr.trim());
		} catch(Exception ex) {
			System.out.println("addAsig(AddHandler): " + ex.getMessage());
			return null;
		}
		if(credits == -1) return null;
		
		asig.setCreditos(credits);
		
		return asig;
	}
	
	// --------------------------------------------------------AÑADIR TITULOS
	public Titulacion addTIT(Scanner S, IRepository<Asignatura> asigRepo, IRepository<Matricula> matRepo) throws RemoteException {  

			Titulacion tit = new Titulacion(); 
			
			// ---------------- ID TITULACION ---------------- 
			System.out.print(">> ID titulación: "); 
			tit.setId(S.nextLine());
			
			// ---------------- NOMBRE TITULACION ---------------- 
			System.out.print(">> Nombre titulación: "); 
			tit.setNombre(S.nextLine());
			
			
			// ---------------- SELECCIONAR ASIGNATURAS ---------------- 
			ArrayList<Asignatura> asigs = asignaturasDisponibles(asigRepo); 
			if (asigs == null) return null;
			
			System.out.println(">> ASIGNATURAS DISPONIBLES <<");
			for (Asignatura as : asigs) {
				System.out.println(as.getId() + " - " + as.getNombre()); 
			} 
			
			String idAsig;
			do {
				System.out.print(">> ID asignatura a añadir (ENTER para terminar): ");
				idAsig = S.nextLine();
				if (!idAsig.isEmpty()) {
					Asignatura as = asigRepo.get(idAsig); 
					
					if (as != null) {
						tit.addAsignatura(as);
						System.out.println("Asignatura añadida."); 
					} else {
						System.out.println("ID no válido."); 
					}
				}
			} while (!idAsig.isEmpty()); 
			
			if (tit.getAsignaturas().isEmpty()) { 
				System.out.println("No se seleccionó ninguna asignatura.");
				return null; 
			}
			
			
			// ---------------- SELECCIONAR MATRICULAS ---------------- 
			ArrayList<Matricula> disponibles = matRepo.list(); 
			if (disponibles.isEmpty()) { 
				System.out.println("No hay matrículas disponibles para titular."); 
				return null; 
			}
			
			System.out.println(">> MATRICULAS DISPONIBLES <<");
			for (Matricula mat : disponibles) {
				System.out.println(" - " + mat.getId()); 
			} 
			
			String idMat;
			do {
				System.out.print(">> ID matricula a añadir (ENTER para terminar): ");
				idMat = S.nextLine();
				if (!idMat.isEmpty()) {
					Asignatura as = asigRepo.get(idMat); 
					
					if (as != null) {
						tit.addAsignatura(as);
						System.out.println("Asignatura añadida."); 
					} else {
						System.out.println("ID no válido."); 
					}
				}
			} while (!idMat.isEmpty()); 
			
			if (tit.getAsignaturas().isEmpty()) { 
				System.out.println("No se seleccionó ninguna asignatura.");
				return null; 
			}
			
			return tit;
	}
		
	
	// --------------------------------------------------------AÑADIR MATRICULAS
	public Matricula addMatricula(Scanner S, IRepository<Asignatura> asigRepo) throws RemoteException {
		Matricula mat = new Matricula();
		
		
		// ---------------- ID MATRÍCULA ---------------- 
		System.out.print(">> ID matrícula: "); 
		mat.setId(S.nextLine());
		
		
		// ---------------- CREAR ALUMNO ---------------- 
		Alumno a = new Alumno();
		System.out.print(">> DNI alumno: ");
		a.setDni(S.nextLine()); 
		System.out.print(">> Nombre alumno: ");
		a.setNombre(S.nextLine());
		System.out.print(">> Apellidos alumno: "); 
		a.setApellidos(S.nextLine()); 
		mat.setAlumno(a);
		
		
		// ---------------- SELECCIONAR ASIGNATURAS ---------------- 
		ArrayList<Asignatura> asigs = asignaturasDisponibles(asigRepo); 
		if (asigs == null) return null;
		
		System.out.println(">> ASIGNATURAS DISPONIBLES <<");
		for (Asignatura as : asigs) {
			System.out.println(as.getId() + " - " + as.getNombre()); 
		} 
		
		String idAsig;
		do {
			System.out.print(">> ID asignatura a añadir (ENTER para terminar): ");
			idAsig = S.nextLine(); 
			if (!idAsig.isEmpty()) {
				Asignatura as = asigRepo.get(idAsig); 
				
				if (as != null) {
					mat.addAsignatura(as);
					System.out.println("Asignatura añadida."); 
				} else { 
					System.out.println("ID no válido."); 
				}
			} 
		} while (!idAsig.isEmpty()); 
		
		if (mat.getAsignaturas().isEmpty()) { 
			System.out.println("No se seleccionó ninguna asignatura.");
			return null; 
		}
		
		return mat; 
	}
	
	
	// -------------------------------------------------------- ASIGNATURAS DISPONIBLES
	private ArrayList<Asignatura> asignaturasDisponibles(IRepository<Asignatura> asigRepo) throws RemoteException {
		ArrayList<Asignatura> asigs = asigRepo.list();
		
		if(asigs != null) {
			if(asigs.isEmpty()) {
				System.out.println("No hay asignaturas disponibles para matricular."); 
			} else {
				return asigs;
			}
		}
		
		return null;
	}
}

