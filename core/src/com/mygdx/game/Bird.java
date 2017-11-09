package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * This is the Bird class, it is the Flappy bird. The bird has velocity and movement.
 * Gravity acts on it when its above position of 0
 * The PlayState restarts every time the bird touches the ground or the tube
 */

public class Bird {
    private int gravity = -15;
    private Vector2 position;//Holds x,y, and z
    private Vector2 velocity;
    private Texture bird;
    private Rectangle bounds;
    private Texture texture;
    private int movement  = 100;
    public Bird(int x, int y){
        position = new Vector2(x,y);
        velocity = new Vector2(0,0);//Starts idle
        bird = new Texture("bird(3).png");
        bounds = new Rectangle(x,y,bird.getWidth(),bird.getHeight());//A rectangle around the bird to detect collisions
    }
    public void update(float dt){
        
        velocity.add(0, gravity);

        position.add(movement*dt,velocity.y*dt);

        if(position.y <= 0){
            position.y = 0;
        }
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public Vector2 getPosition() {
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
