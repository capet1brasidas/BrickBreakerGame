package com.jga.util.screen.loading;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jga.util.GdxUtils;
import com.jga.util.game.GameBase;
import com.jga.util.screen.ScreenBaseAdapter;

public abstract class LoadingScreenBase extends ScreenBaseAdapter {


    //constants
//    private static final Logger log=new Logger(LoadingScreenBase.class.getName(), Logger.DEBUG);

//    private static final float PROGRESS_BAR_WIDTH= GameConfig.HUD_WIDTH/2f;
    public static final float DEFAULT_HUD_WIDTH=640f;
    private static final float DEFAULT_HUD_HEIGHT=480f;
    public static final float DEFAULT_PROGRESS_BAR_HEIGHT=60f;
    //attributes
    protected final GameBase game;
    protected final AssetManager assetManager;
    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected ShapeRenderer renderer;
    private float progress;
    private float waitTime=0.75f;
    private float progressBarWidth;
    private float progressBarHeight;

    private float hudWidth;
    private float hudHeight;
    private boolean changeScreen;

    //constructors
    protected LoadingScreenBase(GameBase game){
        this.game=game;
        assetManager=game.getAssetManager();

    }

    //abstract methods
    protected abstract Array<AssetDescriptor> getAssetDescriptors();

    protected abstract void onLoadDone();


    //public methods




    @Override
    public void show() {
        hudWidth=getHudWidth();
        hudHeight=getHudHeight();

        progressBarWidth=getProgressBarWidth();
        progressBarHeight=getProgressBarHeight();

        camera=new OrthographicCamera();
        viewport=new FitViewport(hudWidth,hudHeight);
        renderer=new ShapeRenderer();

        for (AssetDescriptor descriptor:getAssetDescriptors()
        ) {
            assetManager.load(descriptor);

        }


    }

    @Override
    public void render(float delta) {
//        log.debug("render called");
        update(delta);

        GdxUtils.clearScreen();
        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        draw();

        renderer.end();
//        log.debug("changescreen= "+changeScreen);
        if(changeScreen){
           onLoadDone();

        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();

    }

    //protected methods

    protected float getHudWidth() {
        return DEFAULT_HUD_WIDTH;
    }

    protected float getHudHeight() {
        return DEFAULT_HUD_HEIGHT;
    }

    protected float getProgressBarWidth() {
        return getHudWidth()/2f;
    }

    protected float getProgressBarHeight() {
        return DEFAULT_PROGRESS_BAR_HEIGHT;
    }


    //private methods

    private void update(float delta){
        progress=assetManager.getProgress();
//        log.debug("wait time= "+waitTime);

        if(assetManager.update()){
            waitTime-=delta;
            if(waitTime<=0f){

                changeScreen=true;
            }
        }

    }
    private void draw(){
        float progressBarX=(hudWidth-progressBarWidth)/2f;
        float progressBarY=(hudHeight-progressBarHeight)/2f;

        renderer.rect(progressBarX,progressBarY,
                progress*progressBarWidth,progressBarHeight);


    }

}
