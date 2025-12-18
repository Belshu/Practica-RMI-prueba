package edu.ucam.server.interfaces;

public interface IRepository extends java.rmi.Remote {
	public void add(Object obj);
	public void remove(Object obj);
	public void get(String id);
	public void List();
}
