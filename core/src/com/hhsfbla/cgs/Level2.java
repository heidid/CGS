package com.hhsfbla.cgs;

public class Level2 extends Level {
	public Level2() {
		id = 1;

		setSpawnPosition(14, 7, PlayerSpawn.DIR_DOWN);

		add(new FileStack(), 12, 5);
		add(new FileStack(), 8, 7);
		add(new FileStack(), 4, 5);

		add(new Enemy(), 11, 7);
		add(new Enemy(), 4, 6);

		for (int i = 3; i <= 15; i++) add(new Wall(Wall.DIR_RIGHT), i, 4);

		add(new FileStack(), 3, 0);
		add(new FileStack(), 12, 3);

		add(new Enemy(), 3, 3);
		add(new Enemy(), 10, 0);

		setExitPosition(14, 0, PlayerSpawn.DIR_UP);
	}

	@Override
	public void onSpawn() {
		screen.addDialog(new DialogBox(100, 600,
				"Oh no! It looks like there are even more malware minions here.\n"
				+ "Elimate all the malware minions before they corrupt all your files."));
	}
}
