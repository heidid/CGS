package com.hhsfbla.cgs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class Main extends Game {
	private Stage stage;

	@Override
	public void create() {
		stage = new Stage(new ScalingViewport(Scaling.fit, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Level level = new Level();
		level.setPlayerPosition(0, 100);
		level.addEnemy(new Enemy(), 100, 0);
		setScreen(new LevelScreen(stage, level));
	}
}
