package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * Extends the State class
 * This is the state in which the game is played in
 */

public class PlayState {

    private Texture background;

    protected OrthographicCamera cam;//Camera
    protected Vector3 mouse;//Mouse

    public PlayState() {
        cam = new OrthographicCamera();
        mouse = new Vector3();
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);//Sets the camera's origin lower left
                                                                            //and sets the camera centered
        background = new Texture("bgPlay.png");

    }

    public PlayState update(float dt) {
        cam.update();//Tells to the camera that we repositioned
        return this;
    }

    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);//The coordinate game screen starts bottom left
        sb.begin();
        sb.draw(background,cam.position.x - (cam.viewportWidth/2),0);//Viewport is centered in middle of screen, so draw it halfway off the center


        sb.end();//Close the sprite batch
    }

    public void dispose() {
        background.dispose();
    }

}
