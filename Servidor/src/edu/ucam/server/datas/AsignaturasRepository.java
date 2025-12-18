package edu.ucam.server.datas;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import edu.ucam.domain.Asignatura;
import edu.ucam.server.interfaces.IRepository;

public class AsignaturasRepository extends BaseRepository <Asignatura>{
	private static final long serialVersionUID = 2L;

	public AsignaturasRepository() throws RemoteException {
		super(Asignatura::getId);
	}
}