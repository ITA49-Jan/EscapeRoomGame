package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.engine.Canvas;
import com.mygdx.engine.SceneManager;
import com.mygdx.scenes.*;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	private SpriteBatch UIbatch;

	public static Canvas canvas;

	@Override
	public void create() {

		batch = new SpriteBatch();
		UIbatch = new SpriteBatch();

		canvas = new Canvas();
        Gdx.input.setInputProcessor(canvas);

		Timer.start();
		Timer.createDisplay();
		CodeGenerator.init();
		RoomNavigation.init();
		Inventory.init();

		SceneManager.load(new MainMenu()); //MainMenu
	}

	@Override
	public void render() {

        Game.canvas.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

		Timer.update();

		Timer.updateDisplay();

		SceneManager.update();

		Inventory.update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//anything else
		batch.begin();
		SceneManager.draw(batch);
		batch.end();

		// draw ui canvas
		UIbatch.begin();
		SceneManager.stage_draw(UIbatch);
		UIbatch.end();
	}

	@Override
	public void resize(int width, int height) {
		SceneManager.resize(width, height);
		//inventory.resize(width, height);
        Game.canvas.getViewport().update(width, height, false);
	}

	@Override
	public void dispose() {
		batch.dispose();
		SceneManager.dispose();
	}
}
