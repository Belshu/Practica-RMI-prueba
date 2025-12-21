package edu.ucam.interfaces;

import java.rmi.RemoteException;

public interface IContract extends java.rmi.Remote {
	public boolean autentication(String user, String pass) throws RemoteException;
}
