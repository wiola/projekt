package com.example.milionerzy;

class Odpowiedz {
	
	public String odp;
	public boolean prawda;
	
	public Odpowiedz(String odp, int p)
	{
		this.odp = odp;
		if(p==0)
		{
			this.prawda=false;
		}
		else
		{
			this.prawda=true;
		}
	}

}