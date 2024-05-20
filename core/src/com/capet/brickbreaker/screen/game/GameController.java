package com.capet.brickbreaker.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.capet.brickbreaker.entity.Ball;

public class GameController {

    //attributes
    private final GameWorld gameWorld;
    private final GameRenderer renderer;


    //constructors
    public GameController(GameWorld gameWorld,GameRenderer renderer){
        this.renderer =renderer;
        this.gameWorld =gameWorld;
    }

    //init




    public void update(float delta) {
        //handle debug input
        handleDebugInput();

        if(gameWorld.isGameOver()){
            return;
        }

        Ball ball=gameWorld.getBall();

        if(ball.isNotActive()&&(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)||
                Gdx.input.justTouched())){
              gameWorld.activateBall();

        }
       gameWorld.update(delta);
    }

    public Vector2 screenToWorld(Vector2 screenCoordinates){
        return  renderer.screenToWorld(screenCoordinates);

    }

    //private methods
    private void handleDebugInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.R)){
            gameWorld.toggleDrawGrid();

        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.T)){
            gameWorld.toggleDebug();

        }

    }



}
