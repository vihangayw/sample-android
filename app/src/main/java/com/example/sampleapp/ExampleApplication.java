package com.example.sampleapp;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.sampleapp.api.response.Error;
import com.example.sampleapp.listener.NetworkListener;

import java.util.ArrayList;
import java.util.List;

public class ExampleApplication extends MultiDexApplication {
	private static final String TAG = ExampleApplication.class.getSimpleName();
	private static ExampleApplication application;
	private final List<NetworkListener> networkListeners = new ArrayList<>();

	public static ExampleApplication getInstance() {
		return application;
	}

	/**
	 * Called when the application is starting, before any activity, service,
	 * or receiver objects (excluding content providers) have been created.
	 * Implementations should be as quick as possible (for example using
	 * lazy initialization of state) since the time spent in this function
	 * directly impacts the performance of starting the first activity,
	 * service, or receiver in a process.
	 * If you override this method, be sure to call super.onCreate().
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
		MultiDex.install(this);
	}

	/**
	 * Register fragment/activity network listener
	 *
	 * @param listener - network listener
	 */
	public void addNetworkListener(NetworkListener listener) {
		networkListeners.add(listener);
	}

	/**
	 * Remove all network listeners
	 */
	public void removeNetworkListeners() {
		networkListeners.clear();
	}

	/**
	 * Remove a specific network listener or remove all
	 *
	 * @param context current context
	 */
	public void removeNetworkListeners(NetworkListener context) {
		for (int i = 0; i < networkListeners.size(); i++) {
			NetworkListener networkListener = networkListeners.get(i);
			if (networkListener == context) {
				networkListeners.remove(i);
				return;
			}
		}
		networkListeners.clear();
	}

	/**
	 * Get all the network Listeners
	 *
	 * @return network listeners
	 */
	public List<NetworkListener> getNetworkListeners() {
		return networkListeners;
	}

	/**
	 * Get app version as a string
	 *
	 * @return - app version
	 */
	public String getVersion() {
		String versionName = null;
		try {
			versionName = getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
		} catch (PackageManager.NameNotFoundException e) {
			Log.e("Error", e.getMessage());
		}
		return versionName;
	}

	/**
	 * Restart the application from the dex
	 */
	public void restartApplication() {
		//		UserSessionManager.getInstance().clearPref();
		Intent i = getBaseContext().getPackageManager()
				.getLaunchIntentForPackage(getBaseContext().getPackageName());
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(i);
	}

	/**
	 * check network availability of the app
	 * and show a toast if there is not network connection available
	 *
	 * @return true if connected
	 */
	public boolean checkNetwork() {
		if (isNetworkConnected()) {
			return true;
		}
		Toast.makeText(this, getString(R.string.no_network), Toast.LENGTH_SHORT).show();
		return false;
	}

	/**
	 * check network availability of the app with out any alert message
	 *
	 * @return true if connected
	 */
	public boolean isNetworkConnected() {
		ConnectivityManager cm =
				(ConnectivityManager) ExampleApplication.getInstance().getApplicationContext()
						.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
			return activeNetwork != null &&
					activeNetwork.isConnectedOrConnecting();
		}
		return false;
	}

	/**
	 * Check whether a given service is running
	 *
	 * @param serviceClass class of the service
	 * @return running / not running
	 */
	public boolean isMyServiceRunning(Class<?> serviceClass) {
		ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		if (manager == null)
			return false;
		for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (serviceClass.getName().equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Show the common API error in a toast
	 *
	 * @param error api error
	 */
	public void showError(Error error) {
		if (error != null) {
			if (!TextUtils.isEmpty(error.getMessage()))
				Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
			else if (!TextUtils.isEmpty(error.getData()))
				Toast.makeText(this, error.getData(), Toast.LENGTH_SHORT).show();
		}
	}
}
