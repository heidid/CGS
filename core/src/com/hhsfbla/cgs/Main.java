package com.hhsfbla.cgs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends Game {
	private Stage stage;

	@Override
	public void create() {
		stage = new Stage(new FitViewport(1280, 720));
		Level level = new Level();
		level.setPlayerPosition(7, 7);
		Enemy enemy = new Enemy();
		level.addEnemy(enemy, 7, 0);
		for (int i = 0; i < 5; i++) {
			level.addObstacle(new Wall(), 6 + i, 6);
			level.addObstacle(new Wall(), 6 + i, 8);
			level.addObstacle(new Wall(), 6 + i, 4);
			level.addObstacle(new Wall(), 6 + i, 2);
		}

		for (int i = 0; i < 8; i++) {
			level.addObstacle(new Wall(Wall.DIR_LEFT), 12, 2 + i);
		}
		setScreen(new LevelScreen(stage, new TextureAtlas(), level));
		enemy.addAction(new PathFindingAction(level, enemy, 7, 7, 1));
	}
}
