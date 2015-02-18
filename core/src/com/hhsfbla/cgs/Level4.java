package com.hhsfbla.cgs;

public class Level4 extends Level {
	Enemy enemy = new Enemy();

	@Override
	public void init() {
		enemy.addAction(new PathFindingAction(this, enemy, 0, 0, 1));
	}

	public Level4() {
		setPlayerPosition(1, 7);
		addEnemy(enemy, 1, 1);

		for (int i = 0; i < 5; i++) {
			final int dir = i == 0 ? Wall.DIR_LEFT : Wall.DIR_RIGHT;
			final boolean end = i % 4 == 0;

			addObstacle(new Wall(dir, end), 6 + i, 7);
			addObstacle(new Wall(dir, end), 6 + i, 5);
			addObstacle(new Wall(dir, end), 6 + i, 3);
			addObstacle(new Wall(dir, end), 6 + i, 1);
		}

		for (int i = 0; i < 7; i++) {
			addObstacle(new Wall(i == 0 ? Wall.DIR_DOWN : Wall.DIR_UP,
					i % 6 == 0), 12, 1 + i);
		}
	}
}
