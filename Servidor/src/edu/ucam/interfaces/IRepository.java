package edu.ucam.interfaces;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRepository <T> extends java.rmi.Remote {
	public void addModel(T obj) throws RemoteException;
	public boolean remove(String id) throws RemoteException;
	public T get(String id) throws RemoteException;
	public ArrayList<T> list() throws RemoteException;
}
