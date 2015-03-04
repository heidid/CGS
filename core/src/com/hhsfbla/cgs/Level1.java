package com.hhsfbla.cgs;

public class Level1 extends Level {

	public Level1() {
		add(new PlayerSpawn(), 0, 6);

		final Enemy enemy = new Enemy();
		enemy.addAction(new MoveToAttack(player.getX(), player.getY()));
		add(enemy, 2, 1);

		for (int i = 0; i < 11; i++) {
			add(new Wall(), i, 5);
			add(new Wall(), i, 2);
		}

		add(new Wall(Wall.DIR_UP_RIGHT), 11, 5);
		for (int i = 0; i < 2; i++) add(new Wall(Wall.DIR_UP), 11, 3 + i);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 11, 2);

		final File key = new File();
		final Switch swtch = new FirewallSwitch();
		add(swtch, 13, 3);
		add(new Firewall(swtch), 1, 1);
		add(key, 13, 5);

		add(new Port(), 0, 0);
	}
}
