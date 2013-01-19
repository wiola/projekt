package com.example.milionerzy;

import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
		 
		 
		 
		 TextView pytanie = (TextView) findViewById(R.id.pytanie);
		 myDbHelper.zwrocPytanie();
		 int[] tablicaPytan = StanGry.wybierzPytaniaZKategori(StanGry.kategoria);
		 
		 Random rand = new Random();
		 
		 int nr = tablicaPytan[rand.nextInt(15)];
		 StanGry.nr = nr;
		 
		 pytanie.setText(StanGry.pytania[nr].pytanie);
		 
		 final RadioGroup odpowiedzi = (RadioGroup) findViewById(R.id.odpowiedzi);
		 
			int k=0;
			Odp[] tmp = new Odp[4];
			for(int j=0; j<240; ++j)
			{
				if(StanGry.pytania[nr]._id==StanGry.odp[j].idPytania)
				{
					tmp[k]=StanGry.odp[j];
					++k;
					if(k==4)
					{
						break;
					}
				}
			}
			
			for(int i=0; i<4; ++i)
			{
				if(tmp[i].prawda==1)
				{
					StanGry.prawidlowa=tmp[i].odpowiedz;
				}
			}
		 
		 //Log.e("CHUJ", Integer.toString(nr)+": "+Integer.toString(StanGry.pytania[nr].odpowiedzi[0].idPytania));
		 
		 RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton1);
		 rb1.setText(tmp[0].odpowiedz);
		 //rb1.setBackgroundColor(Color.MAGENTA);
		 RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
		 rb2.setText(tmp[1].odpowiedz);
		 RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3);
		 rb3.setText(tmp[2].odpowiedz);
		 RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton4);
		 rb4.setText(tmp[3].odpowiedz);
			Button button1 = (Button) findViewById(R.id.button1);
			
			if(StanGry.wybor==0)
				{
				TextView zegar = (TextView) findViewById(R.id.Timer);
				final Zegar z = new Zegar(zegar);
				z.startTimer();
		 
			button1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					z.stopTimer();
		 
					int selectedId = odpowiedzi.getCheckedRadioButtonId();
		 
					RadioButton rb = (RadioButton) findViewById(selectedId);
					//if((rb.getText().toString()).compareTo(PytaniaIOdpowiedzi.poprawna(StanGry.nr))==0)
					if((rb.getText().toString()).compareTo(StanGry.prawidlowa)==0)
					{
						rb.setBackgroundColor(Color.GREEN);
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
						
						AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
					    
						tAlertu.setTitle("Zdobyte punkty: ");
					    tAlertu.setMessage(Integer.toString(StanGry.punkty))
						.setCancelable(false)
						.setPositiveButton("Dalej", new DialogInterface.OnClickListener() {
										    
										    public void onClick(DialogInterface dialog, int id) {
											final Intent mainIntent = new Intent(StartGry.this, StartGry.class);
											StartGry.this.startActivity(mainIntent);
											StartGry.this.finish();
										    }
										})
						.setNegativeButton("Koniec", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							    dialog.cancel();
							    StartGry.this.finish();
							}
						    });
					    

					    AlertDialog alert = tAlertu.create();
					    alert.show();
			 
					}
					else
					{
						StanGry.punkty=NIEPRAWIDLOWA;
						rb.setBackgroundColor(Color.RED);
						
						RadioButton[] rbp = {(RadioButton) findViewById(R.id.radioButton1), (RadioButton) findViewById(R.id.radioButton2), (RadioButton) findViewById(R.id.radioButton3), (RadioButton) findViewById(R.id.radioButton4)};
						for(int i=0; i<4; ++i)
						{
							if((rbp[i].getText().toString()).compareTo(StanGry.prawidlowa)==0) {
								rbp[i].setBackgroundColor(Color.GREEN);
							}
						}
						
						AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
					    
						tAlertu.setTitle("Zdobyte punkty: ");
					    tAlertu.setMessage(Integer.toString(StanGry.punkty))
						.setCancelable(false)
						.setNegativeButton("Koniec", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							    dialog.cancel();
							    StartGry.this.finish();
							}
						    });
					    

					    AlertDialog alert = tAlertu.create();
					    alert.show();
					}
				}
		 
			});
				} ///TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
			else
			{
				button1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {			 
						int selectedId = odpowiedzi.getCheckedRadioButtonId();
			 
						RadioButton rb = (RadioButton) findViewById(selectedId);
												if((rb.getText().toString()).compareTo(StanGry.prawidlowa)==0)
						{
							rb.setBackgroundColor(Color.GREEN);
								StanGry.punkty+=PRAWIDLOWA;
								
								AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
							    
								tAlertu.setTitle("Zdobyte punkty: ");
							    tAlertu.setMessage(Integer.toString(StanGry.punkty))
								.setCancelable(false)
								.setPositiveButton("Dalej", new DialogInterface.OnClickListener() {
												    
												    public void onClick(DialogInterface dialog, int id) {
													final Intent mainIntent = new Intent(StartGry.this, StartGry.class);
													StartGry.this.startActivity(mainIntent);
													StartGry.this.finish();
												    }
												})
								.setNegativeButton("Koniec", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
									    dialog.cancel();
									    StartGry.this.finish();
									}
								    });
							    

							    AlertDialog alert = tAlertu.create();
							    alert.show();
						}
						else
						{
							StanGry.punkty=NIEPRAWIDLOWA;
							rb.setBackgroundColor(Color.RED);
							
							RadioButton[] rbp = {(RadioButton) findViewById(R.id.radioButton1), (RadioButton) findViewById(R.id.radioButton2), (RadioButton) findViewById(R.id.radioButton3), (RadioButton) findViewById(R.id.radioButton4)};
							for(int i=0; i<4; ++i)
							{
								if((rbp[i].getText().toString()).compareTo(StanGry.prawidlowa)==0) {
									rbp[i].setBackgroundColor(Color.GREEN);
								}
							}
							
							AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
						    
							tAlertu.setTitle("Zdobyte punkty: ");
						    tAlertu.setMessage(Integer.toString(StanGry.punkty))
							.setCancelable(false)
							.setNegativeButton("Koniec", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
								    dialog.cancel();
								    StartGry.this.finish();
								}
							    });
						    

						    AlertDialog alert = tAlertu.create();
						    alert.show();
						}
			 
					}
			 
				});
			}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_start_gry, menu);
		return true;
	}

}
