package edu.ucam.server.datas;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import edu.ucam.server.interfaces.IRepository;
import java.util.function.Function;

public class BaseRepository <T> extends UnicastRemoteObject implements IRepository<T>{
	private static final long serialVersionUID = 1L;
	
	private final ArrayList<T> data = new ArrayList<>();
	private final Function<T, String> idGetter;
	
	protected BaseRepository(Function<T, String> idGetter) throws RemoteException {
		super();
		this.idGetter = idGetter;
	}

	@Override
	public void addModel(T obj) throws RemoteException {
		data.add(obj);
	}

	@Override
	public void remove(String id) throws RemoteException {
		T obj = get(id);
		
		if(obj != null) data.remove(obj);
	}

	@Override
	public T get(String id) throws RemoteException {
		for(T obj : data) {
			if(idGetter.apply(obj).equals(id)) return obj;
		}
		
		return null;
	}

	@Override
	public ArrayList<T> list() throws RemoteException {
		return new ArrayList<>(data);
	}
	
}
