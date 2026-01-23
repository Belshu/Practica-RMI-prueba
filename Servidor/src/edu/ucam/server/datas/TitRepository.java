package edu.ucam.server.datas;

import java.rmi.RemoteException; 

import edu.ucam.domain.Titulacion;

public class TitRepository extends BaseRepository<Titulacion>{
	private static final long serialVersionUID = 4L;
	
	public TitRepository() throws RemoteException { 
		super(Titulacion::getId); 
		}
}
