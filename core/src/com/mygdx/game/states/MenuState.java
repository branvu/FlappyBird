package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyBird;

/**
 * Extends State. When pressed, it should go to the Play State
 */

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    public MenuState(GameStateManager gsm) {
        super(gsm);//Passes the gsm to the mother State class
        background = new Texture("Background.jpg");//Comes from the Assets folder
        playBtn = new Texture("Button.png");
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);//Sets the camera's origin lower left

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();//Always begin and end the sprite batch
        sb.draw(background,cam.position.x - (cam.viewportWidth/2),0);
        sb.draw(playBtn,cam.position.x - playBtn.getWidth() / 2,cam.position.y);//Positions the button in the center
        sb.end();
    }
    @Override
    public void dispose(){
        background.dispose();
        playBtn.dispose();
        System.out.println("MenuState disposed");
    }
}
