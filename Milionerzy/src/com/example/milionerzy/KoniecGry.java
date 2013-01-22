package com.example.milionerzy;

import java.io.IOException;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class KoniecGry extends Activity {
	private DataBaseHelper pomocnik;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_koniec_gry);
		
		 final DataBaseHelper myDbHelper = new DataBaseHelper(this);
		 pomocnik=myDbHelper;
		 
		 try {
			 
			 myDbHelper.createDataBase();
			  
			 } catch (IOException ioe) {
			  
			 throw new Error("Unable to create database");
			  
			 }

		
		TextView wynik = (TextView) findViewById(R.id.koncowyWynik);
		wynik.setText("Wygrałeś: "+Integer.toString(StanGry.punkty));
		
		Button button = (Button) findViewById(R.id.koniecp);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			    public void onClick(View arg0) {
				String[][] tmp = myDbHelper.wczytajNajlepszeWyniki();
				
				Wyniki[] tab = new Wyniki[4];
				
				for(int i=0; i<3; ++i)
				{
					tab[i] = new Wyniki(tmp[i][0], Integer.parseInt(tmp[i][1]));
				}
				
				tab[3] = new Wyniki(StanGry.nazwaUzytkownika, StanGry.punkty);
				
				Arrays.sort(tab);
				
				myDbHelper.usun("Scores");
				
				myDbHelper.najlepsze(tab);
				boolean p=false;
				for(int i=0; i<3; ++i)
				{
					if(tab[i].pkt==StanGry.punkty)
					{
						p=true;
						break;
					}
				}
				
				if(p==false)
				{
				    KoniecGry.this.finish();
				}
				else
				{
					final Intent mainIntent = new Intent(KoniecGry.this, NajlepszeWyniki.class);
				    KoniecGry.this.startActivity(mainIntent);
				    KoniecGry.this.finish();
				}
			}
		    });
	}
	
	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    pomocnik.close();
	}

}
