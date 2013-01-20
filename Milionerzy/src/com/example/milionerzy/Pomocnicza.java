package com.example.milionerzy;

import java.util.Random;

public class Pomocnicza {
	public static boolean sprawdz(int nr)
	{
		for(int i=0; i<15; ++i)
		{
			if(StanGry.nieaktualnePytania[i]==nr)
			{
				return true;
			}
		}
		
		return false;
	}
	
		  public static void losujKolejnosc(Odp[] a) {
		    int n = a.length;
		    Random random = new Random();
		    random.nextInt();
		    for (int i = 0; i < n; i++) {
		      int x = i + random.nextInt(n - i);
		      zamien(a, i, x);
		    }
		  }

		  private static void zamien(Odp[] a, int i, int x) {
		    Odp tmp = a[i];
		    a[i] = a[x];
		    a[x] = tmp;
		  }

}
