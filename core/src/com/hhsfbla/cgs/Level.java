package com.hhsfbla.cgs;

import com.badlogic.gdx.utils.Array;

public abstract class Level {
	public static final int GRID_COLS = 16;
	public static final int GRID_ROWS = 9;

	protected Array<AnimatedActor> actors;
	protected Player player;
	protected Array<Enemy> enemies;
	protected Array<Obstacle> obstacles;
	protected Array<Projectile> projectiles;
	protected StageScreen screen;
	Grid grid;

	public void init() {

	}

	public Level() {
		player = new Player();
		player.addListener(player.new LevelInputListener());
		enemies = new Array<>();
		obstacles = new Array<>();
		actors = new Array<>();
		grid = new Grid(this);
		grid.generate();

		actors.add(player);
		player.setLevel(this);
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
	}

	public void addEnemy(Enemy enemy, float x, float y) {
		enemy.setPosition(x, y);
		enemy.setLevel(this);
		enemies.add(enemy);
		addAnimatedActor(enemy);
	}

	public void addObstacle(Obstacle obstacle, float x, float y) {
		obstacle.setPosition(x, y);
		obstacles.add(obstacle);
		addAnimatedActor(obstacle);
	}

	public void addProjectile(Obstacle obstacle, float x, float y) {
		obstacle.setPosition(x, y);
		obstacles.add(obstacle);
		addAnimatedActor(obstacle);
	}

	public void remove(AnimatedActor actor) {
		actors.removeValue(actor, true);
		actor.setLevel(null);
	}

	public void remove(Enemy enemy) {
		enemies.removeValue(enemy, true);
		remove((AnimatedActor) enemy);
	}

	public void remove(Obstacle obstacle) {
		obstacles.removeValue(obstacle, true);
		remove((AnimatedActor) obstacle);
	}

	public void remove(Projectile projectile) {
		projectiles.removeValue(projectile, true);
		remove((AnimatedActor) projectile);
	}

	public void setScreen(StageScreen screen) {
		this.screen = screen;
	}

}
