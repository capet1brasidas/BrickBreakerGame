package com.capet.brickbreaker;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.capet.brickbreaker.BrickBreakerGame;
import com.jga.util.ads.NullAdController;

public class AndroidLauncher extends AndroidApplication {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new BrickBreakerGame(NullAdController.INSTANCE), config);
	}
}
