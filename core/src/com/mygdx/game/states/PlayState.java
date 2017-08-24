package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyBird;
import com.mygdx.game.Sprites.Bird;
import com.mygdx.game.Sprites.Tube;

import static com.mygdx.game.Sprites.Tube.TUBE_WIDTH;

/**
 * Created by Kumon on 8/24/2017.
 */

public class PlayState extends State {
    private Bird bird;
    private Tube tube;

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private Texture background;
    private Array<Tube> tubes;
    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        tube = new Tube(200);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);

        background = new Texture("bgPlay.png");
        tubes = new Array<Tube>();
        for(int i = 1; i <= TUBE_COUNT;i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;
        for(Tube tube: tubes){
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth())
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            if(tube.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm));
            }
        }
        cam.update();//Tells to reposition

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,cam.position.x - (cam.viewportWidth/2),0);//Viewport is centered in middle of screen, so draw it halfway off the center
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);
        for(Tube tube : tubes){
            sb.draw(tube.getTopTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);
            sb.draw(tube.getBotTube(),tube.getPosBotTube().x,tube.getPosBotTube().y);
        }

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        for(Tube tube : tubes){
            tube.dispose();
        }
    }
}
