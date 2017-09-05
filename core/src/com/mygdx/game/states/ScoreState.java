package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.FlappyBird;

/**
 * Created by Brandon on 9/4/2017.
 */

public class ScoreState extends State{
    private Texture background;
    private FreeTypeFontGenerator font;
    private BitmapFont font12;
    ScoreState(GameStateManager gsm){
        super(gsm);
        background = new Texture("bgPlay.png");
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);//Sets the camera's origin lower left

        font = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/OpenSans-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        font12 = font.generateFont(parameter);
        Gdx.gl.glClearColor(1, 1, 1, 1);


    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched() && (System.currentTimeMillis() - PlayState.milliSecondsSincedPressed) > 700 ){//Gets input if user touched the screen
            gsm.set(new MenuState(gsm));//Uses the GameStateManager to set the class
            //System.out.println(" " + FlappyBird.getScore());
            FlappyBird.setScore(0);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,cam.position.x - (cam.viewportWidth/2),0);//Positions the background at the bottom left
        font12.draw(sb,Integer.toString(FlappyBird.getScore()),cam.position.x - 10,cam.position.y + 50);
        //System.out.print("Printing");
        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
        font12.dispose();
    }
}
