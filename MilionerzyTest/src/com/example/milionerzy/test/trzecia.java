package com.example.milionerzy.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.AssertionFailedError;

import com.example.milionerzy.Milionerzy;
import com.example.milionerzy.R;
import com.example.milionerzy.StartGry;
import com.jayway.android.robotium.solo.Solo;


	public class trzecia extends ActivityInstrumentationTestCase2<Milionerzy> {
		private Solo solo;

		public trzecia() {
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
		
		
		public void testWyboruOdp1() {
			
			solo.clickOnButton("Zacznij grę!");
		    solo.enterText(0, "robotium");
		    solo.clickOnView(solo.getView(android.R.id.button1));
		    solo.clickOnView(solo.getView(android.R.id.button1));
		    solo.clickOnView(solo.getView(android.R.id.button1));
		    solo.clickOnView(solo.getView(android.R.id.button2));
		    solo.clickOnRadioButton(0);
		    solo.isRadioButtonChecked(0);
		    solo.clickOnButton("OK");
		    solo.finishOpenedActivities();
		}

		
}
