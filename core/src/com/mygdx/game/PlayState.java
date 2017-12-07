package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Extends the State class
 * This is the state in which the game is played in
 */

public class PlayState {
    private Bird bird;
    private final static int GROUND_Y_OFFSET = -50;//Offset to place the ground correctly
    private Texture ground;
    private static final int TUBE_SPACING = 100;//Spacing between tubes
    private static final int TUBE_COUNT = 4;
    private Texture background;
    private Vector2 groundPos1, groundPos2;
    protected OrthographicCamera cam;//Camera
    protected Vector2 mouse;//Mouse
    private Tube tube1, tube2, tube3, tube4;
    private boolean gameOver = false;
    public PlayState() {
        ground = new Texture("ground.jpg");
        bird = new Bird(50,300);
        cam = new OrthographicCamera();
        mouse = new Vector2();
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);//Sets the camera's origin lower left
        //and sets the camera centered
        groundPos1 = new Vector2(cam.position.x - (cam.viewportWidth / 2),GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - (cam.viewportWidth / 2)) + ground.getWidth(),GROUND_Y_OFFSET);
        background = new Texture("bgPlay.png");
        tube1 = new Tube((TUBE_SPACING + Tube.TUBE_WIDTH));//Adding the tubes to the screen at different x cordinates
        tube2 = new Tube((TUBE_SPACING + Tube.TUBE_WIDTH) * 2);
        tube3 = new Tube((TUBE_SPACING + Tube.TUBE_WIDTH) * 3);
        tube4 = new Tube((TUBE_SPACING + Tube.TUBE_WIDTH) * 4);
    }

    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    public PlayState update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;//Center the camera on the bird
        updateTube(tube1);
        updateTube(tube2);
        updateTube(tube3);
        updateTube(tube4);
        if(gameOver){
            return new PlayState();
        }
        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){//If bird touches the ground
            dispose();
            return new PlayState();
        }
        cam.update();//Tells to the camera that we repositioned
        return this;
    }

    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);//The cordinate game screen starts bottom left
        sb.begin();
        sb.draw(background,cam.position.x - (cam.viewportWidth/2),0);//Viewport is centered in middle of screen, so draw it halfway off the center
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);//Draws the bird in the position
        renderTube(tube1,sb);
        renderTube(tube2,sb);
        renderTube(tube3,sb);
        renderTube(tube4,sb);
        sb.draw(ground,groundPos1.x,groundPos1.y);//Draw the first ground
        sb.draw(ground,groundPos2.x,groundPos2.y);//Draw the second
        sb.end();//Close the sprite batch
    }

    public void dispose() {
        background.dispose();
        bird.dispose();
        ground.dispose();
        tube1.dispose();
        tube2.dispose();
        tube3.dispose();
        tube4.dispose();
    }
    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth /2) > groundPos1.x + ground.getWidth()){//If the camera position is in front of the first ground,...
            groundPos1.add(ground.getWidth() *2,0);
        }
        if(cam.position.x - (cam.viewportWidth /2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() *2,0);
        }
    }
    private void updateTube(Tube tube){
        if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth())
            //If the camera's left origin is greater than the right end of the tube's X POSITION, reposition the tube
            tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));//Evenly puts the next Top tube
        if(tube.collides(bird.getBounds())){
            dispose();
            gameOver = true;
        }
        else{
            gameOver = false;
        }
    }
    private void renderTube(Tube tube, SpriteBatch sb){
        sb.draw(tube.getTopTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);//Draw the top tube
        sb.draw(tube.getBotTube(),tube.getPosBotTube().x,tube.getPosBotTube().y);//Draw the bottom tube
    }
}