package com.example.sampleapp.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import com.example.sampleapp.R;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class UtilityManager {

	public static void showSnackBarWithAction(String msg, int duration, String actionMsg,
	                                          CoordinatorLayout coordinatorLayout, View.OnClickListener listener) {
		Snackbar snackbar = Snackbar
				.make(coordinatorLayout, msg, duration)
				.setAction(actionMsg, listener);
		snackbar.show();
	}

	public static AlertDialog showAlert(final Context context, String title, String msg, String btnPositive,
	                                    String btnNegative, boolean cancelable,
	                                    DialogInterface.OnClickListener positiveClick,
	                                    DialogInterface.OnClickListener negativeClick) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogStyle);
		builder.setMessage(msg);
		if (!TextUtils.isEmpty(title))
			builder.setTitle(title);
		builder.setCancelable(cancelable);
		builder.setPositiveButton(btnPositive, positiveClick);
		builder.setNegativeButton(btnNegative, negativeClick);
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
		//call after showing dialog
		if (positiveClick != null)
			alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
		if (negativeClick != null)
			alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context, R.color.colorBlack));

		return alertDialog;
	}

	public static void showSnack(String msg, int duration, CoordinatorLayout coordinatorLayout) {
		Snackbar.make(coordinatorLayout, msg, duration).show();
	}

	public static ProgressDialog showProgressAlert(final Context context, String msg) {
		ProgressDialog builder = new ProgressDialog(context, R.style.DialogStyle);
		builder.setMessage(msg);
		builder.setCancelable(false);
		builder.show();
		return builder;
	}

	public static ObjectMapper getDefaultObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(getDefaultSimpleDateFormat());
		return objectMapper;
	}

	private static SimpleDateFormat getDefaultSimpleDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", java.util.Locale.getDefault());
	}

}
