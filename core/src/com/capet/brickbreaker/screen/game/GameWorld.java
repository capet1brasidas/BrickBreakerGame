package com.capet.brickbreaker.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.capet.brickbreaker.common.ScoreController;
import com.capet.brickbreaker.common.SoundController;
import com.capet.brickbreaker.config.GameConfig;
import com.capet.brickbreaker.entity.Ball;
import com.capet.brickbreaker.entity.Brick;
import com.capet.brickbreaker.entity.EntityFactory;
import com.capet.brickbreaker.entity.Paddle;
import com.capet.brickbreaker.entity.PickUp;
import com.capet.brickbreaker.input.PaddleInputController;
import com.capet.brickbreaker.script.BallSlowDownScript;
import com.capet.brickbreaker.script.BallSpeedUpScript;
import com.capet.brickbreaker.script.PaddleExpandScript;
import com.capet.brickbreaker.script.PaddleShrinkScript;
import com.jga.util.parallax.ParallaxLayer;
import com.jga.util.shape.RectangleUtils;

public class GameWorld {

    //attributes
    private final SoundController soundController;
    private final ScoreController scoreController;
    private final EntityFactory factory;
    private Paddle paddle;
    private PaddleInputController paddleInputController;
    private Array<Brick> bricks=new Array<Brick>();
    private Ball ball;
    private ParallaxLayer background;

    private boolean drawGrid=true;
    private boolean drawDebug=true;

    private Array<ParticleEffectPool.PooledEffect> effects=new Array<ParticleEffectPool.PooledEffect>();
    private Array<PickUp>pickUps=new Array<PickUp>();
    private int lives=GameConfig.LIVES_START;





    //constructors
    public GameWorld(SoundController soundController,ScoreController scoreController, EntityFactory factory){
        this.soundController=soundController;
        this.factory=factory;
        this.scoreController=scoreController;
        init();
    }

    //init
    private void init(){
        background=factory.createBackground();
        paddle=factory.createPaddle();
        paddle=factory.createPaddle();
        scoreController.reset();
//        paddleInputController=new PaddleInputController(paddle);


        ball=factory.createBall();

        startLevel();
    }



    public void update(float delta) {
        background.update(delta);


        if(ball.isNotActive()){
            return;
        }

       //update paddle
        paddle.update(delta);

        //block paddle from leaving the world
        blockPaddleFromLeavingTheWorld();

        //update ball
        ball.update(delta);

        //block ball from leaving the world;
        checkBallBounds();

        //update pickups
        updatePickups(delta);

        //check collision
        checkCollision();
        //update effects
        updateEffects(delta);

        if(bricks.size==0){
            startLevel();
        }



    }

    public ParallaxLayer getBackground() {
        return background;
    }

    public Array<PickUp> getPickUps() {
        return pickUps;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public Array<Brick> getBricks() {
        return bricks;
    }

    public Ball getBall() {
        return ball;
    }

    public Array<ParticleEffectPool.PooledEffect> getEffects() {
        return effects;
    }

    public boolean isDrawGrid() {
        return drawGrid;
    }

    public boolean isDrawDebug() {
        return drawDebug;
    }

    public String getScoreString(){
      return   scoreController.getScoreString();
    }
    public void activateBall(){
        ball.setVelocity(GameConfig.BALL_START_ANGLE,GameConfig.BALL_START_SPEED);
    }

    public void toggleDrawGrid() {
        drawGrid=!drawGrid;

    }

    public void toggleDebug() {
        drawDebug=!drawDebug;

    }

    public boolean isGameOver(){
        return lives<=0;
    }

    public int getLives() {
        return lives;
    }

    //private methods
    private void blockPaddleFromLeavingTheWorld() {
        //block left
        if(paddle.getX()<=0){
            paddle.setX(0);
        }
        //block right
        float paddleRightX=paddle.getX()+paddle.getWidth();

        if(paddleRightX>=GameConfig.WORLD_WIDTH){
            paddle.setX(GameConfig.WORLD_WIDTH-paddle.getWidth());

        }
    }

    private void checkCollision(){
        checkBallWithPaddleCollision();
        checkBallWithBrickCollision();
        checkPaddleWithPickupCollision();
    }



    private void checkBallWithBrickCollision() {
        Polygon ballPolygon=ball.getBounds();
        float ballRadius=ball.getWidth()/2f;
        Circle ballBounds=new Circle(
            ball.getX()+ballRadius,
            ball.getY()+ballRadius,
            ballRadius
        );
        for (int i = 0; i < bricks.size; i++) {
            Brick brick=bricks.get(i);
            Polygon brikPolygon=brick.getBounds();
            Rectangle brickBounds=brick.getBounds().getBoundingRectangle();

            if(!Intersector.overlapConvexPolygons(ballPolygon,brikPolygon)){
                continue;
            }

            soundController.hit();

            //check which side of brick is overlapping with ball
            Vector2 bottomLeft=RectangleUtils.getBottomLeft(brickBounds);
            Vector2 bottomRight=RectangleUtils.getBottomRight(brickBounds);
            Vector2 topLeft= RectangleUtils.getTopLeft(brickBounds);
            Vector2 topRight=RectangleUtils.getTopRight(brickBounds);

            Vector2 center=new Vector2(ballBounds.x,ballBounds.y);
            float squareRadius=ballBounds.radius*ballBounds.radius;
            boolean bottomHit=Intersector.intersectSegmentCircle(
                    bottomLeft,bottomRight,center,squareRadius
            );
            boolean leftHit=Intersector.intersectSegmentCircle(
                    bottomLeft,topLeft,center,squareRadius
            );
            boolean rightHit=Intersector.intersectSegmentCircle(
                    bottomRight,topRight,center,squareRadius
            );
            boolean topHit=Intersector.intersectSegmentCircle(
                    topLeft,topRight,center,squareRadius);
            //left right
            if(ball.getVelocity().x>0&&leftHit){
                ball.multiplyVelocityX(-1);


            }else if(ball.getVelocity().x<0&&rightHit){
                ball.multiplyVelocityX(-1);

            }

            if(ball.getVelocity().y>0&&bottomHit){
                ball.multiplyVelocityY(-1);

            }else if(ball.getVelocity().y<0&&topHit){
                ball.multiplyVelocityY(-1);

            }
            //create fire effect
            float effectX=brick.getX()+brick.getWidth()/2f;
            float y=brick.getY()+brick.getHeight()/2f;

            spawnFireEffect(effectX,y);

//            if(MathUtils.random()<0.2){
//
//
//            }
            float pickupX=brick.getX()+(brick.getWidth()-GameConfig.PICKUP_SIZE)/2f;
            spawnPickup(pickupX,y);

            //remove brick
            bricks.removeValue(brick,false);

            //add score
            scoreController.addScore(GameConfig.BRICK_SCORE);
            System.out.println("currentScore= "+scoreController.getScoreString());

        }

    }

    private void checkPaddleWithPickupCollision() {
        Polygon paddleBounds=paddle.getBounds();
        for (int i = 0; i <pickUps.size ; i++) {
            PickUp pickUp=pickUps.get(i);
            Polygon pickupBounds=pickUp.getBounds();
            if(Intersector.overlapConvexPolygons(pickupBounds,paddleBounds)){
                soundController.pickup();
                float x=pickUp.getX()+pickUp.getWidth()/2f;
                float y=pickUp.getY();

                addScript(pickUp);
                spawnStarEffect(x,y);
                pickUps.removeIndex(i);
                factory.freePickup(pickUp);

            }
        }




    }

    private void spawnStarEffect(float x, float y) {
        ParticleEffectPool.PooledEffect effect=factory.createStar(x,y);
        effects.add(effect);

    }


    private void spawnFireEffect(float effectX, float effectY) {
        ParticleEffectPool.PooledEffect effect=factory.createFire(effectX,effectY);
        effects.add(effect);

    }

    private void checkBallWithPaddleCollision() {
        Polygon ballBounds=ball.getBounds();
        Polygon paddleBounds=paddle.getBounds();
        if(Intersector.overlapConvexPolygons(ballBounds,paddleBounds)){
            soundController.hit();
            float ballCenterX= ball.getX()+ball.getWidth()/2f;
            float percent=(ballCenterX-paddle.getX())/ paddle.getWidth();//0-1
            //interpolate angle between 150 and 30
            float bounceAngle=150-percent*120;
            //150-0*120=150 ; 150-1*120=30
            ball.setVelocity(bounceAngle,ball.getSpeed());

        }


    }

    private void startLevel() {


        restart();
        bricks.addAll(factory.generateBricks());

    }

    private void restart() {
        for (int i = 0; i <pickUps.size ; i++) {
            PickUp pickUp=pickUps.get(i);
            factory.freePickup(pickUp);
            pickUps.removeIndex(i);
        }
        for (int i = 0; i < effects.size; i++) {
            ParticleEffectPool.PooledEffect effect=effects.get(i);
            effect.free();
            effects.removeIndex(i);
        }


//        bricks.add(factory.createBrick(GameConfig.PADDLE_START_X,GameConfig.PADDLE_START_Y+3));

        paddle.setPosition(GameConfig.PADDLE_START_X,GameConfig.PADDLE_START_Y);
        ball.setPosition(GameConfig.BALL_START_X,GameConfig.BALL_START_Y);
        ball.stop();
    }


    private void spawnPickup(float x, float y) {
        PickUp pickUp=factory.createPickUp(x,y);
        pickUps.add(pickUp);
    }

    private void addScript(PickUp pickUp){
        if(pickUp.isExpand()){
            paddle.addScript(new PaddleExpandScript());
        } else if (pickUp.isShrink()) {
            paddle.addScript(new PaddleShrinkScript());
        }else if (pickUp.isSpeedUp()){
            ball.addScript(new BallSpeedUpScript());
        } else if (pickUp.isSlowDown()) {
            ball.addScript(new BallSlowDownScript());

        }
    }

    private void checkBallBounds() {
        if(ball.getY()<=0){
            soundController.lost();
            lives--;
            restart();
            if(isGameOver()){
                scoreController.updateHighScore();
            }

//            ball.setY(0);
//            ball.multiplyVelocityY(-1);

        }
        //top
        float ballTop=ball.getY()+ball.getHeight();
        if(ballTop>=GameConfig.WORLD_HEIGHT){
            ball.setY(GameConfig.WORLD_HEIGHT-ball.getHeight());
            ball.multiplyVelocityY(-1);
        }
        //left
        if(ball.getX()<=0){
            ball.setX(0);
            ball.multiplyVelocityX(-1);
        }

        //right
        float ballRight=ball.getX()+ball.getWidth();
        if(ballRight>=GameConfig.WORLD_WIDTH){
            ball.setX(GameConfig.WORLD_WIDTH-ball.getWidth());
            ball.multiplyVelocityX(-1);
        }
    }

    private void updatePickups(float delta) {
        for (int i = 0; i <pickUps.size ; i++) {
            PickUp pickUp=pickUps.get(i);
            pickUp.update(delta);

            if(pickUp.getY()<-pickUp.getHeight()){
                factory.freePickup(pickUp);
                pickUps.removeIndex(i);

            }

        }
    }

    private void updateEffects(float delta) {
        for (int i = 0; i <effects.size ; i++) {
            ParticleEffectPool.PooledEffect effect=effects.get(i);
            effect.update(delta);
            if(effect.isComplete()){
                effects.removeIndex(i);
                effect.free();
            }
        }
    }



}
