package com.capet.brickbreaker.screen.loading;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.utils.Array;
import com.capet.brickbreaker.assets.AssetDescriptors;
import com.capet.brickbreaker.config.GameConfig;
import com.capet.brickbreaker.screen.menu.MenuScreen;
import com.jga.util.game.GameBase;
import com.jga.util.screen.loading.LoadingScreenBase;

public class LoadingScreen extends LoadingScreenBase {


    //constructors
    public LoadingScreen(GameBase game) {
        super(game);
    }




    //protected methods
    @Override
    protected Array<AssetDescriptor> getAssetDescriptors() {
        return AssetDescriptors.ALL;
    }

    @Override
    protected void onLoadDone() {
        game.setScreen(new MenuScreen(game));
    }

    @Override
    protected float getHudWidth() {
        return GameConfig.HUD_WIDTH;
    }

    @Override
    protected float getHudHeight() {
        return GameConfig.HUD_HEIGHT;
    }



}
