package edu.ucam.server.datas;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import edu.ucam.domain.Asignatura;
import edu.ucam.interfaces.IRepository;

public class AsigRepository extends BaseRepository <Asignatura>{
	private static final long serialVersionUID = 3L;

	public AsigRepository() throws RemoteException {
		super(Asignatura::getId);
	}
}