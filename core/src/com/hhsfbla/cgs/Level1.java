package com.hhsfbla.cgs;

public class Level1 extends Level {

	public Level1() {
		add(new PlayerSpawn(), 0, 6);

		add(new Enemy(new AttackFileStackAction()), 2, 1);
		add(new EnemySpawn(), 13, 7);

		for (int i = 0; i < 11; i++) {
			add(new Wall(), i, 5);
			add(new Wall(), i, 2);
		}

		add(new Wall(Wall.DIR_UP_RIGHT), 11, 5);
		for (int i = 0; i < 2; i++) add(new Wall(Wall.DIR_UP), 11, 3 + i);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 11, 2);

		final File key = new File();
		final Switch swtch = new FirewallSwitch();
		add(new FileStack(), 7, 0);
		add(swtch, 13, 1);
		add(new Firewall(false, swtch), 13, 6);
		add(key, 13, 5);

		add(new ExitPort(), 0, 0);
	}
}
