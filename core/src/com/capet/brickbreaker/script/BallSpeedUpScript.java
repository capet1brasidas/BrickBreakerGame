package com.capet.brickbreaker.script;

import com.capet.brickbreaker.config.GameConfig;
import com.capet.brickbreaker.entity.Ball;
import com.jga.util.entity.script.EntityScriptBase;

public class BallSpeedUpScript extends EntityScriptBase<Ball> {
    //attribute
    private float finalSpeed;
    //public methods


    @Override
    public void added(Ball entity) {
        super.added(entity);
        float currentSpeed=entity.getSpeed();
        finalSpeed=currentSpeed+ GameConfig.BALL_SPEED_FACTOR*GameConfig.BALL_START_SPEED;
        if(finalSpeed>=GameConfig.BALL_MAX_SPEED){
            finalSpeed=GameConfig.BALL_MAX_SPEED;
        }
    }

    @Override
    public void update(float delta) {
        float angleDeg=entity.getAngleDeg();
        entity.setVelocity(angleDeg,finalSpeed);
        finish();
    }
}
