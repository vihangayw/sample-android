package com.example.sampleapp.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sampleapp.BuildConfig;
import com.example.sampleapp.ExampleApplication;
import com.example.sampleapp.modal.Cart;

/**
 * SQLLite database is created and updated here,
 * this is a singleton class which return a global instance of database handler through out the entire app
 * Maintain the version number in a proper document.
 * Make sure to add all the version conflicts for the onUpgrade method
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = BuildConfig.APPLICATION_ID + ".db";
	private static final int DATABASE_VERSION = 1;

	private static DatabaseHelper instance;

	private DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Get an instance of the db helper
	 *
	 * @return DatabaseHelper instance
	 */
	public static synchronized DatabaseHelper getInstance() {
		if (instance == null) {
			instance = new DatabaseHelper(ExampleApplication.getInstance().getApplicationContext());
		}
		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/*cart table*/
		String tableCart = "CREATE TABLE " + Cart.TABLE_NAME + "("
				+ Cart.COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL,"
				+ Cart.COLUMN_ITEM_ID + " INTEGER,"
				+ Cart.COLUMN_QTY + " INTEGER,"
				+ Cart.COLUMN_QTY_AVAILABLE + " INTEGER,"
				+ Cart.COLUMN_NAME + " TEXT,"
				+ Cart.COLUMN_URL + " TEXT,"
				+ Cart.COLUMN_ADDED_TIME + " TEXT,"
				+ Cart.COLUMN_UPDATED_TIME + " TEXT,"
				+ Cart.COLUMN_PRICE + " REAL"
				+ ");";

		db.execSQL(tableCart);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*
		since its the version 1, i will drop the table on upgrade,
		please manage the version conflicts here
		*/
		db.execSQL(" DROP TABLE IF EXISTS " + Cart.TABLE_NAME);
	}
}