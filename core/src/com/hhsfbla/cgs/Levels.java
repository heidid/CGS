package com.hhsfbla.cgs;

public class Levels {
	public static Level Level1() {
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
		for (int i = 0; i < 6; i++) {
			level.addObstacle(new Wall(Wall.DIR_LEFT), 12, 2 + i);
		}
		return level;
	}
	public static Level Level2() {
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
		for (int i = 0; i < 6; i++) {
			level.addObstacle(new Wall(Wall.DIR_LEFT), 12, 2 + i);
		}
		return level;
	}
	public static Level Level3() {
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
		for (int i = 0; i < 6; i++) {
			level.addObstacle(new Wall(Wall.DIR_LEFT), 12, 2 + i);
		}
		return level;
	}
	public static Level Level4() {
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
		for (int i = 0; i < 6; i++) {
			level.addObstacle(new Wall(Wall.DIR_LEFT), 12, 2 + i);
		}
		return level;
	}
}
