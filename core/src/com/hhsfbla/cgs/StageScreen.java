package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * A Screen that uses a Stage
 */
public class StageScreen extends ScreenAdapter {
	protected Stage stage;
	public TextureAtlas atlas;

	public StageScreen(Stage stage, TextureAtlas atlas) {
		this.stage = stage;
		this.atlas = atlas;
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	ShapeRenderer sr = new ShapeRenderer();
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		//draw grid
		/*sr.begin(ShapeType.Filled);
		for(int i=0;i<Level.GRID_ROWS;i++)
			sr.rectLine(0, i*this.getStage().getHeight()/Level.GRID_ROWS, this.getStage().getWidth(), i*this.getStage().getHeight()/Level.GRID_ROWS, 5);
		for(int i=0;i<Level.GRID_COLS;i++)
			sr.rectLine(i*this.getStage().getWidth()/Level.GRID_COLS, 0, i*this.getStage().getWidth()/Level.GRID_COLS, this.getStage().getHeight(), 5);
		sr.flush();
		sr.end();*/
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
