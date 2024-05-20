package com.capet.brickbreaker.entity;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.jga.util.entity.EntityBase;
import com.jga.util.shape.ShapeUtils;

public class Ball extends EntityBase {
    //attributes

    //constructors

    public Ball() {

    }


    //public methods

    //protected methods

    @Override
    protected float[] createVertices(){
        return ShapeUtils.createOctagon(
                width/2f ,//origin x or center x
                    height/2f,//origin y or center y
                 width/2f //radius
        );
    }





}
