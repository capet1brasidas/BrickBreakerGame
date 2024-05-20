package com.capet.brickbreaker.screen.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.capet.brickbreaker.BrickBreakerGame;
import com.capet.brickbreaker.common.ScoreController;
import com.capet.brickbreaker.common.SoundController;
import com.capet.brickbreaker.entity.EntityFactory;
import com.capet.brickbreaker.input.PaddleInputController;
import com.capet.brickbreaker.screen.menu.MenuScreen;
import com.jga.util.game.GameBase;
import com.jga.util.screen.ScreenBaseAdapter;

public class GameScreen extends ScreenBaseAdapter {

    //attributes
    private final GameBase game;
    private final AssetManager assetManager;
    private final SpriteBatch batch;
    private final ScoreController scoreController;
    private GameWorld gameWorld;//model
    private GameController controller;//controller
    private GameRenderer renderer;//view
    private EntityFactory factory;
    private PaddleInputController paddleInputController;


    //constructors
    public GameScreen(GameBase game) {
        this.game = game;
        assetManager=game.getAssetManager();
        batch=game.getBatch();
        scoreController=((BrickBreakerGame)game).getScoreController();
    }
    //public methods
    @Override
    public void show() {
        factory=new EntityFactory(assetManager);
        gameWorld=new GameWorld(new SoundController(assetManager),scoreController,factory);

        renderer=new GameRenderer(gameWorld,batch,assetManager);

        controller=new GameController(gameWorld,renderer);

        paddleInputController=new PaddleInputController(gameWorld.getPaddle(),controller);


    }

    @Override
    public void render(float delta) {
        boolean gameOver=gameWorld.isGameOver();

        if(!gameOver){
            paddleInputController.update(delta);
        }

        controller.update(delta);
        renderer.render(delta);
        if(gameOver){
            game.setScreen(new MenuScreen(game));
        }

    }

    @Override
    public void resize(int width, int height) {
      renderer.resize(width,height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();


    }
}
