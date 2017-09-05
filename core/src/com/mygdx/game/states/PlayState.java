package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyBird;
import com.mygdx.game.Sprites.Bird;
import com.mygdx.game.Sprites.Tube;

import sun.rmi.runtime.Log;

/**
 * Extends the State class
 * This is the state in which the game is played in
 */

public class PlayState extends State {
    private Bird bird;
    private Tube tube;

    private final static int GROUND_Y_OFFSET = -50;//Offset to place the ground correctly
    private Texture ground;
    private boolean justScored = false;
    private static final int TUBE_SPACING = 100;//Spacing between tubes
    private static final int TUBE_COUNT = 4;
    private Texture background;
    private Array<Tube> tubes;//Array of tubes
    private Vector2 groundPos1, groundPos2;
    private double milliSinceScore;
    protected PlayState(GameStateManager gsm) {
        super(gsm);
        ground = new Texture("ground.jpg");
        bird = new Bird(50,300);
        tube = new Tube(200);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);//Sets the camera's origin lower left
                                                                            //and sets the camera centered
        groundPos1 = new Vector2(cam.position.x - (cam.viewportWidth / 2),GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - (cam.viewportWidth / 2)) + ground.getWidth(),GROUND_Y_OFFSET);
        background = new Texture("bgPlay.png");
        tubes = new Array<Tube>();//Initialize the tubes array
        for(int i = 1; i <= TUBE_COUNT;i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));//Adding the tubes to the screen at different x cordinates
        }
    }
    public static long milliSecondsSincedPressed;
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
            milliSecondsSincedPressed = System.currentTimeMillis();
        }
    }
    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;//Center the camera on the bird
        for(int i = 0; i <tubes.size; i++){//Do this rather than below because other way will throw nested error
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth())
                //If the camera's left origin is greater than the right end of the tube's X POSITION, reposition the tube
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));//Evenly puts the next Top tube
            if(tube.collides(bird.getBounds())){
                gsm.set(new ScoreState(gsm));
            }
            if(tube.checkScore(bird.getScoreBounds())){
                if((System.currentTimeMillis() - milliSinceScore) > 500) {
                    FlappyBird.setScoreBy1();
                    milliSinceScore = System.currentTimeMillis();
                }

            }
        }
        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){//If bird touches the ground
            milliSecondsSincedPressed = System.currentTimeMillis();

            gsm.set(new ScoreState(gsm));
        }
        cam.update();//Tells to the camera that we repositioned

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);//The cordinate game screen starts bottom left
        sb.begin();
        sb.draw(background,cam.position.x - (cam.viewportWidth/2),0);//Viewport is centered in middle of screen, so draw it halfway off the center
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);//Draws the bird in the position
        for(Tube tube : tubes){//For every tube in the tube array........
            sb.draw(tube.getTopTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);//Draw the top tube
            sb.draw(tube.getBotTube(),tube.getPosBotTube().x,tube.getPosBotTube().y);//Draw the bottom tube
        }
        sb.draw(ground,groundPos1.x,groundPos1.y);//Draw the first ground
        sb.draw(ground,groundPos2.x,groundPos2.y);//Draw the second

        sb.end();//Close the sprite batch
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube : tubes){//Dispose every tube in the tubes array
            tube.dispose();
        }
    }
    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth /2) > groundPos1.x + ground.getWidth()){//If the camera position is in front of the first ground,...
            groundPos1.add(ground.getWidth() *2,0);
        }
        if(cam.position.x - (cam.viewportWidth /2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() *2,0);
        }
    }
}
