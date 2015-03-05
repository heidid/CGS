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

		add(player, 0, 0);
	}

	public void init() {
		// LibGDX won't let me use a foreach loop
		// something about "#iterator() cannot be used nested"
		for (int i = 0; i < obstacles.size; i++) {
			final Obstacle o = obstacles.get(i);
			if (o instanceof PlayerSpawn) ((PlayerSpawn) o).spawn(getPlayer());
		}
	}

	public void setScreen(StageScreen screen) {
		this.screen = screen;
	}

	public Player getPlayer() {
		return player;
	}

	public Array<Obstacle> getObstacles() {
		return obstacles;
	}

	public Array<FileStack> getFileStacks() {
		return fileStacks;
	}

	public void setPlayerPosition(float x, float y) {
		player.setPosition(x, y);
	}

	public Array<Enemy> getEnemies() {
		return enemies;
	}

	public void add(AnimatedActor aa, float x, float y) {
		aa.setLevel(this);
		aa.setPosition(x, y);
		addActor(aa);
	}

	public void add(FileStack fs, float x, float y) {
		fileStacks.add(fs);
		add((Obstacle) fs, x, y);
	}

	public void add(Enemy enemy, float x, float y) {
		enemies.add(enemy);
		add((AnimatedActor) enemy, x, y);
	}

	public void add(Obstacle obstacle, float x, float y) {
		obstacles.add(obstacle);
		add((AnimatedActor) obstacle, x, y);
	}

	public void add(Item item, float x, float y) {
		items.add(item);
		add((AnimatedActor) item, x, y);
	}

	public void add(Projectile projectile, float x, float y) {
		projectiles.add(projectile);
		add((AnimatedActor) projectile, x, y);
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

	public void end() {
		screen.game.setScreen(new OverworldScreen(screen.game, screen.stage));
	}
}
