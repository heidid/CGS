package com.hhsfbla.cgs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * A Screen that uses a Stage
 */
public class StageScreen extends ScreenAdapter {
	protected Game game;
	protected Stage stage;

	public StageScreen(Game game, Stage stage) {
		this.game = game;
		setStage(stage);
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		stage.clear();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
}
