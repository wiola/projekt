package com.example.milionerzy;

class StanGry {

	public static String nazwaUzytkownika;
	public static int kategoria = 0;
	public final static String[] kategorie = { "Biologia", "Historia", "Matematyka", "Astronomia" };
	public static int wybor = 0;
	public final static String[] czas = {"Tak", "Nie"};
	public static Pyt[] pytania = new Pyt[60];
	public static Odp[] odp = new Odp[240];
	public static int punkty = 0;
	public static int nr;
	public static String prawidlowa;
	public static int iloscPytan = 0;
	public static volatile int slot = 0;
	public static int[] nieaktualnePytania = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
	public static int KoloRatunkowe = 0;
	public final static String[] KolaRatunkowe = {"Telefon do przyjaciela", "Pytanie do publiczności", "50/50" };
	public static Odp[] aktualneOdpowiedzi;
	public static boolean wykorzystaneKola[] = new boolean[3];
	public static int[] tablicaPytan;
	
	public static int[] wybierzPytaniaZKategori(int id)
	{
		int wynik[] = new int[15];
		int k=0;
		for(int i=0; i<pytania.length; ++i)
		{
			if(pytania[i].idkategori==id)
			{
				wynik[k]=i;
				++k;
				if(k==15)
				{
					break;
				}
			}
		}
		
		return wynik;
	}
	
	public static void zeruj() {
		punkty=0;
		iloscPytan=0;
		
		for(int i=0; i<15; ++i)
		{
			nieaktualnePytania[i]=-1;
		}
		
		for(int i=0; i<3; ++i)
		{
			wykorzystaneKola[i]=false;
		}
	}
}
