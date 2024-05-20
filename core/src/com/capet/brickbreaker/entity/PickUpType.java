package com.capet.brickbreaker.entity;

import com.badlogic.gdx.utils.Array;

public enum PickUpType {
    EXPAND,
    SHRINK,
    SLOW_DOWN,
    SPEED_UP;

    //public methods
    public boolean isExpand(){
        return this==EXPAND;
    }
    public boolean isShrink(){
        return this==SHRINK;
    }
    public boolean isSlowDown(){
        return this==SLOW_DOWN;
    }
    public boolean isSpeedUp(){
        return this==SPEED_UP;
    }

    //static methods
    public static PickUpType random(){
        Array<PickUpType>types=new Array<PickUpType>(PickUpType.values());
        types.shuffle();
        return types.random();

    }


}
