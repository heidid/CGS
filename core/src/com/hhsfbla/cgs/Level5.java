package com.hhsfbla.cgs;

public class Level5 extends Level {
	public Level5() {
		id = 1;

		setSpawnPosition(2, 7, PlayerSpawn.DIR_DOWN);

		add(new Wall(Wall.DIR_UP), 1, 6);
		add(new Wall(Wall.DIR_DOWN_LEFT), 3, 6);
		add(new Wall(Wall.DIR_UP_RIGHT), 4, 6);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 1, 5);
		add(new Wall(Wall.DIR_UP_LEFT), 0, 5);
		add(new Wall(Wall.DIR_UP), 4, 5);
		add(new Wall(Wall.DIR_UP), 0, 4);
		add(new Wall(Wall.DIR_UP), 4, 4);
		add(new Wall(Wall.DIR_UP), 0, 3);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 4, 3);
		add(new Wall(Wall.DIR_UP_LEFT), 3, 3);
		add(new Wall(Wall.DIR_DOWN_LEFT), 0, 2);
		add(new Wall(Wall.DIR_UP_RIGHT), 1, 2);
		add(new Wall(Wall.DIR_DOWN), 3, 2);
		add(new Wall(Wall.DIR_DOWN_LEFT, true), 1, 1);
		add(new Wall(Wall.DIR_DOWN_RIGHT, true), 3, 1);

		final FloorSwitch[] switches = {
				new FloorSwitch(),
				new FloorSwitch(),
				new FloorSwitch(),
				new FloorSwitch(),
				new FloorSwitch(),
				new FloorSwitch(),
				new FloorSwitch()
		};

		add(switches[0], 2, 5);
		add(switches[1], 3, 5);
		add(switches[2], 1, 4);
		add(switches[3], 2, 4);
		add(switches[4], 3, 4);
		add(switches[5], 1, 3);
		add(switches[6], 2, 3);

		add(new SwitchGroupDoor(switches), 2, 1);
		add(new ImportantFile(), 2, 0);

		setExitPosition(14, 1, ExitPort.DIR_LEFT);
	}
}
