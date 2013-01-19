package com.example.milionerzy.test;

import android.test.ActivityInstrumentationTestCase2;
import com.example.milionerzy.Milionerzy;
import com.example.milionerzy.R;
import com.example.milionerzy.StartGry;
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
	
	
	public void testObecnosciPrzyciskZacznijGre() {
		
		solo.searchButton("Zacznij grę!");
		solo.finishOpenedActivities();
		
	}
	
	public void testObecnosciPrzyciskPomoc() {
		
		solo.searchButton("Pomoc");
		solo.finishOpenedActivities();
	}
	
	public void testObecnosciPrzyciskInfo() {
		
		solo.searchButton("Info");
		solo.finishOpenedActivities();
	}
	
	public void testInfo() {
		
		solo.clickOnButton("Info");
		solo.finishOpenedActivities();
	}
	
	public void testObecnosciPrzyciskuWrocInfo() {
		
		solo.clickOnButton("Info");
		solo.searchButton("Wróć");
		solo.finishOpenedActivities();
	}
	
	public void testWyjsciaInfo() {

		solo.clickOnButton("Info");
		solo.clickOnButton("Wróć");
		solo.finishOpenedActivities();
	}
	
	
	public void testObecnosciPrzyciskWyjscie() {
		
		solo.searchButton("Wyjście");
		solo.finishOpenedActivities();
	}
	

	public void testPomoc() {
	    solo.clickOnButton("Pomoc");
	    solo.finishOpenedActivities();
	}
	
	public void testPowrotuPomoc() {
		
	    solo.clickOnButton("Pomoc");
		solo.goBack();
		solo.finishOpenedActivities();
	}

	public void testObecnosciNieWyjscie() {
		
		solo.clickOnButton("Wyjście");
		solo.searchButton("Nie");
		solo.finishOpenedActivities();
	}
	
	public void testObecnosciTakWyjscie() {
		
		solo.clickOnButton("Wyjście");
		solo.searchButton("Tak");
		solo.finishOpenedActivities();
	}

	public void testAnulowanieWyjscia() {

		solo.clickOnButton("Wyjście");
		solo.clickOnButton("Nie");
		solo.finishOpenedActivities();
	}

	public void testWyjscia() {

		solo.clickOnButton("Wyjście");
		solo.clickOnButton("Tak");
		solo.finishOpenedActivities();
	}
	
	public void testWpisywania() {
		
		solo.clickOnButton("Zacznij grę!");
        solo.enterText(0, "robotium");
        solo.finishOpenedActivities();
	}
	
	public void testObecnosciOkZacznijGre() {
		
		solo.clickOnButton("Zacznij grę!");
		solo.searchButton("OK");
		solo.finishOpenedActivities();
	}

	public void testObecnosciWrocZacznijGre() {
	
		solo.clickOnButton("Zacznij grę!");
		solo.searchButton("Wróć");
		solo.finishOpenedActivities();
	}
	
	public void TestWrocWpisywania() {
		
		solo.clickOnButton("Zacznij grę!");
        solo.enterText(0, "robotium");
        solo.clickOnView(solo.getView(android.R.id.button2));
        solo.finishOpenedActivities();
	}
	
	public void testWrocZzacznijGre() {

		solo.clickOnButton("Zacznij grę!");
		solo.clickOnButton("Wróć");
		solo.finishOpenedActivities();
	}
	
	public void testPrzejsciaDalej() {
		
		solo.clickOnButton("Zacznij grę!");
        solo.enterText(0, "robotium");
        solo.clickOnView(solo.getView(android.R.id.button1));
        solo.finishOpenedActivities();
	}
	
	public void testTekstuNick() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    assertTrue(this.solo.waitForText("Witaj robotium!")); 
	    solo.finishOpenedActivities();
	}
	
	public void testMiejscaDoZapisuNicku() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    assertTrue(solo.getString(R.string.podaj_nick), true);
	    solo.finishOpenedActivities();
	}
	
	public void testObecnosciOKKategorie() {
		
		solo.clickOnButton("Zacznij grę!");
        solo.enterText(0, "robotium");
        solo.clickOnView(solo.getView(android.R.id.button1));
        solo.searchButton("OK");
        solo.finishOpenedActivities();
	}
	
	public void testObecnosciWrocKategorie() {
		
		solo.clickOnButton("Zacznij grę!");
        solo.enterText(0, "robotium");
        solo.clickOnView(solo.getView(android.R.id.button1));
        solo.searchButton("Wróć");
        solo.finishOpenedActivities();
	}
	
	
	public void testPowrotZKategorii() {
		
		solo.clickOnButton("Zacznij grę!");
        solo.enterText(0, "robotium");
        solo.clickOnView(solo.getView(android.R.id.button1));
        solo.clickOnView(solo.getView(android.R.id.button2));
        solo.finishOpenedActivities();
	}
	
	
	
	public void testWyboruKategoriBiologia() {
		
		solo.clickOnButton("Zacznij grę!");
        solo.enterText(0, "robotium");
        solo.clickOnView(solo.getView(android.R.id.button1));
        solo.clickOnText("Biologia");
        solo.clickOnView(solo.getView(android.R.id.button1));
        solo.finishOpenedActivities();
	}
	
	public void testZaznaczeniaRadioButtonBiologia() {
		
		solo.clickOnButton("Zacznij grę!");
        solo.enterText(0, "robotium");
        solo.clickOnView(solo.getView(android.R.id.button1));
        solo.clickOnText("Biologia");
        solo.isRadioButtonChecked("Biologia");
        solo.finishOpenedActivities();
	}
	
	public void testTekstuBiologia() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnText("Biologia");
        solo.clickOnView(solo.getView(android.R.id.button1));
	    assertTrue(this.solo.waitForText("Biologia")); 
	    solo.finishOpenedActivities();
	}
	
	public void testZaznaczeniaRadioButtonHistoria() {
		
		solo.clickOnButton("Zacznij grę!");
        solo.enterText(0, "robotium");
        solo.clickOnView(solo.getView(android.R.id.button1));
        solo.clickOnText("Historia");
        solo.isRadioButtonChecked("Historia");
        solo.finishOpenedActivities();
	}
	
	public void testWyboruKategoriHistoria() {
		
		solo.clickOnButton("Zacznij grę!");
        solo.enterText(0, "robotium");
        solo.clickOnView(solo.getView(android.R.id.button1));
        solo.clickOnText("Historia");
        solo.clickOnView(solo.getView(android.R.id.button1));
        solo.finishOpenedActivities();
	}


	public void testTekstuHistoria() {
	
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnText("Historia");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    assertTrue(this.solo.waitForText("Historia")); 
	    solo.finishOpenedActivities();
	}

	public void testZaznaczoniaRadioButtonMatematyka() {
	
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnText("Matematyka");
	    solo.isRadioButtonChecked("Matematyka");
	    solo.finishOpenedActivities();
	    
    }

	public void testWyboruKategoriMatematyka() {
	
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnText("Matematyka");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.finishOpenedActivities();
	}

	public void testTekstuMatematyka() {
	
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnText("Matematyka");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    assertTrue(this.solo.waitForText("Matematyka")); 
	    solo.finishOpenedActivities();
	}

	public void testZaznaczeniaRadioButtonAstronomia() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnText("Astronomia");
	    solo.isRadioButtonChecked("Astronomia");
	    solo.finishOpenedActivities();
    }

	public void testWyboruKategoriAstronomia() {
	
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnText("Astronomia");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.finishOpenedActivities();
	}

	public void testTekstuAstronomia() {
	
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnText("Astronomia");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    assertTrue(this.solo.waitForText("Astronomia")); 
	    solo.finishOpenedActivities();
	}

	public void testObecnosciWrocCzas() {
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.searchButton("Wróć");
		solo.finishOpenedActivities();
	}

	public void testPowrotuzCzasu() {
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button2));
		solo.finishOpenedActivities();
	
	}
	
	public void testObecnosciOKCzas() {
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.searchButton("OK");
		solo.finishOpenedActivities();
	}

	public void testZaznaczeniaRadioButtonTak() {
	
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnText("Tak");
		solo.isRadioButtonChecked("Tak");
		solo.finishOpenedActivities();
	}

	public void testOKCzas() {
	
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.finishOpenedActivities();
	}

	public void testTekstuCzasTak() {
	
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnText("Tak");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    assertTrue(this.solo.waitForText("Tak")); 
	    solo.finishOpenedActivities();
	}

	public void testZaznaczeniaRadioButtonNie() {
	
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnText("Nie");
		solo.isRadioButtonChecked("Nie");
		solo.finishOpenedActivities();
	}

	public void testWyboruNieCzas() {
	
		solo.clickOnButton("Zacznij grę!");
		solo.enterText(0, "robotium");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.clickOnText("Nie");
		solo.clickOnView(solo.getView(android.R.id.button1));
		solo.finishOpenedActivities();
	}

	public void testTekstuNieCzas() {
	
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnText("Nie");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    assertTrue(this.solo.waitForText("Nie")); 
		solo.finishOpenedActivities();
	}

	public void testAlertuPrzypominajacego() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    assertTrue(this.solo.waitForText("Pamiętaj, że "));
	    solo.finishOpenedActivities();
	}

	public void testObenosciPrzyciskuWrocAlrtPrzyp() {
	
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.searchButton("Wróć");
	    solo.finishOpenedActivities();
	}

	public void testObecnosciZegara() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.getView(R.id.Timer);
	    solo.finishOpenedActivities();
	}

	public void testObecnosciPytania() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.getView(R.id.pytanie);
	    solo.finishOpenedActivities();
	}

	public void testObecnosciOdpowiedzi() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.getView(R.id.odpowiedzi);
	    solo.finishOpenedActivities();
	}

	public void testObecnosciOkStartGry() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.searchButton("OK");
	    solo.finishOpenedActivities();
	}

	public void testZaznaczeniaRadioButton1() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.clickOnRadioButton(0);
	    solo.isRadioButtonChecked(0);
	    solo.finishOpenedActivities();
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

	public void testZaznaczeniaRadioButton2() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.clickOnRadioButton(1);
	    solo.isRadioButtonChecked(1);
	    solo.finishOpenedActivities();
	}

	public void testWyboruOdp2() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.clickOnRadioButton(1);
	    solo.isRadioButtonChecked(1);
	    solo.clickOnButton("OK");
	    solo.finishOpenedActivities();
	}

	public void testZaznaczenia1RadioButton3() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.clickOnRadioButton(2);
	    solo.isRadioButtonChecked(2);
	    solo.finishOpenedActivities();
	}

	public void testWyboruOdp3() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.clickOnRadioButton(2);
	    solo.isRadioButtonChecked(2);
	    solo.clickOnButton("OK");
	    solo.finishOpenedActivities();
	}

	public void testZaznaczenia1RadioButton4() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.clickOnRadioButton(3);
	    solo.isRadioButtonChecked(3);
	    solo.finishOpenedActivities();
	}

	public void testWyboruOdp4() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.clickOnRadioButton(3);
	    solo.isRadioButtonChecked(3);
	    solo.clickOnButton("OK");
	    solo.finishOpenedActivities();
	}
	
	public void testAlertPodsumowanie() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.clickOnRadioButton(0);
	    solo.isRadioButtonChecked(0);
	    solo.clickOnButton("OK");
	    solo.searchText("Zdobyte punkty: ");
	    solo.finishOpenedActivities();
	}
	
	public void testObecnosciWrocPodsumowania() {
		
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.clickOnRadioButton(3);
	    solo.isRadioButtonChecked(3);
	    solo.clickOnButton("OK");
	    assertTrue(solo.searchButton("Wróć", true));
	    solo.finishOpenedActivities();
	    
	}
	
	public void testPowrotZPodsumowania() {
			
		solo.clickOnButton("Zacznij grę!");
	    solo.enterText(0, "robotium");
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button1));
	    solo.clickOnView(solo.getView(android.R.id.button2));
	    solo.clickOnRadioButton(3);
	    solo.isRadioButtonChecked(3);
	    solo.clickOnButton("OK");
	    solo.clickOnButton("Wróć");
	    solo.finishOpenedActivities();
	}

}