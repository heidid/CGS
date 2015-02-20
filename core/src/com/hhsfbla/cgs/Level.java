package com.hhsfbla.cgs;

import com.badlogic.gdx.utils.Array;

public abstract class Level extends AnimatedActorGroup {
	//constants for level size
	public static final int GRID_COLS = 16;
	public static final int GRID_ROWS = 9;

	protected Player player;
	protected Array<Enemy> enemies;
	protected Array<Obstacle> obstacles;
	protected Array<Item> items;
	protected Array<Projectile> projectiles;
	protected Array<FileStack> fileStacks;
	protected StageScreen screen;
	Grid grid;

	public Level() {
		player = new Player();
		player.addListener(player.new LevelInputListener());
		enemies = new Array<>();
		obstacles = new Array<>();
		items = new Array<>();
		projectiles = new Array<>();
		fileStacks = new Array<>();
		grid = new Grid(this);
		grid.generate();

		add(player);
		player.setLevel(this);
	}

	public void init() {}

	public Player getPlayer() {
		return player;
	}

	public Array<Obstacle> getObstacles(){
		return obstacles;
	}

	public Array<FileStack> getFileStacks(){
		return fileStacks;
	}

	public void setPlayerPosition(float x, float y) {
		player.setPosition(x, y);
	}

	public Array<Enemy> getEnemies() {
		return enemies;
	}

	public void add(AnimatedActor aa) {
		aa.setLevel(this);
		addActor(aa);
	}

	public void add(FileStack fs, float x, float y) {
		fileStacks.add(fs);
		add((Obstacle) fs, x, y);
		add(fs);
	}

	public void add(Enemy enemy, float x, float y) {
		enemy.setPosition(x, y);
		enemies.add(enemy);
		add(enemy);
	}

	public void add(Obstacle obstacle, float x, float y) {
		obstacle.setPosition(x, y);
		obstacles.add(obstacle);
		add(obstacle);
	}

	public void add(Item item, float x, float y) {
		item.setPosition(x, y);
		items.add(item);
		add(item);
	}

	public void add(Projectile projectile, float x, float y) {
		projectile.setPosition(x, y);
		projectiles.add(projectile);
		add(projectile);
	}

	public void remove(AnimatedActor actor) {
		removeActor(actor);
	}

	public void remove(FileStack fs) {
		fileStacks.removeValue(fs, true);
		remove((AnimatedActor) fs);
	}

	public void remove(Enemy enemy) {
		enemies.removeValue(enemy, true);
		remove((AnimatedActor) enemy);
	}

	public void remove(Obstacle obstacle) {
		obstacles.removeValue(obstacle, true);
		remove((AnimatedActor) obstacle);
	}

	public void remove(Item item) {
		items.removeValue(item, true);
		remove((AnimatedActor) item);
	}

	public void remove(Projectile projectile) {
		projectiles.removeValue(projectile, true);
		remove((AnimatedActor) projectile);
	}

	public void setScreen(StageScreen screen) {
		this.screen = screen;
	}

}
