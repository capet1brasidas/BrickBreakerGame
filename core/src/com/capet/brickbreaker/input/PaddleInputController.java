package com.capet.brickbreaker.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.capet.brickbreaker.config.GameConfig;
import com.capet.brickbreaker.entity.Paddle;
import com.capet.brickbreaker.screen.game.GameController;


public class PaddleInputController {
    //attributes
    private final Paddle paddle;
    private final GameController controller;
    private final Rectangle screenLeftSide;
    private final Rectangle  screenRightSide;

    //constructors
    public PaddleInputController(Paddle paddle,GameController controller) {
        this.paddle = paddle;
        this.controller=controller;

        float halfWorldWidth= GameConfig.WORLD_WIDTH/2;
        screenLeftSide=new Rectangle(0,0,halfWorldWidth,  GameConfig.WORLD_HEIGHT);
        screenRightSide=new Rectangle(halfWorldWidth,0,halfWorldWidth, GameConfig.WORLD_HEIGHT);




    }

    //public methods
    public void update(float delta){


        float velocityX=0;

        paddle.setVelocityX(velocityX);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            velocityX=-GameConfig.PADDLE_VELOCITY_X;
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            velocityX=GameConfig.PADDLE_VELOCITY_X;
        }
        if(Gdx.input.isTouched()){
            float screenX=Gdx.input.getX();//in pixels
            float screenY=Gdx.input.getY();//in pixels
            Vector2 screenCoordinates=new Vector2(screenX,screenY);
            Vector2 worldCoordinates=controller.screenToWorld(screenCoordinates);//world units

            if(isLeftSideTouched(worldCoordinates)){
                velocityX=-GameConfig.PADDLE_VELOCITY_X;
            }else if(isRightSideTouched(worldCoordinates)){
                velocityX=GameConfig.PADDLE_VELOCITY_X;
            }

        }


        paddle.setVelocityX(velocityX);

    }

    //private method
    private boolean isLeftSideTouched(Vector2 worldCoordinates) {
        return screenLeftSide.contains(worldCoordinates);
    }

    private boolean isRightSideTouched(Vector2 worldCoordinates){
        return screenRightSide.contains(worldCoordinates);
    }
}
