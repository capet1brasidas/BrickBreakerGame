package com.capet.brickbreaker;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {
    //constants
    private static final String RAW_ASSETS_PATH="assets";
    public static final String ASSETS_PATH="assets";

    //main
    public static void main(String[] args) {
        TexturePacker.process(
                RAW_ASSETS_PATH+"/gameplay",
                ASSETS_PATH+"/gameplay",
                "gameplay"
        );

        TexturePacker.process(
                RAW_ASSETS_PATH+"/ui",
                ASSETS_PATH+"/ui",
                "skin"
        );
    }





}
