package com.example.sampleapp.listener;

/**
 * Interface is used to make a call back for network change and notify activities, fragments
 * or services with network availability.
 * Created by Vihanga on 2/28/2018.
 */

public interface NetworkListener {
	void onNetworkChange(boolean connected);
}
