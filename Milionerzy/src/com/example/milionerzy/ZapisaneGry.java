package com.example.milionerzy;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ZapisaneGry extends Activity {
	
	private static final int RB1_ID = R.id.wczytaj1;
	private static final int RB2_ID = R.id.wczytaj2;
	private static final int RB3_ID = R.id.wczytaj3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zapisane_gry);
		
		final DataBaseHelper myDbHelper = new DataBaseHelper(this);
		 
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
		 
		 final RadioGroup w = (RadioGroup) findViewById(R.id.wczytajrg);
		 
		 RadioButton rb1 = (RadioButton) findViewById(R.id.wczytaj1);
		 RadioButton rb2 = (RadioButton) findViewById(R.id.wczytaj2);
		 RadioButton rb3 = (RadioButton) findViewById(R.id.wczytaj3);
		
		Button button = (Button) findViewById(R.id.wczytajok);
		button.setOnClickListener(new OnClickListener() {
			int selectedId = w.getCheckedRadioButtonId();
			
			@Override
			    public void onClick(View arg0) {
				if(selectedId==RB1_ID)
				{
					myDbHelper.wczytaj(1);
				}
				else if(selectedId==RB2_ID)
				{
					myDbHelper.wczytaj(2);
				}
				else if(selectedId==RB3_ID)
				{
					myDbHelper.wczytaj(3);
				}
			    final Intent mainIntent = new Intent(ZapisaneGry.this, StartGry.class);
			    ZapisaneGry.this.startActivity(mainIntent);
			    ZapisaneGry.this.finish();
			}
		    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_zapisane_gry, menu);
		return true;
	}

}
