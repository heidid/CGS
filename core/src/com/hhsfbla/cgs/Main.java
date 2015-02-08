package com.hhsfbla.cgs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;
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
		}
		setScreen(new LevelScreen(stage, level));
		CellPath cp = level.grid.getPath(7,0,7,10);
		SequenceAction sa = new SequenceAction();
		for(Cell c : cp.array){
			System.out.println(c.x+","+c.y);
			sa.addAction(Actions.moveTo(c.x, c.y, 1));
		}
		enemy.addAction(sa);
	}
}
