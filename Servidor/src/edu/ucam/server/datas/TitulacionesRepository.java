package edu.ucam.server.datas;

import java.rmi.RemoteException; 

import edu.ucam.domain.Titulacion;

public class TitulacionesRepository extends BaseRepository<Titulacion>{
	private static final long serialVersionUID = 4L;
	
	public TitulacionesRepository() throws RemoteException { 
		super(Titulacion::getId); 
		}
}
