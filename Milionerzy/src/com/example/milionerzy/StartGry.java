package com.example.milionerzy;

import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class StartGry extends Activity {
	
	final int PRAWIDLOWA = 5;
	final int NIEPRAWIDLOWA = 0;
	final String pauza = "               ";
	
	DataBaseHelper pomocnik;

	Zegar zz;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_gry);
		
		final Context context = this;
		
		 final DataBaseHelper myDbHelper = new DataBaseHelper(this);
		 pomocnik = myDbHelper;
		 
		 try {
			 
			 myDbHelper.createDataBase();
			  
			 } catch (IOException ioe) {
			  
			 throw new Error("Unable to create database");
			  
			 }

		 
		 
		 
		 TextView pytanie = (TextView) findViewById(R.id.pytanie);
		 myDbHelper.zwrocPytanie();
		 int[] tablicaPytan = StanGry.wybierzPytaniaZKategori(StanGry.kategoria);
		 
		 Random rand = new Random();
		 int nr;
		 do {
			 nr = tablicaPytan[rand.nextInt(15)];
		 } while (Pomocnicza.sprawdz(nr));
		 StanGry.nr = nr;
		 
		 StanGry.nieaktualnePytania[StanGry.iloscPytan]=nr;
		 
		 pytanie.setText(StanGry.pytania[nr].pytanie);
		 
		 final RadioGroup odpowiedzi = (RadioGroup) findViewById(R.id.odpowiedzi);
		 
			int k=0;
			Odp[] tmp = new Odp[4];
			for(int j=0; j<240; ++j)
			{
				if(StanGry.pytania[nr]._id==StanGry.odp[j].idPytania)
				{
					tmp[k]=StanGry.odp[j];
					++k;
					if(k==4)
					{
						break;
					}
				}
			}
			
			Pomocnicza.losujKolejnosc(tmp);
			StanGry.aktualneOdpowiedzi=tmp;
			
			for(int i=0; i<4; ++i)
			{
				if(tmp[i].prawda==1)
				{
					StanGry.prawidlowa=tmp[i].odpowiedz;
				}
			}
		 
		 RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton1);
		 rb1.setText(pauza + tmp[0].odpowiedz);
		 rb1.setBackgroundResource(R.drawable.odp);
		 RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
		 rb2.setText(pauza + tmp[1].odpowiedz);
		 rb2.setBackgroundResource(R.drawable.odp);
		 RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3);
		 rb3.setText(pauza + tmp[2].odpowiedz);
		 rb3.setBackgroundResource(R.drawable.odp);
		 RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton4);
		 rb4.setText(pauza + tmp[3].odpowiedz);
		 rb4.setBackgroundResource(R.drawable.odp);
		 
		 rb1.setSelected(true);
			Button button1 = (Button) findViewById(R.id.button1);
			

				TextView zegar = (TextView) findViewById(R.id.Timer);
				final Zegar z = new Zegar(zegar);
				zz = z;
				if(StanGry.wybor==0)
					{
					z.startTimer();
					}
				else
				{
					zegar.setText("");
				}
		 
			button1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(StanGry.wybor==0)
					{
						z.stopTimer();
					}
		 
					int selectedId = odpowiedzi.getCheckedRadioButtonId();
		 
					RadioButton rb = (RadioButton) findViewById(selectedId);
					if((rb.getText().toString()).compareTo(pauza+StanGry.prawidlowa)==0)
					{
						++StanGry.iloscPytan;
						rb.setBackgroundColor(Color.GREEN);
						if(StanGry.wybor==0)
						{
							if(z.czas()<=5)
							{
								StanGry.punkty+=PRAWIDLOWA*10;
							}
							else if(z.czas()<=10)
							{
								StanGry.punkty+=PRAWIDLOWA*5;
							}
							else
							{
								StanGry.punkty+=PRAWIDLOWA;
							}
						}
						else
						{
							StanGry.punkty+=PRAWIDLOWA;
						}
						
						if(StanGry.iloscPytan!=15)
						{
						AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
					    
						tAlertu.setTitle("Brawo!\nTo poprawna odpowiedź :)");
					    tAlertu.setMessage("Zdobyte punkty: " + Integer.toString(StanGry.punkty))
						.setCancelable(false)
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
										    
											@Override
										    public void onClick(DialogInterface dialog, int id) {
										    	AlertDialog.Builder tAlertu1 = new AlertDialog.Builder(context);
												tAlertu1.setTitle("Czy chcesz grać dalej?");
												tAlertu1.setMessage("Pamiętaj, że podczas złej odpowiedzi tracisz wszystkie zdobyte puknty\nWięc jak?")
												.setCancelable(false)
												.setPositiveButton("Gram dalej", new DialogInterface.OnClickListener() {
													
													@Override
													public void onClick(DialogInterface dialog, int id) {
														final Intent mainIntent = new Intent(StartGry.this, StartGry.class);
														StartGry.this.startActivity(mainIntent);
														StartGry.this.finish();
													    }
													})
													.setNeutralButton("Zapisz", new DialogInterface.OnClickListener() {
													
													@Override
													public void onClick(DialogInterface dialog, int id) {
														AlertDialog.Builder tAlertu3 = new AlertDialog.Builder(context);
													    tAlertu3.setTitle("Wybierz slot");
													    
													    tAlertu3.setSingleChoiceItems(new String[] {"Slot 1", "Slot 2", "Slot 3"},0,new DialogInterface.OnClickListener() {
														    
														    public void onClick(DialogInterface dialog, int item) {
															StanGry.slot=item;
														    }
														});
													    
													    
													    tAlertu3.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener() {
														    
														    @Override
															public void onClick(DialogInterface dialog, int id) {
														    	++StanGry.slot;
														    	myDbHelper.update("Gry", myDbHelper.gry(StanGry.slot), Integer.toString(StanGry.slot));
															myDbHelper.usun("zapis"+Integer.toString(StanGry.slot));
															myDbHelper.nieaktualnePytania(StanGry.slot);
															StartGry.this.finish();
															}
														});
													    
													    
													    tAlertu3.setNegativeButton("Wróć",new DialogInterface.OnClickListener() {
														    
														    public void onClick(DialogInterface dialog,int id) {
															dialog.cancel();
														    }
														});
													    
													    
													    AlertDialog alert = tAlertu3.create();
													    alert.show();
													    dialog.cancel();
													    }
													})
												
										    			
						
												.setNegativeButton("Kończę", new DialogInterface.OnClickListener() {
													
												@Override
												public void onClick(DialogInterface dialog, int id) {
													AlertDialog.Builder tAlertu2 = new AlertDialog.Builder(context);
													tAlertu2.setMessage("Jeżeli jesteś pewny tej decyzji to w takim razie dzięki wielkie za grę :)")
													.setCancelable(false)
													.setNegativeButton("Koniec", new DialogInterface.OnClickListener() {
														
														@Override
														public void onClick(DialogInterface dialog, int id) {
														dialog.cancel();
														final Intent mainIntent = new Intent(StartGry.this, KoniecGry.class);
										    		    StartGry.this.startActivity(mainIntent);
										    		    StartGry.this.finish();
													}
													
														
												});
													
													AlertDialog alert = tAlertu2.create();
												    alert.show();
	
							}
						    });
					    

					    AlertDialog alert = tAlertu1.create();
					    alert.show();
			 
					}
						});
					    
					    AlertDialog alert = tAlertu.create();
					    alert.show();
						}
						else
						{
							final Intent mainIntent = new Intent(StartGry.this, KoniecGry.class);
			    		    StartGry.this.startActivity(mainIntent);
			    		    StartGry.this.finish();
						}
					}
					
					
					else
					{
						++StanGry.iloscPytan;
						StanGry.punkty=NIEPRAWIDLOWA;
						rb.setBackgroundColor(Color.RED);
						
						RadioButton[] rbp = {(RadioButton) findViewById(R.id.radioButton1), (RadioButton) findViewById(R.id.radioButton2), (RadioButton) findViewById(R.id.radioButton3), (RadioButton) findViewById(R.id.radioButton4)};
						for(int i=0; i<4; ++i)
						{
							if((rbp[i].getText().toString()).compareTo(pauza+StanGry.prawidlowa)==0) {
								rbp[i].setBackgroundColor(Color.GREEN);
							}
						}
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
						AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
					    
						tAlertu.setTitle("To niestety nieprawidłowa odpowiedź :(");
					    tAlertu.setMessage("Zdobyte punkty: " + Integer.toString(StanGry.punkty))
						.setCancelable(false)
						
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
													
												@Override
												public void onClick(DialogInterface dialog, int id) {
													AlertDialog.Builder tAlertu2 = new AlertDialog.Builder(context);
													tAlertu2.setTitle("To koniec...");
													tAlertu2.setMessage("Zgodnie z zasadami musimy zakończyć grę...\nWszystkie punkty, które dotychczas zebrałeś przepadły...")
													.setCancelable(false)
													.setNegativeButton("Koniec", new DialogInterface.OnClickListener() {
														
														@Override
														public void onClick(DialogInterface dialog, int id) {
														dialog.cancel();
														StartGry.this.finish();
													}
													
														
												});
													
													AlertDialog alert = tAlertu2.create();
												    alert.show();
	
							}
						    });
					    

					    AlertDialog alert = tAlertu.create();
					    alert.show();
							}
							}, 3000);
					}
				}
		 
			});


	}

	public static final int MENU_PAUZA = Menu.FIRST;
	public static final int MENU_KOLA = Menu.FIRST + 1;
	
	final Context context = this;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    super.onCreateOptionsMenu(menu);

	    menu.add(Menu.NONE, MENU_PAUZA, Menu.NONE, "Pauza");
	    menu.add(Menu.NONE, MENU_KOLA, Menu.NONE, "Koła Ratunkowe");
	    return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case MENU_PAUZA:
            	zz.stopTimer();

            	
            	AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
            	tAlertu.setTitle("Puza");
            	tAlertu.setMessage("\n\n\nJeżeli chcesz powrócić do gry kliknij poniższy przycisk ;)\n\n\n")
            	.setCancelable(false)
            	.setNegativeButton("Koniec pauzy", new DialogInterface.OnClickListener() {
													
													@Override
													public void onClick(DialogInterface dialog, int id) {
													dialog.cancel();
													zz.startTimer();
												}
            	});
            	

				AlertDialog alert = tAlertu.create();
			    alert.show();

            return true;
        case MENU_KOLA:
        	int wspolczynnnikKol=0;
        	
        	if(StanGry.wykorzystaneKola[0]==false)
        	{
        		wspolczynnnikKol=wspolczynnnikKol+1;
        	}
        	if(StanGry.wykorzystaneKola[1]==false)
        	{
        		wspolczynnnikKol=wspolczynnnikKol+2;
        	}
        	if(StanGry.wykorzystaneKola[2]==false)
        	{
        		wspolczynnnikKol=wspolczynnnikKol+4;
        	}
        	
        	zz.stopTimer();
        	
        	switch(wspolczynnnikKol)
        	{
        	
        	default:
        		
        		AlertDialog.Builder tAlertu1= new AlertDialog.Builder(context);
        		tAlertu1.setTitle("Koła ratunkowe");
        		tAlertu1.setMessage("Wykorzystałeś już wszystkie dostępne koła ratunkowe...");
        		tAlertu1.setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
			    	
			    	@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
						
						zz.startTimer();
			    	}
			    });
        		
        		AlertDialog alert1 = tAlertu1.create();
			    alert1.show();
			    
			    break;
			    
        		
        	case 1:
        		
        		AlertDialog.Builder tAlertu2 = new AlertDialog.Builder(context);
            	tAlertu2.setTitle("Koła ratunkowe");
            	tAlertu2.setSingleChoiceItems(new String[] {"Telefon do przyjaciela"}, 0, new DialogInterface.OnClickListener() {
            		
            		@Override
    				public void onClick(DialogInterface dialog, int item) {
    				    StanGry.KoloRatunkowe = item;
    				}
            	});
			    
            	tAlertu2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            		
            		@Override
    				public void onClick(DialogInterface dialog, int item) {
            			
            			String s = KolaRatunkowe.telefonDoPrzyjaciela(StanGry.aktualneOdpowiedzi);
						AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
					    tAlertu.setTitle("Przyjaciel mówi:");
					    tAlertu.setMessage(s);
					    
					    tAlertu.setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
					    	
					    	@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
								zz.startTimer();
					    	}
					    });
					    
					    AlertDialog alert = tAlertu.create();
					    
					    RadioButton rb;
					    if(s.compareTo(StanGry.aktualneOdpowiedzi[0].odpowiedz)==0)
						{
							rb = (RadioButton) findViewById(R.id.radioButton1);
							rb.setBackgroundColor(Color.YELLOW);
						}
					    if(s.compareTo(StanGry.aktualneOdpowiedzi[1].odpowiedz)==0)
						{
							rb = (RadioButton) findViewById(R.id.radioButton2);
							rb.setBackgroundColor(Color.YELLOW);
						}
					    if(s.compareTo(StanGry.aktualneOdpowiedzi[2].odpowiedz)==0)
						{
							rb = (RadioButton) findViewById(R.id.radioButton3);
							rb.setBackgroundColor(Color.YELLOW);
						}
					    if(s.compareTo(StanGry.aktualneOdpowiedzi[3].odpowiedz)==0)
						{
							rb = (RadioButton) findViewById(R.id.radioButton4);
							rb.setBackgroundColor(Color.YELLOW);
						}
					    
					    alert.show();
					    StanGry.wykorzystaneKola[0]=true;
					    StanGry.KoloRatunkowe=0;
            		}
            	});
			    
            	AlertDialog alert2 = tAlertu2.create();
			    alert2.show();
			    break;
			    
        	case 2:
        		
        		AlertDialog.Builder tAlertu3 = new AlertDialog.Builder(context);
            	tAlertu3.setTitle("Koła ratunkowe");
            	tAlertu3.setSingleChoiceItems(new String[] {"Pytanie do publiczności"}, 0, new DialogInterface.OnClickListener() {
            		
            		@Override
    				public void onClick(DialogInterface dialog, int item) {
    				    StanGry.KoloRatunkowe = item;
    				}
            	});
            	
            	tAlertu3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            		
            		@Override
    				public void onClick(DialogInterface dialog, int item) {
            			
            			String s = KolaRatunkowe.pytanieDoPublicznosci(StanGry.aktualneOdpowiedzi);
						AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
						tAlertu.setTitle("Zdaniem publiczności poprawna odpowiedź to:");
						tAlertu.setMessage(s);
						tAlertu.setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
								zz.startTimer();
								
							}
						});
						
						AlertDialog alert = tAlertu.create();
						
						   RadioButton rb;
						    if(s.compareTo(StanGry.aktualneOdpowiedzi[0].odpowiedz)==0)
							{
								rb = (RadioButton) findViewById(R.id.radioButton1);
								rb.setBackgroundColor(Color.YELLOW);
							}
						    if(s.compareTo(StanGry.aktualneOdpowiedzi[1].odpowiedz)==0)
							{
								rb = (RadioButton) findViewById(R.id.radioButton2);
								rb.setBackgroundColor(Color.YELLOW);
							}
						    if(s.compareTo(StanGry.aktualneOdpowiedzi[2].odpowiedz)==0)
							{
								rb = (RadioButton) findViewById(R.id.radioButton3);
								rb.setBackgroundColor(Color.YELLOW);
							}
						    if(s.compareTo(StanGry.aktualneOdpowiedzi[3].odpowiedz)==0)
							{
								rb = (RadioButton) findViewById(R.id.radioButton4);
								rb.setBackgroundColor(Color.YELLOW);
							}
					    alert.show();
					    StanGry.wykorzystaneKola[1]=true;
					    StanGry.KoloRatunkowe=0;
            		}
            		});
			    
            	AlertDialog alert3 = tAlertu3.create();
			    alert3.show();
			    break;
			    
			    
        	case 3:
        		
        		AlertDialog.Builder tAlertu4 = new AlertDialog.Builder(context);
            	tAlertu4.setTitle("Koła ratunkowe");
            	tAlertu4.setSingleChoiceItems(new String[] {"Telefon do przyjaciela", "Pytanie do publiczności"}, 0, new DialogInterface.OnClickListener() {
            		
            		@Override
    				public void onClick(DialogInterface dialog, int item) {
    				    StanGry.KoloRatunkowe = item;
    				}
            	});
			    
            	tAlertu4.setPositiveButton("OK", new DialogInterface.OnClickListener() {

    				@Override
    				public void onClick(DialogInterface dialog, int id) {
    					
    					if (StanGry.KoloRatunkowe == 0)
    					{
    						String s = KolaRatunkowe.telefonDoPrzyjaciela(StanGry.aktualneOdpowiedzi);
    						AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
    					    tAlertu.setTitle("Przyjaciel mówi:");
    					    tAlertu.setMessage(s);
    					    
    					    tAlertu.setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
    					    	
    					    	@Override
    							public void onClick(DialogInterface dialog, int id) {
    								dialog.cancel();
    								zz.startTimer();
    					    	}
    					    });
    					    
    					    AlertDialog alert = tAlertu.create();
    					    
    					    RadioButton rb;
    					    if(s.compareTo(StanGry.aktualneOdpowiedzi[0].odpowiedz)==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton1);
    							rb.setBackgroundColor(Color.YELLOW);
    						}
    					    if(s.compareTo(StanGry.aktualneOdpowiedzi[1].odpowiedz)==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton2);
    							rb.setBackgroundColor(Color.YELLOW);
    						}
    					    if(s.compareTo(StanGry.aktualneOdpowiedzi[2].odpowiedz)==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton3);
    							rb.setBackgroundColor(Color.YELLOW);
    						}
    					    if(s.compareTo(StanGry.aktualneOdpowiedzi[3].odpowiedz)==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton4);
    							rb.setBackgroundColor(Color.YELLOW);
    						}
    					    
    					    alert.show();
    					    StanGry.wykorzystaneKola[0]=true;
    					    StanGry.KoloRatunkowe=0;
    					    
    					}
    					
    					else
    					{
    						String s = KolaRatunkowe.pytanieDoPublicznosci(StanGry.aktualneOdpowiedzi);
    						AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
    						tAlertu.setTitle("Zdaniem publiczności poprawna odpowiedź to:");
    						tAlertu.setMessage(s);
    						tAlertu.setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
    							
    							@Override
    							public void onClick(DialogInterface dialog, int id) {
    								dialog.cancel();
    								
    							}
    						});
    						
    						AlertDialog alert = tAlertu.create();
    						
    						   RadioButton rb;
    						    if(s.compareTo(StanGry.aktualneOdpowiedzi[0].odpowiedz)==0)
    							{
    								rb = (RadioButton) findViewById(R.id.radioButton1);
    								rb.setBackgroundColor(Color.YELLOW);
    							}
    						    if(s.compareTo(StanGry.aktualneOdpowiedzi[1].odpowiedz)==0)
    							{
    								rb = (RadioButton) findViewById(R.id.radioButton2);
    								rb.setBackgroundColor(Color.YELLOW);
    							}
    						    if(s.compareTo(StanGry.aktualneOdpowiedzi[2].odpowiedz)==0)
    							{
    								rb = (RadioButton) findViewById(R.id.radioButton3);
    								rb.setBackgroundColor(Color.YELLOW);
    							}
    						    if(s.compareTo(StanGry.aktualneOdpowiedzi[3].odpowiedz)==0)
    							{
    								rb = (RadioButton) findViewById(R.id.radioButton4);
    								rb.setBackgroundColor(Color.YELLOW);
    							}
    					    alert.show();
    					    StanGry.wykorzystaneKola[1]=true;
    					    StanGry.KoloRatunkowe=0;
    					}
    				}
            	});
			    
            	AlertDialog alert4 = tAlertu4.create();
			    alert4.show();
			    break;
			    
			    
        	case 4:
        		
        		AlertDialog.Builder tAlertu5 = new AlertDialog.Builder(context);
            	tAlertu5.setTitle("Koła ratunkowe");
            	tAlertu5.setSingleChoiceItems(new String[] {"50/50"}, 0, new DialogInterface.OnClickListener() {
            		
            		@Override
    				public void onClick(DialogInterface dialog, int item) {
    				    StanGry.KoloRatunkowe = item;
    				}
            	});
            	
            	tAlertu5.setPositiveButton("OK", new DialogInterface.OnClickListener() {

    				@Override
    				public void onClick(DialogInterface dialog, int id) {
    					
    					int s = KolaRatunkowe.poprawna(StanGry.aktualneOdpowiedzi);
						int y = 0;
						int o = 0;
						Random rand = new Random();
						do {
							y = rand.nextInt(4);
						} while(y==s);
						
						do {
							o = rand.nextInt(4);
						} while(o==y || o==s);
						
						RadioButton rb;
						
						if(y==0 || o==0)
						{
							rb = (RadioButton) findViewById(R.id.radioButton1);
							rb.setVisibility(View.GONE);
						}
						if(y==1 || o==1)
						{
							rb = (RadioButton) findViewById(R.id.radioButton2);
							rb.setVisibility(View.GONE);
						}
						if(y==2 || o==2)
						{
							rb = (RadioButton) findViewById(R.id.radioButton3);
							rb.setVisibility(View.GONE);
						}
						if(y==3 || o==3)
						{
							rb = (RadioButton) findViewById(R.id.radioButton4);
							rb.setVisibility(View.GONE);
						}
						
						StanGry.wykorzystaneKola[2]=true;
						StanGry.KoloRatunkowe=0;
						zz.startTimer();
    				}
            	});
			    
            	AlertDialog alert5 = tAlertu5.create();
			    alert5.show();
			    break;
            	
            	
        	case 5:
        		
        		AlertDialog.Builder tAlertu6 = new AlertDialog.Builder(context);
            	tAlertu6.setTitle("Koła ratunkowe");
            	tAlertu6.setSingleChoiceItems(new String[] {"Telefon do przyjaciela", "50/50"}, 0, new DialogInterface.OnClickListener() {
            		
            		@Override
    				public void onClick(DialogInterface dialog, int item) {
    				    StanGry.KoloRatunkowe = item;
    				}
            	});
            	
            	tAlertu6.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            		
            		@Override
    				public void onClick(DialogInterface dialog, int id) {
    					
    					if (StanGry.KoloRatunkowe == 0)
    					{
    						String s = KolaRatunkowe.telefonDoPrzyjaciela(StanGry.aktualneOdpowiedzi);
    						AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
    					    tAlertu.setTitle("Przyjaciel mówi:");
    					    tAlertu.setMessage(s);
    					    
    					    tAlertu.setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
    					    	
    					    	@Override
    							public void onClick(DialogInterface dialog, int id) {
    								dialog.cancel();
    								zz.startTimer();
    					    	}
    					    });
    					    
    					    AlertDialog alert = tAlertu.create();
    					    
    					    RadioButton rb;
    					    if(s.compareTo(StanGry.aktualneOdpowiedzi[0].odpowiedz)==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton1);
    							rb.setBackgroundColor(Color.YELLOW);
    						}
    					    if(s.compareTo(StanGry.aktualneOdpowiedzi[1].odpowiedz)==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton2);
    							rb.setBackgroundColor(Color.YELLOW);
    						}
    					    if(s.compareTo(StanGry.aktualneOdpowiedzi[2].odpowiedz)==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton3);
    							rb.setBackgroundColor(Color.YELLOW);
    						}
    					    if(s.compareTo(StanGry.aktualneOdpowiedzi[3].odpowiedz)==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton4);
    							rb.setBackgroundColor(Color.YELLOW);
    						}
    					    
    					    alert.show();
    					    StanGry.wykorzystaneKola[0]=true;
    					    StanGry.KoloRatunkowe=0;
    					    
    					}
    					else
    					{
    						
    						int s = KolaRatunkowe.poprawna(StanGry.aktualneOdpowiedzi);
    						int y = 0;
    						int o = 0;
    						Random rand = new Random();
    						do {
    							y = rand.nextInt(4);
    						} while(y==s);
    						
    						do {
    							o = rand.nextInt(4);
    						} while(o==y || o==s);
    						
    						RadioButton rb;
    						
    						if(y==0 || o==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton1);
    							rb.setVisibility(View.GONE);
    						}
    						if(y==1 || o==1)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton2);
    							rb.setVisibility(View.GONE);
    						}
    						if(y==2 || o==2)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton3);
    							rb.setVisibility(View.GONE);
    						}
    						if(y==3 || o==3)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton4);
    							rb.setVisibility(View.GONE);
    						}
    						
    						StanGry.wykorzystaneKola[2]=true;
    						StanGry.KoloRatunkowe=0;
    						zz.startTimer();
    					}
            	}
        	});
			    
            	AlertDialog alert6 = tAlertu6.create();
			    alert6.show();
			    break;
       
        	
        	case 6:
        		
        		AlertDialog.Builder tAlertu7 = new AlertDialog.Builder(context);
            	tAlertu7.setTitle("Koła ratunkowe");
            	tAlertu7.setSingleChoiceItems(new String[] {"Pytanie do publiczności", "50/50"}, 0, new DialogInterface.OnClickListener() {
            		
            		@Override
    				public void onClick(DialogInterface dialog, int item) {
    				    StanGry.KoloRatunkowe = item;
    				}
            	});
            	
            	tAlertu7.setPositiveButton("OK", new DialogInterface.OnClickListener() {

    				@Override
    				public void onClick(DialogInterface dialog, int id) {
    					
    				if(StanGry.KoloRatunkowe == 0)
    				{
    				String s = KolaRatunkowe.pytanieDoPublicznosci(StanGry.aktualneOdpowiedzi);
						AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
						tAlertu.setTitle("Zdaniem publiczności poprawna odpowiedź to:");
						tAlertu.setMessage(s);
						tAlertu.setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
								zz.startTimer();
								
							}
						});
						
						AlertDialog alert = tAlertu.create();
						
						   RadioButton rb;
						    if(s.compareTo(StanGry.aktualneOdpowiedzi[0].odpowiedz)==0)
							{
								rb = (RadioButton) findViewById(R.id.radioButton1);
								rb.setBackgroundColor(Color.YELLOW);
							}
						    if(s.compareTo(StanGry.aktualneOdpowiedzi[1].odpowiedz)==0)
							{
								rb = (RadioButton) findViewById(R.id.radioButton2);
								rb.setBackgroundColor(Color.YELLOW);
							}
						    if(s.compareTo(StanGry.aktualneOdpowiedzi[2].odpowiedz)==0)
							{
								rb = (RadioButton) findViewById(R.id.radioButton3);
								rb.setBackgroundColor(Color.YELLOW);
							}
						    if(s.compareTo(StanGry.aktualneOdpowiedzi[3].odpowiedz)==0)
							{
								rb = (RadioButton) findViewById(R.id.radioButton4);
								rb.setBackgroundColor(Color.YELLOW);
							}
					    alert.show();
					    StanGry.wykorzystaneKola[1]=true;
					    StanGry.KoloRatunkowe=0;
    				}
    				else
    				{
    					int s = KolaRatunkowe.poprawna(StanGry.aktualneOdpowiedzi);
						int y = 0;
						int o = 0;
						Random rand = new Random();
						do {
							y = rand.nextInt(4);
						} while(y==s);
						
						do {
							o = rand.nextInt(4);
						} while(o==y || o==s);
						
						RadioButton rb;
						
						if(y==0 || o==0)
						{
							rb = (RadioButton) findViewById(R.id.radioButton1);
							rb.setVisibility(View.GONE);
						}
						if(y==1 || o==1)
						{
							rb = (RadioButton) findViewById(R.id.radioButton2);
							rb.setVisibility(View.GONE);
						}
						if(y==2 || o==2)
						{
							rb = (RadioButton) findViewById(R.id.radioButton3);
							rb.setVisibility(View.GONE);
						}
						if(y==3 || o==3)
						{
							rb = (RadioButton) findViewById(R.id.radioButton4);
							rb.setVisibility(View.GONE);
						}
						
						StanGry.wykorzystaneKola[2]=true;
						StanGry.KoloRatunkowe=0;
						zz.startTimer();
    					
    				}
    				
    				}
            	});
			    
            	AlertDialog alert7 = tAlertu7.create();
			    alert7.show();
			    break;
			    
			    
        	case 7:
        		
        		AlertDialog.Builder tAlertu8 = new AlertDialog.Builder(context);
            	tAlertu8.setTitle("Koła ratunkowe");
            	tAlertu8.setSingleChoiceItems(new String[] {"Telefon do przyjaciela", "Pytanie do publiczności", "50/50" }, 0, new DialogInterface.OnClickListener() {
    				
            		@Override
    				public void onClick(DialogInterface dialog, int item) {
    				    StanGry.KoloRatunkowe = item;
    				}
    			    });
            	
            	tAlertu8.setPositiveButton("OK", new DialogInterface.OnClickListener() {

    				@Override
    				public void onClick(DialogInterface dialog, int id) {
    					
    					if (StanGry.KoloRatunkowe == 0)
    					{
    						String s = KolaRatunkowe.telefonDoPrzyjaciela(StanGry.aktualneOdpowiedzi);
    						AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
    					    tAlertu.setTitle("Przyjaciel mówi:");
    					    tAlertu.setMessage(s);
    					    
    					    tAlertu.setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
    					    	
    					    	@Override
    							public void onClick(DialogInterface dialog, int id) {
    								dialog.cancel();
    								zz.startTimer();
    								
    					    	}
    					    });
    					    
    					    AlertDialog alert = tAlertu.create();
    					    
    					    RadioButton rb;
    					    if(s.compareTo(StanGry.aktualneOdpowiedzi[0].odpowiedz)==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton1);
    							rb.setBackgroundColor(Color.YELLOW);
    						}
    					    if(s.compareTo(StanGry.aktualneOdpowiedzi[1].odpowiedz)==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton2);
    							rb.setBackgroundColor(Color.YELLOW);
    						}
    					    if(s.compareTo(StanGry.aktualneOdpowiedzi[2].odpowiedz)==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton3);
    							rb.setBackgroundColor(Color.YELLOW);
    						}
    					    if(s.compareTo(StanGry.aktualneOdpowiedzi[3].odpowiedz)==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton4);
    							rb.setBackgroundColor(Color.YELLOW);
    						}
    					    
    					    alert.show();
    					    StanGry.wykorzystaneKola[0]=true;
    					    StanGry.KoloRatunkowe=0;
    					}
    					
    					else if (StanGry.KoloRatunkowe == 1)
    					{
    						String s = KolaRatunkowe.pytanieDoPublicznosci(StanGry.aktualneOdpowiedzi);
    						AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
    						tAlertu.setTitle("Zdaniem publiczności poprawna odpowiedź to:");
    						tAlertu.setMessage(s);
    						tAlertu.setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
    							
    							@Override
    							public void onClick(DialogInterface dialog, int id) {
    								dialog.cancel();
    								zz.startTimer();
    								
    							}
    						});
    						
    						AlertDialog alert = tAlertu.create();
    						
    						   RadioButton rb;
    						    if(s.compareTo(StanGry.aktualneOdpowiedzi[0].odpowiedz)==0)
    							{
    								rb = (RadioButton) findViewById(R.id.radioButton1);
    								rb.setBackgroundColor(Color.YELLOW);
    							}
    						    if(s.compareTo(StanGry.aktualneOdpowiedzi[1].odpowiedz)==0)
    							{
    								rb = (RadioButton) findViewById(R.id.radioButton2);
    								rb.setBackgroundColor(Color.YELLOW);
    							}
    						    if(s.compareTo(StanGry.aktualneOdpowiedzi[2].odpowiedz)==0)
    							{
    								rb = (RadioButton) findViewById(R.id.radioButton3);
    								rb.setBackgroundColor(Color.YELLOW);
    							}
    						    if(s.compareTo(StanGry.aktualneOdpowiedzi[3].odpowiedz)==0)
    							{
    								rb = (RadioButton) findViewById(R.id.radioButton4);
    								rb.setBackgroundColor(Color.YELLOW);
    							}
    					    alert.show();
    					    StanGry.wykorzystaneKola[1]=true;
    					    StanGry.KoloRatunkowe=0;
    					}
    					else
    					{
    						int s = KolaRatunkowe.poprawna(StanGry.aktualneOdpowiedzi);
    						int y = 0;
    						int o = 0;
    						Random rand = new Random();
    						do {
    							y = rand.nextInt(4);
    						} while(y==s);
    						
    						do {
    							o = rand.nextInt(4);
    						} while(o==y || o==s);
    						
    						RadioButton rb;
    						
    						if(y==0 || o==0)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton1);
    							rb.setVisibility(View.GONE);
    						}
    						if(y==1 || o==1)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton2);
    							rb.setVisibility(View.GONE);
    						}
    						if(y==2 || o==2)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton3);
    							rb.setVisibility(View.GONE);
    						}
    						if(y==3 || o==3)
    						{
    							rb = (RadioButton) findViewById(R.id.radioButton4);
    							rb.setVisibility(View.GONE);
    						}
    						
    						StanGry.wykorzystaneKola[2]=true;
    						StanGry.KoloRatunkowe=0;
    						zz.startTimer();
    					}
    					
    					
    				}
    				
    				
            		
            		
            	});
            	
            	AlertDialog alert8 = tAlertu8.create();
    		    alert8.show();
    		    break;
        	
        	}
   
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    pomocnik.close();
	}

}
