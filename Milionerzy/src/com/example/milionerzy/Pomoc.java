package com.example.milionerzy;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Pomoc extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pomoc);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_pomoc, menu);
		return true;
	}

}
