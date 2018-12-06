package com.example.sampleapp.dbhelper.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sampleapp.dbhelper.DatabaseHelper;
import com.example.sampleapp.modal.Cart;

import java.util.ArrayList;
import java.util.List;


/**
 * Perform all the functions such ass insert, update, delete and retrieve from SQLLite database
 * <p>
 * Created by Vihanga on 27/8/2018.
 */

public class CartDao {

	private static final String TAG = CartDao.class.getSimpleName();

	/**
	 * Delete all cart items from the database
	 *
	 * @return true if all deleted, false if any error occurence
	 */
	public boolean deleteAll() {
		SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
		return db.isOpen() && db.delete(Cart.TABLE_NAME, null, null) > 0;
	}

	/**
	 * Add cart items to local SQLLite database
	 *
	 * @param product selected prduct
	 * @param qty     quantity of the product
	 * @return (- 1 if fail), id of the added item if success
	 */
//	public long addItem(Product product, int qty) {
//		if (product.getId() != 0) {
//			ContentValues values = new ContentValues();
//			SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
//			values.put(Cart.COLUMN_ITEM_ID, product.getId());
//			values.put(Cart.COLUMN_NAME, product.getName());
//			values.put(Cart.COLUMN_URL, product.getUrl());
//			values.put(Cart.COLUMN_QTY, qty);
//			values.put(Cart.COLUMN_QTY_AVAILABLE, product.getQuantity());
//			values.put(Cart.COLUMN_PRICE, product.getAmount());
//			String dateTime = String.valueOf(new Date().getTime());
//			values.put(Cart.COLUMN_ADDED_TIME, dateTime);
//			values.put(Cart.COLUMN_UPDATED_TIME, dateTime);
//			long insert = -1;
//			if (db.isOpen()) {
//				insert = db.insert(Cart.TABLE_NAME, null, values);
//				Log.i(TAG, "item added - " + product.getName());
//			}
//			return insert;
//
//		}
//		return -1;
//	}

	/**
	 * check whether an item is existing on the DB already
	 *
	 * @param prodID product id
	 * @return null if item do not exist, cart item if exist
	 */
	public Cart checkItem(int prodID) {
		Cart cart = null;

		SQLiteDatabase db = DatabaseHelper.getInstance().getReadableDatabase();
		if (db.isOpen()) {
			String query = "SELECT * FROM " + Cart.TABLE_NAME +
					" WHERE " + Cart.COLUMN_ITEM_ID + "=\"" + prodID + "\""
					+ " limit 1";
			Cursor c = db.rawQuery(query, null);
			if (!c.isClosed()) {
				if (c.moveToFirst()) {
					cart = new Cart(
							c.getInt(c.getColumnIndex(Cart.COLUMN_ID)),
							c.getInt(c.getColumnIndex(Cart.COLUMN_ITEM_ID)),
							c.getInt(c.getColumnIndex(Cart.COLUMN_QTY)),
							c.getString(c.getColumnIndex(Cart.COLUMN_NAME)),
							c.getString(c.getColumnIndex(Cart.COLUMN_URL)),
							c.getDouble(c.getColumnIndex(Cart.COLUMN_PRICE)),
							c.getString(c.getColumnIndex(Cart.COLUMN_ADDED_TIME)),
							c.getString(c.getColumnIndex(Cart.COLUMN_UPDATED_TIME)),
							c.getInt(c.getColumnIndex(Cart.COLUMN_QTY_AVAILABLE)));
				}
				c.close();
			}
		}
		return cart;
	}

	/**
	 * Get all cart items that are added to the list
	 *
	 * @return cart items
	 */
	public List<Cart> getAllItem() {
		List<Cart> items = new ArrayList<>();

		SQLiteDatabase db = DatabaseHelper.getInstance().getReadableDatabase();
		if (db.isOpen()) {
			String query = "SELECT * FROM " + Cart.TABLE_NAME
					+ " ORDER BY " + Cart.COLUMN_ID + " ASC";
			Cursor c = db.rawQuery(query, null);
			if (!c.isClosed()) {
				while (c.moveToNext()) {
					items.add(new Cart(
							c.getInt(c.getColumnIndex(Cart.COLUMN_ID)),
							c.getInt(c.getColumnIndex(Cart.COLUMN_ITEM_ID)),
							c.getInt(c.getColumnIndex(Cart.COLUMN_QTY)),
							c.getString(c.getColumnIndex(Cart.COLUMN_NAME)),
							c.getString(c.getColumnIndex(Cart.COLUMN_URL)),
							c.getDouble(c.getColumnIndex(Cart.COLUMN_PRICE)),
							c.getString(c.getColumnIndex(Cart.COLUMN_ADDED_TIME)),
							c.getString(c.getColumnIndex(Cart.COLUMN_UPDATED_TIME)),
							c.getInt(c.getColumnIndex(Cart.COLUMN_QTY_AVAILABLE))));
				}
				c.close();
			}
		}
		return items;
	}

	/**
	 * Check whether the cart item exist.
	 *
	 * @return true - exist, false - not exist
	 */
	public boolean hasItems() {
		boolean hasData = false;

		SQLiteDatabase db = DatabaseHelper.getInstance().getReadableDatabase();
		if (db.isOpen()) {
			String query = "SELECT * FROM " + Cart.TABLE_NAME
					+ " ORDER BY " + Cart.COLUMN_ID + " ASC";
			Cursor c = db.rawQuery(query, null);
			if (!c.isClosed()) {
				hasData = c.moveToNext();
				c.close();
			}
		}
		return hasData;
	}

	/**
	 * Remove a selected item from the cart
	 *
	 * @param prodID product id
	 * @return true is deleted
	 */
	public boolean deleteItem(int prodID) {
		SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
		boolean b = db.delete(Cart.TABLE_NAME, Cart.COLUMN_ITEM_ID + "=" + prodID, null) > 0;
		db.close();
		return b;
	}

	/**
	 * Update product quantity in the cart
	 *
	 * @param id      cart item id (auto generated id)
	 * @param product product
	 * @param newQty  new quantity
	 * @return
	 */
//	public int updateProduct(int id, Product product, int newQty) {
//		SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
//		//
//		ContentValues values = new ContentValues();
//		values.put(Cart.COLUMN_QTY, newQty);
//		if (product != null) {
//			values.put(Cart.COLUMN_PRICE, product.getAmount());
//			values.put(Cart.COLUMN_QTY_AVAILABLE, product.getQuantity());
//		}
//		values.put(Cart.COLUMN_UPDATED_TIME, String.valueOf(new Date().getTime()));
//
//		String selection = Cart.COLUMN_ID + " = ?";
//		String[] selectionArgs = {String.valueOf(id)};
//
//		return db.update(
//				Cart.TABLE_NAME,
//				values,
//				selection,
//				selectionArgs);
//	}
	/**
	 * Update product quantity in the cart
	 *
	 * @param product product
	 * @return
	 */
//	public int updateProduct( Product product) {
//		SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
//		//
//		ContentValues values = new ContentValues();
// 		if (product != null) {
//			values.put(Cart.COLUMN_PRICE, product.getAmount());
//			values.put(Cart.COLUMN_QTY_AVAILABLE, product.getQuantity());
//		} else return -1;
//		values.put(Cart.COLUMN_UPDATED_TIME, String.valueOf(new Date().getTime()));
//
//		String selection = Cart.COLUMN_ITEM_ID + " = ?";
//		String[] selectionArgs = {String.valueOf(product.getId())};
//
//		return db.update(
//				Cart.TABLE_NAME,
//				values,
//				selection,
//				selectionArgs);
//	}
}
