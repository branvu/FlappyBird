package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


/**
 * Handles Animation
 */

public class Animation {
    private Array<TextureRegion> frames;//Store all frames
    private float maxFrameTime;
    private float currentFrameTime;//Current frame time
    private int frameCount;
    private int frame;//Current frame

    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;//Width of single frame
        TextureRegion temp;
        for(int i = 0; i < frameCount;i++){
            temp = new TextureRegion(region, i* frameWidth,0,frameWidth,region.getRegionHeight());
            frames.add(temp);
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
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
        return frames.get(frame);
    }
}
