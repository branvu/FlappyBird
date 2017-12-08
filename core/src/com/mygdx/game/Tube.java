package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;


/**
 * This is the Tube class. It is the tube
 * There should 8 tubes, 4 top and bottom tubes, they move across the screen, and when they reach
 * the end, they recycle to in front of the player in a continuous loop
 * The space between the top and bottom tube will always be TUBE_GAP
 */

public class Tube {
    private static final int FLUCTUATION = 130;//How much the random number can change by
    private static final int TUBE_GAP = 100;//Gap between tubes vertically
    private static final int LOWEST_OPENING = 100;
    public static final int TUBE_WIDTH = 52;//Changes depending on tube sprite
    private Texture topTube;
    private Texture botTube;
    private Vector2 posTopTube,posBotTube;

    public Tube(float x){
        topTube = new Texture("toptube.png");
        botTube = new Texture("bottomtube.png");


    }



    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBotTube() {
        return botTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }
    public void dispose(){
        botTube.dispose();
        topTube.dispose();
    }
}
