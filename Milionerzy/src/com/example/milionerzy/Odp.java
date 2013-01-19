package com.example.milionerzy;

public class Odp {
	public int idPytania;
	public String odpowiedz;
	public int prawda;
	
	public Odp(int idPytania, String odpowiedzi, int prawda){
		
		this.idPytania = idPytania;
		this.odpowiedz = odpowiedzi;
		this.prawda = prawda;
		
	}

	public Odp(String idPytania, String odpowiedzi, int prawda){
		
		this.idPytania = Integer.parseInt(idPytania);
		this.odpowiedz = odpowiedzi;
		this.prawda = prawda;
		
	}
}