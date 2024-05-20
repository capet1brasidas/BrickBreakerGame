package com.capet.brickbreaker;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.capet.brickbreaker.BrickBreakerGame;
import com.capet.brickbreaker.config.GameConfig;
import com.jga.util.ads.AdController;
import com.jga.util.ads.NullAdController;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {


	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(GameConfig.WIDTH,GameConfig.HEIGHT);
		config.setForegroundFPS(60);
		config.setTitle("brick-breaker");
		new Lwjgl3Application(new BrickBreakerGame(NullAdController.INSTANCE), config);
	}
}
