package com.example.sampleapp.ui.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.sampleapp.ExampleApplication;
import com.example.sampleapp.R;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Base activity contains all the common component of an activity.
 * Progress bars, login progress, network checking frequently used db calls, etc...
 */
public class BaseActivity extends AppCompatActivity {

	private ProgressDialog progressDialog;
	private KProgressHUD kProgressHUD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * Initialize common views
	 */
	void initializeViews() {
		kProgressHUD = KProgressHUD.create(BaseActivity.this)
				.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
				.setLabel(getString(R.string.please_wait))
				.setCancellable(false)
				.setAnimationSpeed(2)
				.setDimAmount(0.2f);
	}

	/**
	 * set common listeners
	 */
	void setListeners() {

	}

	/**
	 * show the common progress bar
	 */
	void showProgress() {
		kProgressHUD.show();
	}

	/**
	 * Hide the common progress bar
	 */
	void hideProgress() {
		if (kProgressHUD.isShowing()) {
			kProgressHUD.dismiss();
		}
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
	 * Hide the soft keyboard from the interface
	 */
	void hideKeyBoard() {
		View view = this.getCurrentFocus();
		if (view != null) {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	/**
	 * Show the mobile default progress bar with a message
	 *
	 * @param msg message
	 */
	@SuppressLint("RestrictedApi")
	public void progressDialogSystem(final String msg) {
		if (progressDialog == null)
			progressDialog = new ProgressDialog(new ContextThemeWrapper(BaseActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog));
		progressDialog.setCancelable(false);
		progressDialog.setMessage(msg);
		progressDialog.show();
	}

	/**
	 * Hide the system default progress bar
	 */
	public void hideSystemProgress() {
		if (progressDialog != null) progressDialog.dismiss();
		progressDialog = null;

	}
}
