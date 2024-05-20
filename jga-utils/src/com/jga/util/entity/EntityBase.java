package com.jga.util.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jga.util.entity.script.EntityScript;
import com.jga.util.entity.script.ScriptController;
import com.jga.util.shape.ShapeUtils;

/**
 * Created by goran on 26/09/2016.
 */

public abstract class EntityBase {

    // == attributes ==
    protected float x;
    protected float y;

    protected float width = 1;
    protected float height = 1;
    protected ScriptController scriptController;
    protected Vector2 velocity=new Vector2();

    protected Polygon bounds;

    // == constructors ==
    public EntityBase() {
        init();


    }

    //init
    private void init(){
        bounds = new Polygon();
        bounds.setPosition(x,y);
        bounds.setVertices(createVertices());
        scriptController=new ScriptController(this);
    }
    // == public methods ==

    public void update(float delta){
        scriptController.update(delta);
        float newX=x+velocity.x*delta;
        float newY=y+velocity.y*delta;

        setPosition(newX,newY);


    }



    public Vector2 getVelocity() {
        return velocity;
    }
    public void setVelocityXY(float velocityX,float velocityY){
        velocity.set(velocityX,velocityY);
    }



    public void setVelocity(float angleDeg,float value){
        velocity.x=value* MathUtils.cosDeg(angleDeg);
        velocity.y=value*MathUtils.sinDeg(angleDeg);
    }
    public void setVelocityY(float velocityY){    velocity.y=velocityY;    }
    public void setVelocityX(float velocityX){
        velocity.x=velocityX;
    }
    public void multiplyVelocityX(float xAmount){
        velocity.x*=xAmount;
    }


    public void multiplyVelocityY(float yAmount){
        velocity.y*=yAmount;
    }

    public float getSpeed(){
        return velocity.len();
    }

    public void setPosition(float x,float y){
        this.x=x;
        this.y=y;
        updateBounds();
    }




    public void setSize(float size) {
        this.width = size;
        this.height=size;
        updateBounds();
    }
    public void stop(){
        velocity.setZero();

    }
    public boolean isNotActive(){
        return velocity.isZero();

    }





    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        updateBounds();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
        updateBounds();
    }

    public void setY(float y) {
        this.y = y;
        updateBounds();
    }



    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        updateBounds();
    }

    public float getHeight() {
        return height;
    }

    public Polygon getBounds() {
        return bounds;
    }

    public float getAngleDeg() {
        return MathUtils.atan2(velocity.y,velocity.x)*MathUtils.radiansToDegrees;
    }



    public void updateBounds() {
        bounds.setPosition(x, y);
        bounds.setVertices(createVertices());
    }

    public void addScript(EntityScript toAdd){
        scriptController.addScript(toAdd);

    }
    public void removeScript(EntityScript toRemove){
        scriptController.removeScript(toRemove);

    }

    //protected methods
    protected  float[] createVertices(){
        return ShapeUtils.createRectangle(width,height);

    }



}
