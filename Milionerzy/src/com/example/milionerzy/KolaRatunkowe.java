package com.example.milionerzy;

import java.util.Random;

public class KolaRatunkowe {
	
	public static int poprawna(Odp[] a)
	{
		for(int i=0; i<4; ++i)
		{
			if(a[i].odpowiedz==StanGry.prawidlowa)
			{
				return i;
			}
		}
		
		return 0;
	}
	
	public static String telefonDoPrzyjaciela(Odp[] a) {
		int p = poprawna(a);
		
		Random rand = new Random();
		if(rand.nextDouble()<0.8)
		{
			return a[p].odpowiedz;
		}
		else
		{
			int r;
			do {
				r = rand.nextInt(4);
			} while(r==p);
			
			return a[r].odpowiedz;
		}
	}
	
	public static String pytanieDoPublicznosci(Odp[] a) {
		int p = poprawna(a);
		
		Random rand = new Random();
		if(rand.nextDouble()<0.6)
		{
			return a[p].odpowiedz;
		}
		else
		{
			int r;
			do {
				r = rand.nextInt(4);
			} while(r==p);
			
			return a[r].odpowiedz;
		}
	}

}
