package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Level {
	public static final int GRID_COLS = 16;
	public static final int GRID_ROWS = 15;

	private Array<Actor> actors;
	private Player player;
	private Array<Enemy> enemies;
	private Array<Obstacle> obstacles;

	public Level() {
		player = new Player();
		enemies = new Array<>();
		obstacles = new Array<>();
		actors = new Array<>();
		actors.add(player);
	}

	public Array<Actor> getActors() {
		return actors;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Array<Obstacle> getObstacles(){
		return obstacles;
	}

	public void setPlayerPosition(float x, float y) {
		player.setPosition(x, y);
	}

	public void addEnemy(Enemy enemy, float x, float y) {
		enemy.setPosition(x, y);
		enemies.add(enemy);
		actors.add(enemy);
	}

	public void addObstacle(Obstacle obstacle, float x, float y) {
		obstacle.setPosition(x, y);
		obstacles.add(obstacle);
		actors.add(obstacle);
	}

}
