package com.example.sampleapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.sampleapp.ExampleApplication;
import com.example.sampleapp.listener.NetworkListener;

import java.util.List;

/**
 * Network change broadcast receiver is used to register activities and fragments and notify them with
 * the network availability.
 * This will trigger whenever there is a network loss or when the mobile regained the network
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			boolean online = isOnline(context);
			List<NetworkListener> networkListeners = ExampleApplication.getInstance().getNetworkListeners();
			for (NetworkListener listener : networkListeners) {
				listener.onNetworkChange(online);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check whether the broadcast is triggered for network loss or gain.
	 *
	 * @param context current app context
	 * @return online/offline
	 */
	private boolean isOnline(Context context) {
		try {
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getActiveNetworkInfo();
			return (netInfo != null && netInfo.isConnected());
		} catch (NullPointerException e) {
			e.printStackTrace();
			return false;
		}
	}
}