package com.hhsfbla.cgs;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;

public class Level {
	public static final int GRID_COLS = 16;
	public static final int GRID_ROWS = 9;

	private Array<AnimatedActor> actors;
	private Player player;
	private Array<Enemy> enemies;
	private Array<Obstacle> obstacles;
	private Screen screen;
	Grid grid;

	public Level() {
		player = new Player();
		enemies = new Array<>();
		obstacles = new Array<>();
		actors = new Array<>();
		grid = new Grid(this);
		grid.generate();
		actors.add(player);
	}

	public Array<AnimatedActor> getActors() {
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

	public Array<Enemy> getEnemies() {
		return enemies;
	}

	public void addAnimatedActor(AnimatedActor aa) {
		actors.add(aa);
		aa.setScreen(screen);
	}
	
	public void addEnemy(Enemy enemy, float x, float y) {
		enemy.setPosition(x, y);
		enemy.setScreen(screen);
		enemies.add(enemy);
		addAnimatedActor(enemy);
	}

	public void addObstacle(Obstacle obstacle, float x, float y) {
		obstacle.setPosition(x, y);
		obstacle.setScreen(screen);
		obstacles.add(obstacle);
		addAnimatedActor(obstacle);
	}
	
	public void setScreen(Screen screen) {
		this.screen = screen;
		for(AnimatedActor a : actors)
			a.setScreen(screen);
	}

}
