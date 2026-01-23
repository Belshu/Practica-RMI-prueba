package edu.ucam.server.config;

public class ServerConfig {
	public final static int PORT = 5000;
	public final static String nameBindContract = "rmi://localhost:5000/ContratoAutenticacion";
	public final static String nameBindAsigRepository = "rmi://localhost:5000/RepositorioAsignaturas";
	public final static String nameBindMatRepository = "rmi://localhost:5000/RepositorioMatriculas"; 
	public final static String nameBindTitRepository = "rmi://localhost:5000/RepositorioTitulaciones";
	public final static String name = "admin";
	public final static String pass = "admin";
}
