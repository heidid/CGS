package com.hhsfbla.cgs;

public class Level1 extends Level {
	Enemy enemy = new Enemy();

	@Override
	public void init() {
		enemy.addAction(new MoveToAttack(this, enemy, player.getX(), player.getY()));
	}

	public Level1() {
		setPlayerPosition(1, 7);
		addEnemy(enemy, 1, 1);

		for (int i = 0; i < 5; i++) {
			final int dir = i == 0 ? Wall.DIR_LEFT : Wall.DIR_RIGHT;
			final boolean end = i % 4 == 0;

			addObstacle(new Wall(dir, end && i != 4), 6 + i, 7);
			addObstacle(new Wall(dir, end), 6 + i, 5);
			addObstacle(new Wall(dir, end), 6 + i, 3);
			addObstacle(new Wall(dir, end), 6 + i, 1);
		}

		addObstacle(new Wall(Wall.DIR_RIGHT), 11, 7);

		for (int i = 0; i < 7; i++) {
			addObstacle(new Wall(i == 6 ? Wall.DIR_UP_RIGHT : Wall.DIR_DOWN,
					i % 6 == 0), 12, 1 + i);
		}

		addObstacle(new FloorSwitch(), 8, 6);
	}
}
