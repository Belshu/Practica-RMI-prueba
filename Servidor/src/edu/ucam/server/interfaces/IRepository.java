package edu.ucam.server.interfaces;

import java.util.ArrayList;

public interface IRepository <T> extends java.rmi.Remote {
	public void add(T obj);
	public void remove(String id);
	public T get(String id);
	public ArrayList<T> List();
}
