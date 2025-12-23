package edu.ucam.client.handlers;

import java.util.Scanner;

import edu.ucam.domain.*;

public class AddHandler {
	
	public Asignatura addAsig(Scanner S) {
		Asignatura asig = new Asignatura();
		System.out.print(">> ID: ");
		String id = S.nextLine();
		asig.setId(id);
		
		System.out.print(">> Nombre: ");
		String name = S.nextLine();
		asig.setNombre(name);
		
		System.out.print(">> Creditos: ");
		String creditsStr = S.nextLine();
		int credits = -1;
		
		try {
			credits = Integer.parseInt(creditsStr.trim());
		} catch(Exception ex) {
			System.out.println("addAsig(AddHandler): " + ex.getMessage());
			return null;
		}
		if(credits == -1) return null;
		
		asig.setCreditos(credits);
		
		return asig;
	}
}
