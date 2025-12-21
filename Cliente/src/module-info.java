module Cliente {
	requires java.rmi;
	requires clasesdominio;
	
	exports edu.ucam.client to java.rmi;
}