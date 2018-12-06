package com.example.sampleapp.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart implements Serializable {

	public static final String TABLE_NAME = "cart";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_ITEM_ID = "item_id";
	public static final String COLUMN_NAME = "item_name";
	public static final String COLUMN_URL = "item_image";
	public static final String COLUMN_QTY = "qty";
	public static final String COLUMN_QTY_AVAILABLE = "available_qty";
	public static final String COLUMN_PRICE = "price";
	public static final String COLUMN_ADDED_TIME = "added_time";
	public static final String COLUMN_UPDATED_TIME = "updated_time";

	private int id;
	private int itemID;
	private int qty;
	private String name;
	private String url;
	private double price;
	private long addedTime;
	private long updatededTime;
	private int availableQty;

	private boolean outOfStock;

	public Cart() {
	}

	public Cart(int id, int itemID, int qty, String name, String url, double price, String addedTime, String updatededTime, int availableQty) {
		this.id = id;
		this.itemID = itemID;
		this.qty = qty;
		this.name = name;
		this.url = url;
		this.price = price;
		this.addedTime = Long.parseLong(addedTime);
		this.updatededTime = Long.parseLong(updatededTime);
		this.availableQty = availableQty;
	}

	public boolean isOutOfStock() {
		return availableQty < qty;
	}

	public void setOutOfStock(boolean outOfStock) {
		this.outOfStock = outOfStock;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public long getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(long addedTime) {
		this.addedTime = addedTime;
	}

	public long getUpdatededTime() {
		return updatededTime;
	}

	public void setUpdatededTime(long updatededTime) {
		this.updatededTime = updatededTime;
	}

	public int getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
