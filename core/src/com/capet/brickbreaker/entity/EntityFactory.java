package com.capet.brickbreaker.entity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.capet.brickbreaker.assets.AssetDescriptors;
import com.capet.brickbreaker.config.GameConfig;
import com.jga.util.Direction;
import com.jga.util.parallax.ParallaxLayer;

public class EntityFactory {
    //attributes
    private final AssetManager assetManager;
    private ParticleEffectPool fireEffectPool;
    private ParticleEffectPool starEffectPool;
    private Pool<PickUp> pickUpPool;



    //constructors


    public EntityFactory(AssetManager assetManager) {
        this.assetManager = assetManager;
        init();

    }

    //init
    private void init(){
        ParticleEffect fireEffect=assetManager.get(AssetDescriptors.FIRE);
        ParticleEffect starEffect=assetManager.get(AssetDescriptors.STAR);
        fireEffectPool=new ParticleEffectPool(fireEffect,5,20);
        starEffectPool=new ParticleEffectPool(starEffect,5,10);
        pickUpPool=Pools.get(PickUp.class,10);
    }

    //public methods
    public ParallaxLayer createBackground(){
        ParallaxLayer background=new ParallaxLayer();
        background.setSize(GameConfig.WORLD_WIDTH,GameConfig.WORLD_HEIGHT);
        background.setDirection(Direction.DOWN);
        return background;
    }
    public Paddle createPaddle(){
        Paddle paddle=new Paddle();
        paddle.setPosition(GameConfig.PADDLE_START_X,GameConfig.PADDLE_START_Y);
        paddle.setSize(GameConfig.PADDLE_START_WIDTH,GameConfig.PADDLE_HEIGHT);
        return paddle;

    }

    public Array<Brick> generateBricks(){
        Array<Brick> bricks=new Array<Brick>();
        float startX=GameConfig.LEFT_PAD;
        float startY=GameConfig.WORLD_HEIGHT-GameConfig.TOP_PAD-GameConfig.BRICK_HEIGHT;

        for (int row = 0; row < GameConfig.ROW_COUNT; row++) {
            float brickY=startY-row*(GameConfig.ROW_SPACING+GameConfig.BRICK_HEIGHT);
            for (int column = 0; column < GameConfig.COLUMN_COUNT; column++) {
                float brickX=startX+column*(GameConfig.BRICK_WIDTH+GameConfig.COLUMN_SPACING);

                bricks.add(createBrick(brickX,brickY));

            }

        }
        return bricks;
    }

    public PickUp createPickUp(float x,float y){
        PickUp pickUp=pickUpPool.obtain();
        pickUp.setType(PickUpType.random());
        pickUp.setSize(GameConfig.PICKUP_SIZE,GameConfig.PICKUP_SIZE);
        pickUp.setPosition(x,y);
        pickUp.setVelocityY(GameConfig.PICKUP_VELOCITY_Y);
        return pickUp;
    }

    //private methods
    public Brick createBrick(float x,float y){
        Brick brick=new Brick();
        brick.setPosition(x,y);
        brick.setSize(GameConfig.BRICK_WIDTH,GameConfig.BRICK_HEIGHT);
        return brick;

    }

    public Ball createBall(){
        Ball ball=new Ball();
        ball.setPosition(GameConfig.BALL_START_X,GameConfig.BALL_START_Y);
        ball.setSize(GameConfig.BALL_SIZE);
        ball.setVelocity(GameConfig.BALL_START_ANGLE,GameConfig.BALL_START_SPEED);


        return ball;
    }

    public ParticleEffectPool.PooledEffect createFire(float x,float y){
        ParticleEffectPool.PooledEffect effect=fireEffectPool.obtain();
        effect.setPosition(x,y);
        effect.start();
        return effect;
    }

    public ParticleEffectPool.PooledEffect createStar(float x,float y){
        ParticleEffectPool.PooledEffect effect=starEffectPool.obtain();
        effect.setPosition(x,y);
        effect.start();
        return effect;
    }


    public void freePickup(PickUp pickUp) {
        pickUpPool.free(pickUp);

    }
    public void freePickups(Array<PickUp> pickUps){
        pickUpPool.freeAll(pickUps);
    }


    //private methods


}
