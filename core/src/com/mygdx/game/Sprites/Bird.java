package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Kumon on 8/24/2017.
 */

public class Bird {
    private static final int GRAVITY = -15;
    private Vector3 position;//Holds x,y, and z
    private Vector3 velocity;
    private Texture bird;
    private Rectangle bounds;
    private static final int MOVEMENT  = 100;
    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("bird.png");
        bounds = new Rectangle(x,y,bird.getWidth(),bird.getHeight());

    }
    public void update(float dt){
        if(position.y > 0)
            velocity.add(0,GRAVITY,0);
        velocity.scl(dt);//Multiply everything by delta time, Scales with time
        position.add(MOVEMENT*dt,velocity.y,0);//?

        if(position.y < 0){
            position.y = 0;
        }
        velocity.scl(1/dt);//Reverses the first scale
        bounds.setPosition(position.x,position.y);

    }
    public Rectangle getBounds(){
        return bounds;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }
    public void jump(){
        velocity.y = 250;
    }
    public void dispose(){
        bird.dispose();
    }
}
