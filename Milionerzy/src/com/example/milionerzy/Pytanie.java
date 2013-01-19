package com.example.milionerzy;


class Pytanie {
	public Odpowiedz[] odpowiedzi;// = new Odpowiedz[4];
	public String pyt;
	public int id;
	
	public Pytanie(String pyt, int id)//Odpowiedz[] odpowiedzi)
	{
		this.pyt=pyt;
		this.id=id;
		//this.odpowiedzi=odpowiedzi;
	}
	
	/*static String poprawna(Pytanie p)
	{
		for(int h=0; h<4; ++h)
		{
			if(StanGry.pytania[0].odpowiedzi[h].prawda==true)
			{
				return StanGry.pytania[0].odpowiedzi[h].odp;
			}
		}
		return null;
	}*/

}
