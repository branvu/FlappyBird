package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyBird;

/**
 * Extends State class. When pressed, it should go to the Play State
 * Contains a background and a play button sprite
 */

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    private Texture title;
    public MenuState(GameStateManager gsm) {//Constructor for the MenuState
        super(gsm);//Passes the gsm to the mother State class
        background = new Texture("bgPlay.png");//Comes from the Assets folder
        playBtn = new Texture("Button.png");//Assigns the texture to the path of the actual picture
        title = new Texture("Title.png");
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);//Sets the camera's origin lower left

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){//Gets input if user touched the screen
            gsm.set(new PlayState(gsm));//Uses the GameStateManager to set the class
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }//Constantly update the input

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);//Sets a certain origin and coordinate system
        sb.begin();//Always begin and end the sprite batch
        sb.draw(background,cam.position.x - (cam.viewportWidth/2),0);//Positions the background at the bottom left
        sb.draw(playBtn,cam.position.x - playBtn.getWidth() / 2,cam.position.y - 100);//Positions the button in the center
        sb.draw(title,cam.position.x - playBtn.getWidth() / 2 + 5,cam.position.y + 100);//Positions the button in the center

        sb.end();//close the sprite batch
    }
    @Override
    public void dispose(){
        background.dispose();//Correctly disposes of the texture
        playBtn.dispose();
    }
}
