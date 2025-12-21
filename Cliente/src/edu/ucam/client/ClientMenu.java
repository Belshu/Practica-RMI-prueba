package edu.ucam.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import edu.ucam.client.handlers.AddHandler;
import edu.ucam.domain.Asignatura;
import edu.ucam.interfaces.IContract;
import edu.ucam.interfaces.IRepository;



public class ClientMenu {
	private IContract autenticationServer;
	private IRepository<Asignatura> asigRepo;
	
	private final AddHandler addHandler = new AddHandler();
	
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
	
	@SuppressWarnings("unchecked")
	public void initializeMenu(Scanner S,  String asigUrl) throws MalformedURLException, RemoteException, NotBoundException {
		this.asigRepo = (IRepository<Asignatura>)Naming.lookup(asigUrl);
		
		String answer;
		
		do {
			System.out.println("0. Salir del programa.");
			System.out.println("1. Gestionar asignaturas");
			System.out.print(">> ");
			answer = S.nextLine();
			
			switch(answer) {
				case "0":
					System.out.println("Saliendo del programa...");
				break;
				
				case "1":
					asigMenu(S);
				break;
				
				default:
					System.out.println("Respuesta no valida.");
			}
			
		}while(answer.isEmpty() || !answer.equals("0"));
	}
	
	private void asigMenu(Scanner S) {
		String answer;
		
		do {
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
						Asignatura asig = addHandler.addAsig(S);
						if(asig == null) {
							System.out.println("ERROR: crear asignatura");
							break;
						}
						asigRepo.addModel(asig);
						System.out.println("Asignatura añadida");
					break;
					
					default:
						System.out.println("Respuesta no valida");
				}
			} catch(RemoteException ex) {
				System.out.println("aigMenu (ClientMenut): " + ex.getMessage());
			}
		} while(answer.isEmpty() || answer.equals("0"));
	}
}
