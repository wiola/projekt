package com.example.milionerzy;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class Powitanie extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_powitanie);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				final Intent mainIntent = new Intent(Powitanie.this,
						Milionerzy.class);
				Powitanie.this.startActivity(mainIntent);
				Powitanie.this.finish();
			}
		}, 7000);

	}

}
