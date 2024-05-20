package com.capet.brickbreaker;

import com.badlogic.gdx.utils.Logger;
import com.capet.brickbreaker.common.ScoreController;
import com.capet.brickbreaker.screen.loading.LoadingScreen;
import com.jga.util.ads.AdController;
import com.jga.util.game.GameBase;

public class BrickBreakerGame extends GameBase {

	//constants
	private static final Logger log=new Logger(BrickBreakerGame.class.getName(), Logger.DEBUG);


	//attributes
	private ScoreController scoreController;

	//constructors
	public BrickBreakerGame(AdController adController) {
		super(adController);
	}

	@Override
	public void postCreate() {
//		log.debug("post create");
		scoreController=new ScoreController();
//		log.debug("calling setScreen and setting screen to loading screen");
		setScreen(new LoadingScreen(this));
		//loading screen showed
	}

	public ScoreController getScoreController(){
		return scoreController;
	}
}
