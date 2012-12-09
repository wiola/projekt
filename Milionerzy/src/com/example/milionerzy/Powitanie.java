package com.example.milionerzy;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_powitanie, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.addToDictionary:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
