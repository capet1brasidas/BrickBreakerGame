package com.jga.util.screen.transition;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public interface ScreenTransition {
    float getDuration();

    void render(SpriteBatch batch, Texture currentScreenTexture,
                Texture nextScreenTexture,float percentage);





}
