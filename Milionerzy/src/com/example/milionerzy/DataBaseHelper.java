package com.example.milionerzy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract.Columns;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {

	private static String DB_PATH = "/data/data/com.example.milionerzy/databases/";

	private static String DB_NAME = "baza.sqlite";

	private SQLiteDatabase myDataBase;

	private final Context myContext;


	public DataBaseHelper(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {

		} else {

			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Błąd podczas kopiowania bazy");

			}
		}

	}


	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	private void copyDataBase() throws IOException {

		InputStream myInput = myContext.getAssets().open(DB_NAME);

		String outFileName = DB_PATH + DB_NAME;

		OutputStream myOutput = new FileOutputStream(outFileName);

		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	SQLiteDatabase zwroc() {
		return myDataBase;
	}

	void zwrocPytanie() {
		
		
		Cursor cursor = getReadableDatabase().rawQuery(
				"SELECT *, _id FROM Pytania", null);
		Cursor cursor2 = getReadableDatabase().rawQuery(
				"SELECT *, _id FROM Odpowiedzi", null);
		
		//Pyt[] pytania = new Pyt[60];
		//Odp[] odp = new Odp[240];
		
		for (int i = 0; cursor.moveToNext(); ++i) {
			StanGry.pytania[i] = new Pyt(cursor.getString(cursor.getColumnIndex("idPytanie")), cursor.getInt(cursor.getColumnIndex("IDKategori")), cursor.getString(cursor.getColumnIndex("Pytanie")));
		}
		
		for (int i = 0; cursor2.moveToNext(); ++i) {
			StanGry.odp[i] = new Odp(cursor2.getString(cursor2.getColumnIndex("IDPytanie")), cursor2.getString(cursor2.getColumnIndex("Odpowiedzi")), cursor2.getInt(cursor2.getColumnIndex("Prawda")));
		}
		
		cursor.close();
		cursor2.close();
		
		/*Odp[] tmp = new Odp[4];
		int k;
		
		for(int i=0; i<60; ++i) {
			k=0;
			for(int j=0; j<240; ++j)
			{
				//Log.e("CHUJ", Integer.toString(pytania[i]._id)+" "+Integer.toString(odp[j].idPytania));
					if(pytania[i]._id==odp[j].idPytania)
				{
					tmp[k]=odp[j];
					++k;
					if(k==4)
					{
						break;
					}
				}
			}
			//Log.e("CHUJ", pytania[i].pytanie+" "+tmp[0].odpowiedz+" "+tmp[1].odpowiedz);
			StanGry.pytania[i] = new PytaniaIOdpowiedzi(pytania[i], tmp);
		}*/
	}
	
	void usun(String tabela, String kolumna, String warunek) {
		//try {
			//getWritableDatabase().delete(tabela, kolumna, warunek);
		getWritableDatabase().execSQL("DELETE FROM "+tabela+" WHERE nr = "+ warunek);
		//getWritableDatabase().rawQuery("DELETE FROM "+tabela+" WHERE nr = ", warunek);
		//}
		//catch(SQLiteException e)
		//{
		//	Log.e("CHUJ", "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		//}
	}
	
	void usun(String tabela) {
		//try {
			//getWritableDatabase().delete(tabela, kolumna, warunek);
		getWritableDatabase().delete(tabela, null, null);
		//getWritableDatabase().rawQuery("DELETE FROM "+tabela+" WHERE nr = ", warunek);
		//}
		//catch(SQLiteException e)
		//{
		//	Log.e("CHUJ", "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		//}
	}
	
	void wstaw(String tabela, ContentValues wartosc) {
		getWritableDatabase().insert(tabela, null, wartosc);
	}
	
	ContentValues gry(int nr) {
		ContentValues cv = new ContentValues();
		cv.put("Kategoria", StanGry.kategoria);
		cv.put("Czas", StanGry.wybor);
		cv.put("Gracz", StanGry.nazwaUzytkownika);
		cv.put("Wynik", StanGry.punkty);
		cv.put("nr", nr);
		cv.put("iloscPytan", StanGry.iloscPytan);
		if(StanGry.wykorzystaneKola[0]==false)
		{
			cv.put("Przyjaciel", 0);
		}
		else
		{
			cv.put("Przyjaciel", 1);
		}
		if(StanGry.wykorzystaneKola[1]==false)
		{
			cv.put("Publicznosc", 0);
		}
		else
		{
			cv.put("Publicznosc", 1);
		}
		if(StanGry.wykorzystaneKola[2]==false)
		{
			cv.put("P50", 0);
		}
		else
		{
			cv.put("P50", 1);
		}
		
		return cv;
	}
	
	void nieaktualnePytania(int nr) {
		ContentValues cv = new ContentValues();
		for(int i=0; i<15; ++i)
		{
			if(StanGry.nieaktualnePytania[i]==-1) {
				break;
			}
			else
			{
				cv.clear();
				cv.put("nr", StanGry.nieaktualnePytania[i]);
				wstaw("zapis"+Integer.toString(nr), cv);
			}
		}
	}
	
void wczytaj(int nr) {
		
		
		Cursor cursor = getReadableDatabase().rawQuery(
				"SELECT * FROM Gry WHERE nr=?", new String[] {Integer.toString(nr)});
		cursor.moveToNext();
		Log.e("CHUJ", Integer.toString(nr));
		StanGry.iloscPytan=cursor.getInt(cursor.getColumnIndex("iloscPytan"));
		StanGry.kategoria=cursor.getInt(cursor.getColumnIndex("Kategoria"));
		StanGry.wybor=cursor.getInt(cursor.getColumnIndex("Czas"));
		StanGry.nazwaUzytkownika=cursor.getString(cursor.getColumnIndex("Gracz"));
		StanGry.punkty=cursor.getInt(cursor.getColumnIndex("Wynik"));
		if(cursor.getInt(cursor.getColumnIndex("Przyjaciel"))==0)
		{
			StanGry.wykorzystaneKola[0]=false;
		}
		else
		{
			StanGry.wykorzystaneKola[0]=true;
		}
		if(cursor.getInt(cursor.getColumnIndex("Publicznosc"))==0)
		{
			StanGry.wykorzystaneKola[1]=false;
		}
		else
		{
			StanGry.wykorzystaneKola[1]=true;
		}
		if(cursor.getInt(cursor.getColumnIndex("P50"))==0)
		{
			StanGry.wykorzystaneKola[2]=false;
		}
		else
		{
			StanGry.wykorzystaneKola[2]=true;
		}
		
}

String[][] wczytajNajlepszeWyniki() {
	Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM Scores", null);
	
	String[][] wynik = new String[3][2];
	for(int i=0; i<3; ++i)
	{
		cursor.moveToNext();
		wynik[i][0]=cursor.getString(cursor.getColumnIndex("NazwaUzytkownika"));
		wynik[i][1]=cursor.getString(cursor.getColumnIndex("Wynik"));
	}
	
	return wynik;
}

}
