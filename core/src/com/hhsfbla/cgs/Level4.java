package com.hhsfbla.cgs;

public class Level4 extends Level {
	public Level4() {
		id = 3;

		setSpawnPosition(1, 0, PlayerSpawn.DIR_RIGHT);

		final FirewallSwitch switch1 = new FirewallSwitch();
		final FirewallSwitch switch2 = new FirewallSwitch(false, "blue");

		add(new EnemySpawn(), 2, 7.5f);
		add(new Firewall(false, switch1), 2, 7);

		add(switch1, 5, 7);
		add(switch2, 10, 7);

		add(new EnemySpawn(), 13, 7.5f);
		add(new Firewall(false, switch2, Firewall.BLUE), 13, 7);

		add(new FileStack(), 4, 3);
		add(new FileStack(), 11, 3);

		add(new Wall(Wall.DIR_UP, true), 1, 7);
		add(new Wall(Wall.DIR_DOWN), 1, 6);
		add(new Wall(Wall.DIR_DOWN), 1, 5);
		add(new Wall(Wall.DIR_DOWN), 1, 4);
		add(new Wall(Wall.DIR_DOWN_LEFT), 1, 3);
		add(new Wall(Wall.DIR_UP_RIGHT), 2, 3);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 2, 2);
		add(new Wall(Wall.DIR_LEFT), 1, 2);
		add(new Wall(Wall.DIR_LEFT), 0, 2);

		add(new Wall(Wall.DIR_UP, true), 3, 7);
		add(new Wall(Wall.DIR_DOWN), 3, 6);
		add(new Wall(Wall.DIR_DOWN_LEFT), 3, 5);
		add(new Wall(Wall.DIR_RIGHT), 4, 5);
		add(new Wall(Wall.DIR_UP_RIGHT), 5, 5);
		add(new Wall(Wall.DIR_DOWN), 5, 4);
		add(new Wall(Wall.DIR_DOWN), 5, 3);
		add(new Wall(Wall.DIR_DOWN, true), 5, 2);

		add(new Wall(Wall.DIR_UP, true), 7.5f, 7);
		add(new Wall(Wall.DIR_DOWN), 7.5f, 6);
		add(new Wall(Wall.DIR_DOWN), 7.5f, 5);
		add(new Wall(Wall.DIR_DOWN), 7.5f, 4);
		add(new Wall(Wall.DIR_DOWN), 7.5f, 3);
		add(new Wall(Wall.DIR_DOWN, true), 7.5f, 2);

		add(new Wall(Wall.DIR_UP, true), 12, 7);
		add(new Wall(Wall.DIR_DOWN), 12, 6);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 12, 5);
		add(new Wall(Wall.DIR_LEFT), 11, 5);
		add(new Wall(Wall.DIR_UP_LEFT), 10, 5);
		add(new Wall(Wall.DIR_DOWN), 10, 4);
		add(new Wall(Wall.DIR_DOWN), 10, 3);
		add(new Wall(Wall.DIR_DOWN, true), 10, 2);

		add(new Wall(Wall.DIR_UP, true), 14, 7);
		add(new Wall(Wall.DIR_DOWN), 14, 6);
		add(new Wall(Wall.DIR_DOWN), 14, 5);
		add(new Wall(Wall.DIR_DOWN), 14, 4);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 14, 3);
		add(new Wall(Wall.DIR_UP_LEFT), 13, 3);
		add(new Wall(Wall.DIR_DOWN_LEFT), 13, 2);
		add(new Wall(Wall.DIR_RIGHT), 14, 2);
		add(new Wall(Wall.DIR_RIGHT), 15, 2);

		add(new Wall(Wall.DIR_UP), 3, -1);
		add(new Wall(Wall.DIR_UP_LEFT), 3, 0);
		for (int i = 4; i <= 12; i++) add(new Wall(Wall.DIR_RIGHT), i, 0);
		add(new Wall(Wall.DIR_UP_RIGHT), 13, 0);
		add(new Wall(Wall.DIR_UP), 13, -1);

		setExitPosition(14, 0, ExitPort.DIR_LEFT);
	}
}
