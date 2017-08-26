package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * The state class is the mother class of states.
 * Every state class has an update, render, handleInput, and dispose method
 * A GameStateManager or gsm is passed to the State class
 * The state class has one camera that all future states will share, and it has a Vector three mouse
 * that holds the x and y coordinates of the mouse.
 */

public abstract class State {

    protected State(GameStateManager gsm){

    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
