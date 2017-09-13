package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Flappy Bird";
	private PlayState playState;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		playState = new PlayState();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		playState = playState.update(Gdx.graphics.getDeltaTime());
		playState.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
        playState.dispose();
	}
}
