package com.example.milionerzy.test;

import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;
import com.example.milionerzy.Milionerzy;

public class Test extends ActivityInstrumentationTestCase2<Milionerzy> {
	private Solo solo;

	public Test() {
		super("com.example.milionerzy", Milionerzy.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
	}

	public void testWyjsciaInfo() {

		solo.clickOnButton("Info");
		solo.clickOnButton("Wróć");
		solo.finishOpenedActivities();
	}

	public void testPomoc() {
	    solo.clickOnButton("Pomoc");
	    solo.finishOpenedActivities();
	}


	public void testAnulowanieWyjscia() {

		solo.clickOnButton("Wyjście");
		solo.clickOnButton("Nie");
		solo.waitForDialogToClose(0);
	}

	public void testWyjscia() {

		solo.clickOnButton("Wyjście");
		solo.clickOnButton("Tak");
		solo.finishOpenedActivities();

	}
	
	public void testWroc() {

		solo.clickOnButton("Zacznij grę!");
		solo.clickOnButton("Wróć");
		solo.waitForDialogToClose(0);
	}
	
	
}

