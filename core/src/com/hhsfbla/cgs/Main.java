package com.hhsfbla.cgs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends Game {
	private Stage stage;

	@Override
	public void create() {
		stage = new Stage(new FitViewport(1280, 720));
		Level level = new Level();
		level.setPlayerPosition(1, 1);
		Enemy enemy = new Enemy();
		enemy.addAction(Actions.moveTo(1, 1, 10));
		level.addEnemy(enemy, 11, 1);
		setScreen(new LevelScreen(stage, level));
	}
}
