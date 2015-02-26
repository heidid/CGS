package com.hhsfbla.cgs;

public class Level1 extends Level {
	Enemy enemy = new Enemy();

	@Override
	public void init() {
		enemy.addAction(new MoveToAttack(this, enemy, player.getX(), player.getY()));
	}

	public Level1() {
		setPlayerPosition(1, 6);
		add(enemy, 2, 1);

		for (int i = 0; i < 15; i++) {
			add(new Wall(), i, 7);
			add(new Wall(), i, 0);
		}

		for (int i = 0; i < 11; i++) {
			add(new Wall(), i, 5);
			add(new Wall(), i, 2);
		}

		add(new Wall(Wall.DIR_UP_RIGHT), 15, 7);
		for (int i = 0; i < 6; i++) add(new Wall(Wall.DIR_UP), 15, 1 + i);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 15, 0);

		add(new Wall(Wall.DIR_UP_RIGHT), 11, 5);
		for (int i = 0; i < 2; i++) add(new Wall(Wall.DIR_UP), 11, 3 + i);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 11, 2);

		final File key = new File();
		final Switch swtch = new FirewallSwitch(true);
		add(swtch, 13, 3);
		add(new Firewall(swtch), 1, 1);
		add(key, 13, 5);
	}
}
