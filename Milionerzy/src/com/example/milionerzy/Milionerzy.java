package com.example.milionerzy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Milionerzy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		
		Button button1 = (Button) findViewById(R.id.button1);
		
		final Context context = this;
		
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder tAlert = new AlertDialog.Builder(context);
				
				Dialog cos = 
				
			}
		});	
		

		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				final Intent mainIntent = new Intent(Milionerzy.this,
						Pomoc.class);
				Milionerzy.this.startActivity(mainIntent);

			}
		});

		Button button3 = (Button) findViewById(R.id.button3);

		final Context context0 = this;

		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				AlertDialog.Builder tAlertu = new AlertDialog.Builder(context0);

				tAlertu.setMessage("Milionerzy\nv1.0\nautor: wiola")
						.setCancelable(false)
						.setNegativeButton("Wróć",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {

										dialog.cancel();
									}
								});

				AlertDialog alert = tAlertu.create();

				alert.show();
			}
		});

		Button button4 = (Button) findViewById(R.id.button4);

		final Context context1 = this;

		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				AlertDialog.Builder tAlertu = new AlertDialog.Builder(context1);

				tAlertu.setMessage("Czy na pewno chcesz wyjść z gry?")
						.setCancelable(false)
						.setPositiveButton("Tak",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {

										Milionerzy.this.finish();
									}
								})
						.setNegativeButton("Nie",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {

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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

}
