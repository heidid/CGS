package com.hhsfbla.cgs;

public class Level4 extends Level {
	Enemy enemy = new Enemy();

	@Override
	public void init() {
		enemy.addAction(new PathFindingAction(this, enemy, 0, 0, 1));
	}

	public Level4() {
		super();
		this.setPlayerPosition(1, 1);
		this.addEnemy(enemy, 7, 0);
		for (int i = 0; i < 5; i++) {
			final int dir = i == 0 ? Wall.DIR_LEFT : Wall.DIR_RIGHT;
			final boolean end = i % 4 == 0;
			this.addObstacle(new Wall(dir, end), 6 + i, 6);
			this.addObstacle(new Wall(dir, end), 6 + i, 8);
			this.addObstacle(new Wall(dir, end), 6 + i, 4);
			this.addObstacle(new Wall(dir, end), 6 + i, 2);
		}
		for (int i = 0; i < 6; i++) {
			this.addObstacle(new Wall(i == 0 ? Wall.DIR_DOWN : Wall.DIR_UP,
					i % 6 == 0), 12, 2 + i);
		}
	}
}
