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
    private Random rand;
    private Rectangle boundsTop, boundsBot;

    public Tube(float x){
        topTube = new Texture("toptube.png");
        botTube = new Texture("bottomtube.png");
        rand = new Random();
        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);//Allows to choose random height of tube
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - botTube.getHeight());//Placing the bottom tube based on the top tube
        boundsTop = new Rectangle(posTopTube.x,posTopTube.y, topTube.getWidth(),topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x,posBotTube.y, botTube.getWidth(),botTube.getHeight());

    }
    public void reposition(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);//Repositioning the top and bottom tubes
        posBotTube.set(x, posTopTube.y - TUBE_GAP - botTube.getHeight());
        boundsTop.setPosition(posTopTube.x,posTopTube.y);
        boundsBot.setPosition(posBotTube.x,posBotTube.y);
    }
    public boolean collides(Rectangle player){//Handles collisions between the tube and the player object
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
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
