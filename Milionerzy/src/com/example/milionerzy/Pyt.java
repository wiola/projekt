package com.example.milionerzy;

import android.util.Log;

public class Pyt {
	public int _id;
	public int idkategori;
	public String pytanie;
	
	public Pyt(int _id, int idkategori, String pytanie){
		
		this._id = _id;
		this.idkategori = idkategori;
		this.pytanie = pytanie;
		//Log.d("App_Name", Integer.toString(_id)+" "+pytanie);
		//System.out.println(_id + " " + pytanie);
	}

public Pyt(String _id, int idkategori, String pytanie){
		
		this._id = Integer.parseInt(_id);
		this.idkategori = idkategori;
		this.pytanie = pytanie;
		
	}
}