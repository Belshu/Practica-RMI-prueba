package edu.ucam.server.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.ucam.interfaces.IContract;

public class ContractImp extends UnicastRemoteObject implements IContract{
	private static final long serialVersionUID = 1L;

	public ContractImp() throws RemoteException {
		super();
	}

	@Override
	public boolean autentication(String user, String pass) throws RemoteException {
		return user.equals("admin") && pass.equals("admin");
	}
}
