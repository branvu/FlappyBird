package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


/**
 * Handles Animation of bird
 * Uses a timer of each frame to switch frames
 * Individual frames are located on a larger frame by dividing the width by how many frames there are
 */

public class Animation {
    private Array<TextureRegion> frames;//Store all frames
    private float maxFrameTime;
    private float currentFrameTime;//Current frame time
    private int frameCount;//How many frames there are
    private int frame;//Current frame

    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();//frames stores an array of TextureRegions
        int frameWidth = region.getRegionWidth() / frameCount;//Width of single frame
        TextureRegion temp;
        for(int i = 0; i < frameCount;i++){
            temp = new TextureRegion(region, i* frameWidth,0,frameWidth,region.getRegionHeight());
            frames.add(temp);
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;//Max frame time is the time for the whole animation / how many frames
        frame = 0;//Frame starts at the 0th frame
    }
    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount)
            frame = 0;
    }
    public TextureRegion getFrame(){
        return frames.get(frame);//Get the current frame
    }
}
