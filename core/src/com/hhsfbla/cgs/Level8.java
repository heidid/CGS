package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Level8 extends Level {
	public Level8() {
		id = 2;

		setSpawnPosition(1, 7, PlayerSpawn.DIR_DOWN);

		add(new Wall(Wall.DIR_DOWN), 0, 7);
		add(new Wall(Wall.DIR_DOWN), 0, 6);
		add(new Wall(Wall.DIR_DOWN), 0, 5);
		add(new Wall(Wall.DIR_DOWN), 0, 4);
		add(new Wall(Wall.DIR_DOWN), 0, 3);
		add(new Wall(Wall.DIR_DOWN), 0, 2);
		add(new Wall(Wall.DIR_DOWN), 0, 1);

		add(new Wall(Wall.DIR_UP_LEFT), 2, 8);
		add(new Wall(Wall.DIR_DOWN), 2, 7);
		add(new Wall(Wall.DIR_DOWN, true), 2, 6);
		add(new Wall(Wall.DIR_UP_LEFT), 2, 4);
		add(new Wall(Wall.DIR_DOWN), 2, 3);
		add(new Wall(Wall.DIR_DOWN), 2, 2);
		add(new Wall(Wall.DIR_DOWN), 2, 1);

		add(new Wall(Wall.DIR_RIGHT), 3, 8);
		add(new Wall(Wall.DIR_RIGHT), 4, 8);
		add(new Wall(Wall.DIR_RIGHT), 5, 8);
		add(new Wall(Wall.DIR_RIGHT), 6, 8);
		add(new Wall(Wall.DIR_RIGHT), 7, 8);
		add(new Wall(Wall.DIR_RIGHT), 8, 8);
		add(new Wall(Wall.DIR_RIGHT), 9, 8);
		add(new Wall(Wall.DIR_RIGHT), 10, 8);
		add(new Wall(Wall.DIR_RIGHT), 11, 8);

		add(new Wall(Wall.DIR_DOWN_LEFT), 0, 0);
		add(new Wall(Wall.DIR_RIGHT), 1, 0);
		add(new Wall(Wall.DIR_LEFT_UP_RIGHT), 2, 0);
		add(new Wall(Wall.DIR_RIGHT), 3, 0);
		add(new Wall(Wall.DIR_RIGHT), 4, 0);
		add(new Wall(Wall.DIR_RIGHT), 5, 0);
		add(new Wall(Wall.DIR_RIGHT), 6, 0);
		add(new Wall(Wall.DIR_RIGHT), 7, 0);
		add(new Wall(Wall.DIR_RIGHT), 8, 0);
		add(new Wall(Wall.DIR_RIGHT), 9, 0);
		add(new Wall(Wall.DIR_RIGHT), 10, 0);
		add(new Wall(Wall.DIR_RIGHT), 11, 0);
		add(new Wall(Wall.DIR_RIGHT), 12, 0);
		add(new Wall(Wall.DIR_RIGHT), 13, 0);

		add(new Wall(Wall.DIR_RIGHT), 3, 4);
		add(new Wall(Wall.DIR_LEFT_UP_RIGHT), 4, 4);
		add(new Wall(Wall.DIR_UP), 4, 5);
		add(new Wall(Wall.DIR_UP, true), 4, 6);
		add(new Wall(Wall.DIR_RIGHT), 5, 4);
		add(new Wall(Wall.DIR_RIGHT, true), 6, 4);
		add(new Wall(Wall.DIR_LEFT, true), 6, 6);
		add(new Wall(Wall.DIR_RIGHT), 7, 6);
		add(new Wall(Wall.DIR_RIGHT), 8, 6);
		add(new Wall(Wall.DIR_RIGHT), 9, 6);
		add(new Wall(Wall.DIR_UP_RIGHT), 10, 6);
		add(new Wall(Wall.DIR_DOWN), 10, 5);
		add(new Wall(Wall.DIR_DOWN), 10, 4);
		add(new Wall(Wall.DIR_DOWN), 10, 3);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 10, 2);
		add(new Wall(Wall.DIR_RIGHT), 9, 2);
		add(new Wall(Wall.DIR_RIGHT), 8, 2);
		add(new Wall(Wall.DIR_RIGHT), 7, 2);
		add(new Wall(Wall.DIR_RIGHT), 6, 2);
		add(new Wall(Wall.DIR_RIGHT), 5, 2);
		add(new Wall(Wall.DIR_LEFT, true), 4, 2);

		add(new Wall(Wall.DIR_UP_RIGHT), 12, 8);
		add(new Wall(Wall.DIR_DOWN), 12, 7);
		add(new Wall(Wall.DIR_DOWN), 12, 6);
		add(new Wall(Wall.DIR_DOWN), 12, 5);
		add(new Wall(Wall.DIR_DOWN), 12, 4);
		add(new Wall(Wall.DIR_DOWN), 12, 3);

		add(new Enemy(Actions.sequence(
			new PathfindingAction(8, 4),
			Actions.run(new Runnable() {
				@Override
				public void run() {
					boolean lose = false;
					for (Item i : items) if (i instanceof ImportantFile) lose = true;
					if (lose) screen.addDialog(new GameOverBox(Level8.this));
				}
			}))), 13, 7);

		add(new ImportantFile(), 8, 4);

		setExitPosition(14, 1, ExitPort.DIR_LEFT);
	}

	@Override
	public void onSpawn() {
		// TODO: Add Level3 dialog
	}
}
