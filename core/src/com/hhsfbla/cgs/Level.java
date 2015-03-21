package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public abstract class Level extends AnimatedActorGroup {
	// constants for level size
	public static final int GRID_COLS = 16;
	public static final int GRID_ROWS = 9;

	protected int id;
	protected Player player = new Player();
	protected Array<Enemy> enemies = new Array<>();
	protected Array<Obstacle> obstacles = new Array<>();
	protected Array<Item> items = new Array<>();
	protected Array<Projectile> projectiles = new Array<>();
	protected Array<FileStack> fileStacks = new Array<>();
	protected PlayerSpawn spawn = new PlayerSpawn();
	protected ExitPort exit = new ExitPort();
	protected Array<Factory> factories =  new Array<>();
	protected LevelScreen screen;
	Grid grid = new Grid(this);
	private boolean paused;
	private boolean complete;

	public Level() {
		add(player, 0, 0);
		add(spawn, 0, 0);
		add(exit, 0, 0);
		grid = new Grid(this);
		grid.generate();
	}

	public int getId() {
		return id;
	}

	public void init() {
		spawn.spawn(getPlayer());
	}

	public void setScreen(LevelScreen screen) {
		this.screen = screen;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayerPosition(float x, float y) {
		player.setPosition(x, y);
	}

	public PlayerSpawn getSpawn() {
		return spawn;
	}

	public void setSpawnPosition(float x, float y, int direction) {
		final double ang = Math.toRadians(direction);
		spawn.setDirection(direction);
		spawn.setPosition(x - (float) Math.cos(ang) / 2,
				y - (float) Math.sin(ang) / 2);
	}

	public ExitPort getExit() {
		return exit;
	}

	public void setExitPosition(float x, float y, int direction) {
		final double ang = Math.toRadians(direction);
		exit.setDirection(direction);
		exit.setPosition(x - (float) Math.cos(ang) / 2,
				y - (float) Math.sin(ang) / 2);
	}

	public Array<Obstacle> getObstacles() {
		return obstacles;
	}

	public Array<FileStack> getFileStacks() {
		return fileStacks;
	}
	
	public Array<Factory> getFactories() {
		return factories;
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
	
	public void add(Factory factory, float x, float y) {
		factories.add(factory);
		add((Obstacle)factory, x, y);
	}

	public void remove(AnimatedActor actor) {
		removeActor(actor);
	}

	public void remove(FileStack fs) {
		fileStacks.removeValue(fs, true);
		remove((Obstacle) fs);
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

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
		for (AnimatedActor actor : getActors()) actor.setPaused(paused);
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		if (this.complete != complete) {
			if (complete) {
				exit.addAction(Actions.sequence(exit.new AppearAction(), Actions.run(new Runnable() {
					@Override
					public void run() {
						onComplete();
					}
				})));
			} else {
				exit.addAction(exit.new DisappearAction());
			}
		}
		this.complete = complete;
	}

	public void onSpawn() {}
	public void onComplete() {}

	@Override
	public void act(float delta) {
		if (!paused) {
			super.act(delta);

			// Level completion checking
			if (getEnemies().size > 0) {
				setComplete(false);
				return;
			} else {
				// LibGDX won't let me use a foreach loop
				// something about "#iterator() cannot be used nested"
				for (int i = 0; i < obstacles.size; i++) {
					final Obstacle o = obstacles.get(i);

					if (o instanceof Factory) {
						final Factory factory = (Factory) o;
						if (factory.isInfected()) {
							setComplete(false);
							return;
						}
					} else if (o instanceof EnemySpawn) {
						final EnemySpawn spawn = (EnemySpawn) o;
						if (spawn.canSpawn()) {
							setComplete(false);
							return;
						}
					}
				}

				for (int i = 0; i < items.size; i++) {
					final Item item = items.get(i);
					if (item instanceof ImportantFile) {
						setComplete(false);
						return;
					}
				}

				setComplete(true);
			}
		}
	}

	public void end() {
		screen.game.setScreen(new OverworldScreen(screen.game, screen.stage, 5, id));
	}
}
