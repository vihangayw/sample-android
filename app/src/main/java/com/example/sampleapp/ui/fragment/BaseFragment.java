package com.example.sampleapp.ui.fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.example.sampleapp.ExampleApplication;
import com.example.sampleapp.R;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

	private KProgressHUD kProgressHUD;

	public BaseFragment() {
		// Required empty public constructor
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		FragmentActivity activity = getActivity();
		if (activity != null)
			kProgressHUD = KProgressHUD.create(activity)
					.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
					.setLabel(getString(R.string.please_wait))
					.setCancellable(false)
					.setAnimationSpeed(2)
					.setDimAmount(0.2f);
	}

	void showProgress() {
		kProgressHUD.show();
	}

	void hideProgress() {
		if (kProgressHUD.isShowing()) {
			kProgressHUD.dismiss();
		}
	}

	public boolean checkNetwork() {
		if (isNetworkConnected()) {
			return true;
		}
		FragmentActivity activity = getActivity();
		if (activity != null)
			Toast.makeText(activity, getString(R.string.no_network), Toast.LENGTH_SHORT).show();
		return false;
	}

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
}
