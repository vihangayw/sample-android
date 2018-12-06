package com.example.sampleapp.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.example.sampleapp.listener.NetworkListener;
import com.example.sampleapp.receiver.NetworkChangeReceiver;
import com.example.sampleapp.util.Constants;


/**
 * When the app's NetworkConnectionActivity is created, it starts this service. This is so that the
 * activity and this service can communicate back and forth. See "setUiCallback()"
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetworkSchedulerService extends JobService implements
		NetworkListener {

	private static final String TAG = NetworkSchedulerService.class.getSimpleName();

	private BroadcastReceiver mConnectivityReceiver;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "Service created");
		mConnectivityReceiver = new NetworkChangeReceiver();
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand");
		return START_NOT_STICKY;
	}


	@Override
	public boolean onStartJob(JobParameters params) {
		Log.i(TAG, "onStartJob" + mConnectivityReceiver);
		registerReceiver(mConnectivityReceiver, new IntentFilter(Constants.CONNECTIVITY_ACTION));
		return true;
	}

	@Override
	public boolean onStopJob(JobParameters params) {
		Log.i(TAG, "onStopJob");
		unregisterReceiver(mConnectivityReceiver);
		return true;
	}

	@Override
	public void onNetworkChange(boolean connected) {
		String message = connected ? "Good! Connected to Internet" : "Sorry! Not connected to internet";
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

	}
}