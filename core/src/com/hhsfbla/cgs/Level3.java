package com.hhsfbla.cgs;

public class Level3 extends Level {
	public Level3() {

		add(new PlayerSpawn(), 0.5f, 6);

		add(new PasswordDoor(3), 11, 1);
		add(new PasswordFile(1), 2, 4);
		add(new PasswordFile(3), 2, 2);

		for (int i = 0; i <= 10; i++) {
			final int dir = i == 0 ? Wall.DIR_LEFT : Wall.DIR_RIGHT;
			final boolean end = i == 10;

			add(new Wall(dir, end), i, 5);
			add(new Wall(dir, end), i, 3);
			add(new Wall(dir, end && i != 10), i, 1);
		}

		for (int i = 2; i <= 7; i++) add(new Wall(Wall.DIR_UP), 12, i);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 12, 1);

		add(new FileStack(), 1, 0);
		add(new Enemy(new AttackFileStackAction()), 14, 7);

		add(new ExitPort(ExitPort.DIR_LEFT), 14.5f, 0);
	}
}
