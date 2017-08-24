package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Kumon on 8/24/2017.
 */

public class GameStateManager {
    private Stack<State> states;
    public GameStateManager(){
        states = new Stack<State>();
    }//Stack of states
    public void push(State state){
        states.push(state);
    }//Push the state into the stack
    public void pop(){
        states.pop().dispose();
    }//Pops the state off the stack
    public void set(State state){//Sets the state on the stack
        states.pop().dispose();
        states.push(state);
    }
    public void update(float dt){
        states.peek().update(dt);
    }
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
