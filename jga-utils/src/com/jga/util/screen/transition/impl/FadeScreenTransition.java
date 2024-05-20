package com.jga.util.screen.transition.impl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Logger;
import com.jga.util.GdxUtils;
import com.jga.util.screen.transition.ScreenTransitionBase;

public class FadeScreenTransition extends ScreenTransitionBase {

    private static final Logger log=new Logger(FadeScreenTransition.class.getName(), Logger.DEBUG);


    //constructors

    public FadeScreenTransition(float duration) {
        super(duration);
    }
    //public method


    @Override
    public void render(SpriteBatch batch, Texture currentScreenTexture,
                       Texture nextScreenTexture, float percentage) {
        int currentScreenWidth=currentScreenTexture.getWidth();
        int currentScreenHeight=currentScreenTexture.getHeight();
        int nextScreenWidth=nextScreenTexture.getWidth();
        int nextScreenHeight=nextScreenTexture.getHeight();
        log.debug("percentage= "+percentage);
        //interpolate percentage
        percentage= Interpolation.fade.apply(percentage);
        log.debug("percentage= "+percentage);
        //clear screen
        GdxUtils.clearScreen();

        Color oldColor=batch.getColor().cpy();

        batch.begin();
        batch.setColor(1,1,1,1f-percentage);//white color with transparency
        batch.draw(currentScreenTexture, //texture
                0,0, //x,y
                0,0, //origin x,y
                currentScreenWidth,currentScreenHeight, //width,height
                1,1, //scale x,y
                0, //rotation
                0,0, //src x,y
                currentScreenWidth,currentScreenHeight, //src width,height
                false,true); //flip x,y

        //draw next screen
        batch.setColor(1,1,1,percentage);//white color with transparency
        batch.draw(nextScreenTexture,
                0,0,
                0,0,
                nextScreenWidth,nextScreenHeight,
                1,1,
                0,
                0,0,
                nextScreenWidth,nextScreenHeight,
                false,true);

        batch.setColor(oldColor);

//        batch.end();

    }
}
