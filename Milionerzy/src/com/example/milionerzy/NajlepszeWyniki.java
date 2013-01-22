package com.example.milionerzy;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NajlepszeWyniki extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_najlepsze_wyniki);
		
		final DataBaseHelper myDbHelper = new DataBaseHelper(this);
		 
		 try {
			 
			 myDbHelper.createDataBase();
			  
			 } catch (IOException ioe) {
			  
			 throw new Error("Unable to create database");
			  
			 }

		 
		 TextView wynik1 = (TextView) findViewById(R.id.wynik1);
		 TextView wynik2 = (TextView) findViewById(R.id.wynik2);
		 TextView wynik3 = (TextView) findViewById(R.id.wynik3);
		 
		 String[][] tmp;
		 
		 tmp = myDbHelper.wczytajNajlepszeWyniki();
		 wynik1.setText(tmp[0][0]+" - "+tmp[0][1]);
		 wynik2.setText(tmp[1][0]+" - "+tmp[1][1]);
		 wynik3.setText(tmp[2][0]+" - "+tmp[2][1]);
		 
		 Button button = (Button) findViewById(R.id.najlepszeb);
			button.setOnClickListener(new OnClickListener() {
				
				@Override
				    public void onClick(View arg0) {
				    NajlepszeWyniki.this.finish();
				}
			    });
		}
}
