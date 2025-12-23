package edu.ucam.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import edu.ucam.client.handlers.AddHandler;
import edu.ucam.client.handlers.GetHandler;
import edu.ucam.client.handlers.ListHandler;
import edu.ucam.client.handlers.RemoveHandler;
import edu.ucam.domain.Asignatura;
import edu.ucam.interfaces.IContract;
import edu.ucam.interfaces.IRepository;



public class ClientMenu {
	private IContract autenticationServer;
	private IRepository<Asignatura> asigRepo; 
	
	
	// -------------------------------------------------------- HANDLERS
	private final AddHandler addHandler = new AddHandler();
	private final GetHandler getHandler = new GetHandler();
	private final RemoveHandler removeHandler = new RemoveHandler();
	private final ListHandler listHandler = new ListHandler();
	
	// -------------------------------------------------------- CONSTRUCTOR
	public ClientMenu(String url) throws MalformedURLException, RemoteException, NotBoundException{
		this.autenticationServer = (IContract)Naming.lookup(url);
	}
	
	public boolean autentication(Scanner S) throws RemoteException {
		System.out.print("Nombre: ");
		String user = S.nextLine();
		
		System.out.print("Contraseña: ");
		String pass = S.nextLine();
		
		return autenticationServer.autentication(user, pass);
	}
	
	
	// -------------------------------------------------------- MENU PRINCIPAL
	@SuppressWarnings("unchecked")
	public void initializeMenu(Scanner S,  String asigUrl) throws MalformedURLException, RemoteException, NotBoundException {
		this.asigRepo = (IRepository<Asignatura>)Naming.lookup(asigUrl);
		
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
			System.out.println("1. Añadir asignatura nueva");
			System.out.println("2. Mostrar asignatura");
			System.out.println("3. Eliminar asignatura");
			System.out.println("4. Listar asignatura");
			System.out.print(">> ");
			answer = S.nextLine();
			
			try {
				switch(answer) {
					case "0": break;
					case "1":
						asig = addHandler.addAsig(S);
						if(asig == null) {
							System.out.println("ERROR: crear asignatura");
							break;
						}
						asigRepo.addModel(asig);
						System.out.println("Asignatura añadida");
					break;
					
					case "2":
						asig = getHandler.getAsig(asigRepo, S);
						if(asig == null) {
							System.out.println("ERROR: mostrar asignatura");
							break;
						}
						System.out.println("ID -> " + asig.getId() + " | Nombre -> " + asig.getNombre()
						+ "| Creditos -> " + asig.getCreditos());
					break;
					
					case "3":
						boolean remove = removeHandler.removeAsig(asigRepo, S);
						if(remove) System.out.println("Asignatura eliminada!");
						else System.out.println("Asignatura no eliminada!");
					break;
					
					case "4": listHandler.listAsig(asigRepo); break;
					
					default:
						System.out.println("Respuesta no valida");
				}
			} catch(RemoteException ex) {
				System.out.println("aigMenu (ClientMenut): " + ex.getMessage());
			}
		} while(answer.isEmpty() || !answer.equals("0"));
	}
	
	// -------------------------------------------------------- MENU MATRICULACIONES
	private void matMenu(Scanner S) {
		
	}
	
	// -------------------------------------------------------- MENU TITULACIONES
	private void titMenu(Scanner S) {
		
	}
}
