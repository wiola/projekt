package com.example.milionerzy;

import android.os.Handler;
import android.widget.TextView;

public class Zegar {
	private Handler timer = new Handler();
	private int secondsPassed = 0;
	private TextView zegarek;
	
	public Zegar(TextView zegarek)
	{
		this.zegarek=zegarek;
	}

	public void startTimer()
	{
	    timer.removeCallbacks(updateTimeElasped);
	    timer.postDelayed(updateTimeElasped, 1000);
	}

	public void stopTimer()
	{
	  timer.removeCallbacks(updateTimeElasped);
	}

	private Runnable updateTimeElasped = new Runnable()
	{
	  public void run()
	  {
	    long currentMilliseconds = System.currentTimeMillis();
	    ++secondsPassed;
	    zegarek.setText(Integer.toString(secondsPassed));

	    timer.postAtTime(this, currentMilliseconds);
	    timer.postDelayed(updateTimeElasped, 1000);
	  }
	};
	
	public int czas()
	{
		return secondsPassed;
	}
}
