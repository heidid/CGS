package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu extends ScreenAdapter {
	private SpriteBatch batch;
	private Texture image;

	public MainMenu(SpriteBatch batch) {
		this.batch = batch;
	}

	@Override
	public void show() {
		image = new Texture("badlogic.jpg");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(image, 0, 0);
		batch.end();
	}

	@Override
	public void hide() {

	}
}
