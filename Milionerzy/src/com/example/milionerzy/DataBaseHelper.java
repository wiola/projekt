package com.example.milionerzy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.RadioButton;

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
				"SELECT Pytanie, _id FROM Pytania WHERE IDKategori = ?",
				new String[] { Integer.toString(StanGry.kategoria) });
		Cursor cursor2;
		Odpowiedz[] odpowiedzi = new Odpowiedz[4];

		for (int i = 0; cursor.moveToNext(); ++i) {
			cursor2 = getReadableDatabase()
					.rawQuery(
							"SELECT Prawda, Odpowiedzi FROM Odpowiedzi WHERE IDPytanie = ?",
							new String[] { cursor.getString(cursor
									.getColumnIndex("_id")) });
			for (int j = 0; cursor2.moveToNext(); ++j) {
				odpowiedzi[j] = new Odpowiedz(cursor2.getString(cursor2
						.getColumnIndex("Odpowiedzi")),
						Integer.parseInt(cursor2.getString(cursor2
								.getColumnIndex("Prawda"))));
			}
			StanGry.pytania[i] = new Pytanie(cursor.getString(cursor
					.getColumnIndex("Pytanie")), odpowiedzi);
		}
	}

}
