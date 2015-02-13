package com.hhsfbla.cgs;

public class Levels {
	public static Level Level1() {
		Level level = new Level();
		level.setPlayerPosition(1,1);
		Enemy enemy = new Enemy();
		level.addEnemy(enemy, 7, 0);
		for (int i = 0; i < 5; i++) {
			final int dir = i == 0 ? Wall.DIR_LEFT : Wall.DIR_RIGHT;
			final boolean end = i % 5 == 0;
			level.addObstacle(new Wall(dir, end), 6 + i, 6);
			level.addObstacle(new Wall(dir, end), 6 + i, 8);
			level.addObstacle(new Wall(dir, end), 6 + i, 4);
			level.addObstacle(new Wall(dir, end), 6 + i, 2);
		}
		for (int i = 0; i < 6; i++) {
			level.addObstacle(new Wall(i == 0 ? Wall.DIR_DOWN : Wall.DIR_UP, i % 6 == 0), 12, 2 + i);
		}
		return level;
	}
	public static Level Level2() {
		Level level = new Level();
		level.setPlayerPosition(2,2);
		Enemy enemy = new Enemy();
		level.addEnemy(enemy, 7, 0);
		for (int i = 0; i < 5; i++) {
			final int dir = i == 0 ? Wall.DIR_LEFT : Wall.DIR_RIGHT;
			final boolean end = i % 5 == 0;
			level.addObstacle(new Wall(dir, end), 6 + i, 6);
			level.addObstacle(new Wall(dir, end), 6 + i, 8);
			level.addObstacle(new Wall(dir, end), 6 + i, 4);
			level.addObstacle(new Wall(dir, end), 6 + i, 2);
		}
		for (int i = 0; i < 6; i++) {
			level.addObstacle(new Wall(i == 0 ? Wall.DIR_DOWN : Wall.DIR_UP, i % 6 == 0), 12, 2 + i);
		}
		return level;
	}
	public static Level Level3() {
		Level level = new Level();
		level.setPlayerPosition(3,3);
		Enemy enemy = new Enemy();
		level.addEnemy(enemy, 7, 0);
		for (int i = 0; i < 5; i++) {
			final int dir = i == 0 ? Wall.DIR_LEFT : Wall.DIR_RIGHT;
			final boolean end = i % 5 == 0;
			level.addObstacle(new Wall(dir, end), 6 + i, 6);
			level.addObstacle(new Wall(dir, end), 6 + i, 8);
			level.addObstacle(new Wall(dir, end), 6 + i, 4);
			level.addObstacle(new Wall(dir, end), 6 + i, 2);
		}
		for (int i = 0; i < 6; i++) {
			level.addObstacle(new Wall(i == 0 ? Wall.DIR_DOWN : Wall.DIR_UP, i % 6 == 0), 12, 2 + i);
		}
		return level;
	}
	public static Level Level4() {
		Level level = new Level();
		level.setPlayerPosition(4,4);
		Enemy enemy = new Enemy();
		level.addEnemy(enemy, 7, 0);
		for (int i = 0; i < 5; i++) {
			final int dir = i == 0 ? Wall.DIR_LEFT : Wall.DIR_RIGHT;
			final boolean end = i % 5 == 0;
			level.addObstacle(new Wall(dir, end), 6 + i, 6);
			level.addObstacle(new Wall(dir, end), 6 + i, 8);
			level.addObstacle(new Wall(dir, end), 6 + i, 4);
			level.addObstacle(new Wall(dir, end), 6 + i, 2);
		}
		for (int i = 0; i < 6; i++) {
			level.addObstacle(new Wall(i == 0 ? Wall.DIR_DOWN : Wall.DIR_UP, i % 6 == 0), 12, 2 + i);
		}
		return level;
	}
}
