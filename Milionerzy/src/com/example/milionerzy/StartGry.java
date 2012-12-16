package com.example.milionerzy;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class StartGry extends Activity {
	
	final int PRAWIDLOWA = 5;
	final int NIEPRAWIDLOWA = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_gry);
		
		final Context context = this;
		
		 DataBaseHelper myDbHelper = new DataBaseHelper(this);
		 
		 try {
			 
			 myDbHelper.createDataBase();
			  
			 } catch (IOException ioe) {
			  
			 throw new Error("Unable to create database");
			  
			 }

		 try {
		  
			 SQLiteDatabase baza = myDbHelper.zwroc();
		  
		 }catch(SQLException sqle){
		  
		 throw sqle;
		 }
		 
		 
		 
		 TextView pytanie = (TextView) findViewById(R.id.textView1);
		 myDbHelper.zwrocPytanie();
		 pytanie.setText(StanGry.pytania[0].pyt);
		 
		 final RadioGroup odpowiedzi = (RadioGroup) findViewById(R.id.odpowiedzi);
		 
		 RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton1);
		 rb1.setText(StanGry.pytania[0].odpowiedzi[0].odp);
		 //rb1.setBackgroundColor(Color.MAGENTA);
		 RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
		 rb2.setText(StanGry.pytania[0].odpowiedzi[1].odp);
		 RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3);
		 rb3.setText(StanGry.pytania[0].odpowiedzi[2].odp);
		 RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton4);
		 rb4.setText(StanGry.pytania[0].odpowiedzi[3].odp);
			Button button1 = (Button) findViewById(R.id.button1);
			
			TextView zegar = (TextView) findViewById(R.id.Timer);
			final Zegar z = new Zegar(zegar);
			z.startTimer();
		 
			button1.setOnClickListener(new OnClickListener() {
		 
				@Override
				public void onClick(View v) {
					z.stopTimer();
		 
					int selectedId = odpowiedzi.getCheckedRadioButtonId();
		 
					RadioButton rb = (RadioButton) findViewById(selectedId);
					if(rb.getText().toString().compareTo(Pytanie.poprawna(StanGry.pytania[0]))==0)
					{
						if(z.czas()<=5)
						{
							StanGry.punkty+=PRAWIDLOWA*10;
						}
						else if(z.czas()<=10)
						{
							StanGry.punkty+=PRAWIDLOWA*5;
						}
						else
						{
							StanGry.punkty+=PRAWIDLOWA;
						}
					}
					else
					{
						StanGry.punkty=NIEPRAWIDLOWA;
					}
					
					AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
				    
				    tAlertu.setMessage(Integer.toString(StanGry.punkty))
					.setCancelable(false)
					.setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
						    dialog.cancel();
						}
					    });
				    

				    AlertDialog alert = tAlertu.create();
				    alert.show();
		 
				}
		 
			});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_start_gry, menu);
		return true;
	}

}
