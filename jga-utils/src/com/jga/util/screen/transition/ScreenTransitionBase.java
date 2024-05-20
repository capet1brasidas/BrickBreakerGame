package com.jga.util.screen.transition;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ScreenTransitionBase implements ScreenTransition{
    //attributes
    protected final float duration;

    //constructors


    public ScreenTransitionBase(float duration) {
        this.duration = duration;
    }

    @Override
    public float getDuration() {
        return duration;
    }

}
