package com.hhsfbla.cgs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Main extends Game {
	private Stage stage;

	@Override
	public void create() {
		stage = new Stage();
		setScreen(new LevelScreen(stage));
	}
}
