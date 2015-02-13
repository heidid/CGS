package com.hhsfbla.cgs;

public class Level1 extends Level {
	Enemy enemy = new Enemy();

	@Override
	public void init() {
		enemy.addAction(new MoveToAttack(this, enemy, player.getX(), player.getY()));
	}

	public Level1() {
		super();
		this.setPlayerPosition(1, 1);
		this.addEnemy(enemy, 7, 0);
		for (int i = 0; i < 5; i++) {
			final int dir = i == 0 ? Wall.DIR_LEFT : Wall.DIR_RIGHT;
			final boolean end = i % 5 == 0;
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
