package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Extends State class. When pressed, it should go to the Play State
 * Contains a background and a play button sprite
 */

public class MenuState extends State {


    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(SpriteBatch sb) {

    }
    @Override
    public void dispose(){

    }
}
