package edu.ucam.server.datas;

import java.rmi.RemoteException;

import edu.ucam.domain.Matricula;

public class MatRepository extends BaseRepository<Matricula> { 
	private static final long serialVersionUID = 5L; 
	
	public MatRepository() throws RemoteException { 
		super(Matricula::getId); 
		} 
	}