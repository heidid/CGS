package com.hhsfbla.cgs;

public class Level2 extends Level {
	Enemy enemy = new Enemy();

	@Override
	public void init() {
		enemy.addAction(new PathfindingAction2(0, 0, 1));
	}

	public Level2() {
		setPlayerPosition(1, 7);
		add(enemy, 1, 1);

		for (int i = 0; i < 5; i++) {
			final int dir = i == 0 ? Wall.DIR_LEFT : Wall.DIR_RIGHT;
			final boolean end = i % 4 == 0;

			add(new Wall(dir, end), 6 + i, 7);
			add(new Wall(dir, end), 6 + i, 5);
			add(new Wall(dir, end), 6 + i, 3);
			add(new Wall(dir, end), 6 + i, 1);
		}

		for (int i = 0; i < 7; i++) {
			add(new Wall(i == 0 ? Wall.DIR_DOWN : Wall.DIR_UP,
					i % 6 == 0), 12, 1 + i);
		}
	}
}
