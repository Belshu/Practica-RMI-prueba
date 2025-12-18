package edu.ucam.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import edu.ucam.server.interfaces.IRepository;
import edu.ucam.domain.*;
import edu.ucam.server.datas.AsignaturasRepository;

public class MainServer {
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(5000);
		IRepository <Asignatura> repo = new AsignaturasRepository();
		Naming.rebind("rmi://localhost:5000/RepositorioAsignaturas", repo);
	}
}
