package com.example.milionerzy;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
		    AlertDialog.Builder tAlertu = new AlertDialog.Builder(context);
		    tAlertu.setTitle("Podaj nick");
		    
		    final EditText input = new EditText(context);
		    tAlertu.setView(input);
		    
		    tAlertu.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener() {
			    
		    	@Override
				public void onClick(DialogInterface dialog, int id) {
				StanGry.nazwaUzytkownika = input.getText().toString();
				String powitanie = "Witaj "+ StanGry.nazwaUzytkownika + "!";
				StanGry.zeruj();
				
				Toast.makeText(getApplicationContext(), powitanie, Toast.LENGTH_LONG).show();
				
				AlertDialog.Builder tAlertu2 = new AlertDialog.Builder(context);
				tAlertu2.setTitle("Z jakiej kategorii chcesz odpowiadać?");
				tAlertu2.setSingleChoiceItems(StanGry.kategorie, 0, new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int item) {
					    StanGry.kategoria = item;
					}
				    });
				
				
				tAlertu2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					    public void onClick(DialogInterface dialog, int id) {
					    Toast.makeText(context,StanGry.kategorie[StanGry.kategoria],Toast.LENGTH_SHORT).show();
					    
					    AlertDialog.Builder tAlertu3 = new AlertDialog.Builder(context);
					    tAlertu3.setTitle("Chcesz grać na czas?");
					    
					    tAlertu3.setSingleChoiceItems(StanGry.czas,0,new DialogInterface.OnClickListener() {
						    
						    public void onClick(DialogInterface dialog, int item) {
							StanGry.wybor = item;
						    }
						});
					    
					    
					    tAlertu3.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener() {
						    
						    @Override
							public void onClick(DialogInterface dialog, int id) {
							
							Toast.makeText(context,StanGry.czas[StanGry.wybor],Toast.LENGTH_SHORT).show();
							
							if(StanGry.wybor==0)
							    {
								
								AlertDialog.Builder tAlertu4 = new AlertDialog.Builder(context);
								
								tAlertu4.setMessage("Pamiętaj, że im szybszy czas odpowiedzi tym więcej punktów do zdobycia")
								    .setCancelable(false)
								    
								    .setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
									    
									    public void onClick(DialogInterface dialog, int id) {
										final Intent mainIntent = new Intent(Milionerzy.this, StartGry.class);
										Milionerzy.this.startActivity(mainIntent);
									    }
									});
								
								
								AlertDialog alert = tAlertu4.create();
								alert.show();
							    }
							
							else
							    {
								
								final Intent mainIntent = new Intent(Milionerzy.this, StartGry.class);
								Milionerzy.this.startActivity(mainIntent);
								
							    }
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
				    });
				
				
				tAlertu2.setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog,int id) {
					    dialog.cancel();
					}
				    });
				
				
				AlertDialog alert = tAlertu2.create();
				alert.show();
				dialog.cancel();
			    }
			});
		    
		    
		    tAlertu.setNegativeButton(R.string.wroc, new DialogInterface.OnClickListener() {
			    
			    public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			    }
			});
		    
		    
		    AlertDialog alert = tAlertu.create();
		    alert.show();
		}
	    });
	
	
	Button button2 = (Button) findViewById(R.id.button2);
	button2.setOnClickListener(new OnClickListener() {
		
		@Override
		    public void onClick(View arg0) {
		    final Intent mainIntent = new Intent(Milionerzy.this, Pomoc.class);
		    Milionerzy.this.startActivity(mainIntent);
		}
	    });
	
	
	Button button3 = (Button) findViewById(R.id.button3);
	final Context context0 = this;
	
	button3.setOnClickListener(new OnClickListener() {
		
		@Override
		    public void onClick(View arg0) {
		    
		    AlertDialog.Builder tAlertu = new AlertDialog.Builder(context0);
		    tAlertu.setMessage("\nMilionerzy\n\nv1.0\n\nautor: wiola")
			.setCancelable(false)
			
			.setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int id) {
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
			
			.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int id) {
				    Milionerzy.this.finish();
				}
			    })
			
			
			.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int id) {
				    dialog.cancel();
				}
			    });
		    
		    
		    AlertDialog alert = tAlertu.create();
		    alert.show();
		}
	    });
	
	Button button5 = (Button) findViewById(R.id.button5);
	button5.setOnClickListener(new OnClickListener() {
		
		@Override
		    public void onClick(View arg0) {
		    final Intent mainIntent = new Intent(Milionerzy.this, ZapisaneGry.class);
		    Milionerzy.this.startActivity(mainIntent);
		}
	    });
    }
    
    
    public static final int MENU_ADD = Menu.FIRST;
	public static final int MENU_DELETE = Menu.FIRST + 1;
	
	final Context context = this;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    super.onCreateOptionsMenu(menu);

	    menu.add(Menu.NONE, MENU_ADD, Menu.NONE, "Najlepsze Wyniki");
	    return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
        	case MENU_ADD:
        		final Intent mainIntent = new Intent(Milionerzy.this, NajlepszeWyniki.class);
    		    Milionerzy.this.startActivity(mainIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}