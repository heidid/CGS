package com.hhsfbla.cgs;

import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class StageScreen extends ScreenAdapter {
	protected Stage stage;

	public StageScreen(Stage stage) {
		this.stage = stage;
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

	@Override
	public void hide() {

	}

	public Stage getStage() {
		return stage;
	}

	public final void setStage(Stage stage) {
		this.stage = stage;
		stage.clear();
	}
}
