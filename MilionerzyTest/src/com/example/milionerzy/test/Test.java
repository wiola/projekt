package com.example.milionerzy.test;

import android.test.ActivityInstrumentationTestCase2;
import com.example.milionerzy.Milionerzy;
import com.jayway.android.robotium.solo.Solo;

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
	
	public void testWpisywania() {
		
		solo.clickOnButton("Zacznij grę!");
        	solo.enterText(0, "robotium");
        	solo.waitForDialogToClose(0);

	}
	
	public void TestWrocWpisywania() {
		
		solo.clickOnButton("Zacznij grę!");
        	solo.enterText(0, "robotium");
        	solo.clickOnView(solo.getView(android.R.id.button2));
        	solo.waitForDialogToClose(0);
	}
	
	public void testWroc() {

		solo.clickOnButton("Zacznij grę!");
		solo.clickOnButton("Wróć");
		solo.waitForDialogToClose(0);
	}
	
	public void testPrzejsciaDalej() {
		
		solo.clickOnButton("Zacznij grę!");
        	solo.enterText(0, "robotium");
        	solo.clickOnView(solo.getView(android.R.id.button1));
        	solo.waitForDialogToClose(3);
	}
	
	public void testTekstuNick() {
		
		solo.clickOnButton("Zacznij grę!");
	    	solo.enterText(0, "robotium");
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	assertTrue(this.solo.waitForText("Witaj robotium!")); 
	    	solo.waitForDialogToClose(3);
}
	
	public void testWyboruPowrot() {
		
		solo.clickOnButton("Zacznij grę!");
        	solo.enterText(0, "robotium");
        	solo.clickOnView(solo.getView(android.R.id.button1));
        	solo.clickOnView(solo.getView(android.R.id.button2));
        	solo.waitForDialogToClose(1);
	}
	
	public void testWyboruKategoriBiologia() {
		
		solo.clickOnButton("Zacznij grę!");
        	solo.enterText(0, "robotium");
        	solo.clickOnView(solo.getView(android.R.id.button1));
        	solo.clickOnText("Biologia");
        	solo.clickOnView(solo.getView(android.R.id.button1));
        	solo.waitForDialogToClose(2);

	}
	
	public void testTekstuBiologia() {
		
		solo.clickOnButton("Zacznij grę!");
	    	solo.enterText(0, "robotium");
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	solo.clickOnText("Biologia");
        	solo.clickOnView(solo.getView(android.R.id.button1));
	    	assertTrue(this.solo.waitForText("Biologia")); 
	    	solo.waitForDialogToClose(3);
}
	
public void testWyboruKategoriHistoria() {
		
		solo.clickOnButton("Zacznij grę!");
        	solo.enterText(0, "robotium");
        	solo.clickOnView(solo.getView(android.R.id.button1));
        	solo.clickOnText("Historia");
        	solo.clickOnView(solo.getView(android.R.id.button1));
		solo.waitForDialogToClose(2);
	
	}

public void testTekstuHistoria() {
	
		solo.clickOnButton("Zacznij grę!");
	    	solo.enterText(0, "robotium");
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	solo.clickOnText("Historia");
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	assertTrue(this.solo.waitForText("Historia")); 
	    	solo.waitForDialogToClose(3);
}

public void testWyboruKategoriMatematyka() {
	
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnText("Matematyka");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.waitForDialogToClose(3);

	}

public void testTekstuMatematyka() {
	
		solo.clickOnButton("Zacznij grę!");
	    	solo.enterText(0, "robotium");
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	solo.clickOnText("Matematyka");
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	assertTrue(this.solo.waitForText("Matematyka")); 
	    	solo.waitForDialogToClose(3);
}

public void testWyboruKategoriFizyka() {
	
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnText("Fizyka");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.waitForDialogToClose(3);
	}

public void testTekstuFizyka() {
	
		solo.clickOnButton("Zacznij grę!");
	    	solo.enterText(0, "robotium");
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	solo.clickOnText("Fizyka");
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	assertTrue(this.solo.waitForText("Fizyka")); 
	    	solo.waitForDialogToClose(3);
}

	public void testWyjsciazCzasu() {
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button2));
		solo.waitForDialogToClose(3);
	
	}

public void testPrzejsciaDalejzCzasu() {
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.waitForDialogToClose(3);
	
}

public void testWyboruCzasTak() {
	
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.waitForDialogToClose(3);
	

}

public void testTekstuCzasTak() {
	
		solo.clickOnButton("Zacznij grę!");
	    	solo.enterText(0, "robotium");
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	solo.clickOnText("Tak");
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	assertTrue(this.solo.waitForText("Tak")); 
	    	solo.waitForDialogToClose(3);
}

public void testWyboruCzasNie() {
	
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button2));
		solo.waitForDialogToClose(3);
	

}

public void testTekstuCzasNie() {
	
		solo.clickOnButton("Zacznij grę!");
	    	solo.enterText(0, "robotium");
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	solo.clickOnText("Nie");
	    	solo.clickOnView(solo.getView(android.R.id.button1));
	    	assertTrue(this.solo.waitForText("Nie")); 
	    	solo.waitForDialogToClose(3);
}


}