package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * This is the Bird class, it is the Flappy bird. The bird has velocity and movement.
 * Gravity acts on it when its above position of 0
 * The PlayState restarts every time the bird touches the ground or the tube
 */

public class Bird {
    private static final int GRAVITY = -15;
    private Vector3 position;//Holds x,y, and z
    private Vector3 velocity;
    private Texture bird;
    private Rectangle bounds;
    private Texture texture;
    private static final int MOVEMENT  = 100;
    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);//Starts idle
        bird = new Texture("bird(3).png");
        bounds = new Rectangle(x,y,bird.getWidth(),bird.getHeight());//A rectangle around the bird to detect collisions

    }
    public void update(float dt){
        if(position.y > 0)//If the bird is above 0, gravity applies
            velocity.add(0,GRAVITY,0);//Add the gravity to the bird every update
        //velocity.scl(dt);//Multiply everything by delta time, Scales with time (Put everything in delta time so that we can add the movement also scaled in delta time

        position.add(MOVEMENT*dt,velocity.y*dt,0);//

        if(position.y < 0){//The bird cannot go lower than the ground
            position.y = 0;
        }
        //velocity.scl(1/dt);//Reverses the first scale
        bounds.setPosition(position.x,position.y);//Always set the bounds rectangle to the bird

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

    }
}