package com.capet.brickbreaker.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.jga.util.entity.EntityBase;

public class PickUp extends EntityBase implements Pool.Poolable {
    //attributes
    private PickUpType type;
    //constructors
    public PickUp(){
        type=PickUpType.random();

    }
    //public methods
    @Override
    public void update(float delta){
        super.update(delta);
    }
    public void setType(PickUpType type){
        this.type=type;
    }


    public boolean isExpand(){return type.isExpand();}
    public boolean isShrink(){return type.isShrink();}
    public boolean isSlowDown(){return type.isSlowDown();}
    public boolean isSpeedUp(){return type.isSpeedUp();}




    @Override
    public void reset() {
        type=PickUpType.random();
        velocity.setZero();
    }

    public PickUpType getType() {
        return type;
    }
}
