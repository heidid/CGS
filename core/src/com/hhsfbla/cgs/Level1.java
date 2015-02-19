package com.hhsfbla.cgs;

public class Level1 extends Level {
	Enemy enemy = new Enemy();

	@Override
	public void init() {
		enemy.addAction(new MoveToAttack(this, enemy, player.getX(), player.getY()));
	}

	public Level1() {
		setPlayerPosition(1, 6);
		addEnemy(enemy, 1, 1);

		for (int i = 0; i < 15; i++) {
			addObstacle(new Wall(), i, 7);
			addObstacle(new Wall(), i, 0);
		}

		for (int i = 0; i < 11; i++) {
			addObstacle(new Wall(), i, 5);
			addObstacle(new Wall(), i, 2);
		}

		addObstacle(new Wall(Wall.DIR_UP_RIGHT), 15, 7);
		for (int i = 0; i < 6; i++) addObstacle(new Wall(Wall.DIR_UP), 15, 1 + i);
		addObstacle(new Wall(Wall.DIR_DOWN_RIGHT), 15, 0);

		addObstacle(new Wall(Wall.DIR_UP_RIGHT), 11, 5);
		for (int i = 0; i < 2; i++) addObstacle(new Wall(Wall.DIR_UP), 11, 3 + i);
		addObstacle(new Wall(Wall.DIR_DOWN_RIGHT), 11, 2);

		addObstacle(new FloorSwitch(), 13, 3);
	}
}
