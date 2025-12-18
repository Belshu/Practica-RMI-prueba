module Servidor {
	requires java.rmi;
	requires clasesdominio;
	
	exports edu.ucam.server to java.rmi;
}