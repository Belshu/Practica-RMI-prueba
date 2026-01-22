package edu.ucam.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ucam.client.handlers.AddHandler;
import edu.ucam.client.handlers.GetHandler;
import edu.ucam.client.handlers.ListHandler;
import edu.ucam.client.handlers.RemoveHandler;
import edu.ucam.domain.Asignatura;
import edu.ucam.domain.Matricula;
import edu.ucam.domain.Titulacion;
import edu.ucam.interfaces.IContract;
import edu.ucam.interfaces.IRepository;



public class ClientMenu {
	private IContract autenticationServer;
	private IRepository<Asignatura> asigRepo; 
	private IRepository<Titulacion> titRepo;
	private IRepository<Matricula> matRepo;
	
	// -------------------------------------------------------- HANDLERS
	private final AddHandler addHandler = new AddHandler();
	private final GetHandler getHandler = new GetHandler();
	private final RemoveHandler removeHandler = new RemoveHandler();
	private final ListHandler listHandler = new ListHandler();
	
	// -------------------------------------------------------- CONSTRUCTOR
	public ClientMenu(String url) throws MalformedURLException, RemoteException, NotBoundException{
		this.autenticationServer = (IContract)Naming.lookup(url);
	}
	
	// -------------------------------------------------------- AUTENTICACIÓN DE USUARIO
	public boolean autentication(Scanner S) throws RemoteException {
		System.out.print("Nombre: ");
		String user = S.nextLine();
		
		System.out.print("Contraseña: ");
		String pass = S.nextLine();
		
		return autenticationServer.autentication(user, pass);
	}
	
	
	// -------------------------------------------------------- MENU PRINCIPAL
	@SuppressWarnings("unchecked")
	public void initializeMenu(Scanner S,  String asigUrl, String matUrl, String titUrl) throws MalformedURLException, 
		RemoteException, NotBoundException {
		
		this.asigRepo = (IRepository<Asignatura>)Naming.lookup(asigUrl);
		this.matRepo = (IRepository<Matricula>) Naming.lookup(matUrl); 
		this.titRepo = (IRepository<Titulacion>) Naming.lookup(titUrl);
		String answer;
		
		do {
			System.out.println();
			System.out.println("0. Salir del programa");
			System.out.println("1. Gestionar asignaturas");
			System.out.println("2. Gestionar matriculas");
			System.out.println("3. Gestionar titulaciones");
			System.out.print(">> ");
			answer = S.nextLine();
			
			switch(answer) {
				case "0": System.out.println("Saliendo del programa..."); break;
				case "1": asigMenu(S); break;
				case "2": matMenu(S); break;
				case "3": titMenu(S); break;
				
				default:
					System.out.println("Respuesta no valida.");
			}
		}while(answer.isEmpty() || !answer.equals("0"));
	}
	
	
	// -------------------------------------------------------- MENU ASIGNATURAS
	private void asigMenu(Scanner S) {
		String answer;
		Asignatura asig = null;
		
		do {
			System.out.println();
			System.out.println("0. Volver atras");
			System.out.println("1. Añadir asignatura");
			System.out.println("2. Mostrar asignatura");
			System.out.println("3. Eliminar asignatura");
			System.out.println("4. Listar asignatura");
			System.out.print(">> ");
			answer = S.nextLine();
			
			try {
				switch(answer) {
					case "0": break;
					
					// ---------------- AÑADIR ASIGNATURA ---------------- 
					case "1":
						asig = addHandler.addAsig(S);
						if(asig == null) {
							System.out.println("ERROR: crear asignatura");
							break;
						}
						asigRepo.addModel(asig);
						System.out.println("Asignatura añadida");
					break;
					
					// ---------------- OBTENER/MOSTRAR ASIGNATURA ---------------- 
					case "2":
						asig = getHandler.getAsig(asigRepo, S);
						if(asig == null) {
							System.out.println("ERROR: mostrar asignatura");
							break;
						}
						System.out.println("ID -> " + asig.getId() + " | Nombre -> " + asig.getNombre()
						+ " | Creditos -> " + asig.getCreditos());
					break;
					
					// ---------------- ELIMINAR ASIGNATURA ---------------- 
					case "3":
						boolean removed = removeHandler.removeAsig(asigRepo, S);
						System.out.println(removed ? "Asignatura eliminada!" : "Asignatura no eliminada!");
					break;
					
					// ---------------- LISTAR ASIGNATURAS ---------------- 
					case "4": listHandler.listAsig(asigRepo); break;
					
					default:
						System.out.println("Respuesta no válida");
				}
			} catch(RemoteException ex) {
				System.out.println("aigMenu (ClientMenut): " + ex.getMessage());
			}
		} while(answer.isEmpty() || !answer.equals("0"));
	}
	
	
	// -------------------------------------------------------- MENU MATRICULAS
	private void matMenu(Scanner S) {
		String answer;
		Matricula mat = null;
		
		do { 
			System.out.println(); 
			System.out.println("0. Volver atrás");
			System.out.println("1. Añadir matrícula");
			System.out.println("2. Mostrar matrícula");
			System.out.println("3. Eliminar matrícula");
			System.out.println("4. Listar matrículas");
			System.out.print(">> ");
			answer = S.nextLine(); 
			
			try {
				switch (answer) {
					case "0": break;
					
					// ---------------- AÑADIR MATRICULA ---------------- 
					case "1":
						mat = addHandler.addMatricula(S, asigRepo);
						if(mat == null) {
							System.out.println("ERROR: crear matrícula");
							break;
						}
						matRepo.addModel(mat);
						System.out.println("Matrícula añadida");
					break;
				
					// ---------------- MOSTRAR/OBTENER MATRICULA ---------------- 
					case "2":
						mat = getHandler.getMatricula(matRepo, S);
						if (mat == null) {
							System.out.println("ERROR: mostrar matrícula");
							break;
						}
						System.out.println("ID -> " + mat.getId() + " | Alumno -> " + mat.getAlumno().getNombre() + " " + 
							mat.getAlumno().getApellidos() + " (" + mat.getAlumno().getDni() + ")");
						System.out.println("Asignaturas asociadas: " + mat.getAsignaturas().size()); 
					break;
				
					// ---------------- ELIMINAR MATRÍCULA ---------------- 
					case "3":
						boolean removed = removeHandler.removeMatricula(matRepo, S); 
						System.out.println(removed ? "Matrícula eliminada!" : "Matrícula no eliminada!");
					break;
				
					// ---------------- LISTAS MATRÍCULAS ---------------- 
					case "4": listHandler.listMatriculas(matRepo); break;
					
					default: 
						System.out.println("Respuesta no válida");
				}
			} catch (RemoteException ex) { 
				System.out.println("matMenu (ClientMenu): " + ex.getMessage()); 
			} 
		} while (answer.isEmpty() || !answer.equals("0"));
	}
	
	// -------------------------------------------------------- MENU TITULACIONES
	private void titMenu(Scanner S) {
		String answer; 
		Titulacion tit = null;
		do {
			System.out.println();
			System.out.println("0. Volver atrás");
			System.out.println("1. Añadir titulación");
			System.out.println("2. Mostrar titulación");
			System.out.println("3. Eliminar titulación");
			System.out.println("4. Listar titulaciones");
			System.out.print(">> "); 
			answer = S.nextLine(); 
			
			try {
				switch (answer) { 
					case "0": break; 
					
					// ---------------- AÑADIR TITULACIÓN ---------------- 
					case "1":
						tit = addHandler.addTIT(S, asigRepo, matRepo);
						if(tit == null) {
							System.out.println("ERROR: añadir titulación");
							break;
						}
						
						titRepo.addModel(tit);
						System.out.println("Titulación añadida");
					break; 
				
					// ---------------- OBTENER/MOSTRAR TITULACIÓN ---------------- 
					case "2":
						tit = getHandler.getTIT(titRepo, S);
						if (tit == null) { 
							System.out.println("ERROR: mostrar titulacion");
							break;
						}
						System.out.println("ID -> " + tit.getId() + " | Nombre -> " + tit.getNombre()); 
						System.out.println("Asignaturas asociadas: " + tit.getAsignaturas().size());
						System.out.println("Matrículas asociadas: " + tit.getMatriculas().size()); 
					break;
					
					// ---------------- ELIMINAR TITULACIÓN ---------------- 
					case "3":
						boolean removed = removeHandler.removeTIT(titRepo, S);
						System.out.println(removed ? "Titulación eliminada!" : "Titulación no eliminada!");
					break;
					
					// ---------------- LISTAR TITULACIONES ---------------- 
					case "4": listHandler.listTIT(titRepo); break;

					default: System.out.println("Respuesta no válida"); 
				}
			} catch (RemoteException ex) { 
				System.out.println("titMenu (ClientMenu): " + ex.getMessage());
			} 
		} while (answer.isEmpty() || !answer.equals("0"));
	}
}

