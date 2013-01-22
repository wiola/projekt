package com.example.milionerzy;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ZapisaneGry extends Activity {
	
	private DataBaseHelper pomocnik;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zapisane_gry);
		
		final DataBaseHelper myDbHelper = new DataBaseHelper(this);
		pomocnik=myDbHelper;
		 
		 try {
			 
			 myDbHelper.createDataBase();
			  
			 } catch (IOException ioe) {
			  
			 throw new Error("Unable to create database");
			  
			 }

		 
		 final RadioGroup w = (RadioGroup) findViewById(R.id.wczytajrg);
		 
		 final RadioButton rb1 = (RadioButton) findViewById(R.id.wczytaj1);
		 final RadioButton rb2 = (RadioButton) findViewById(R.id.wczytaj2);
		 final RadioButton rb3 = (RadioButton) findViewById(R.id.wczytaj3);
		
		Button button = (Button) findViewById(R.id.wczytajok);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			    public void onClick(View arg0) {
				
				int selectedId = w.getCheckedRadioButtonId();
				 
				RadioButton rb = (RadioButton) findViewById(selectedId);
				if(rb1.getId()==rb.getId())
				{
					myDbHelper.wczytaj(1);
				}
				else if(rb2.getId()==rb.getId())
				{
					myDbHelper.wczytaj(2);
				}
				else if(rb3.getId()==rb.getId())
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
	protected void onDestroy() {
	    super.onDestroy();
	    pomocnik.close();
	}

}
