package com.example.milionerzy;

public class PytaniaIOdpowiedzi {
	public Pyt pytanie;
	public Odp[] odpowiedzi;
	
	public PytaniaIOdpowiedzi(Pyt pytanie, Odp[] odpowiedzi) {
		this.pytanie = pytanie;
		this.odpowiedzi = odpowiedzi;
	}
	
	/*public static String poprawna(int n)
	{
		for(int i=0; i<4; ++i)
		{
			if(StanGry.odpowiedzi[i].prawda==1)
			{
				return StanGry.pytania[n].odpowiedzi[i].odpowiedz;
			}
		}
		return "";
	}*/
}
