package com.example.sampleapp.util;

import android.text.TextUtils;
import android.util.Patterns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Validation {

	public static boolean isValidEmail(CharSequence target) {
		return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
	}

	public static boolean isValidNumber(String number) {
		return (!TextUtils.isEmpty(number) && number.length() > 9);
	}

	public static boolean isValidDOB(String dob) {
		if (TextUtils.isEmpty(dob)) return false;

		try {
			Date d = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(dob);

			Calendar today = Calendar.getInstance();
			today.add(Calendar.DAY_OF_MONTH, -1); //y'day

			return d.before(new Date(today.getTimeInMillis())); // check whether select date is before y'day
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
}
