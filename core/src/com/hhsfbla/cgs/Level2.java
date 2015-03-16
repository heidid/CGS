package com.hhsfbla.cgs;

public class Level2 extends Level {
	public Level2() {
		add(new PlayerSpawn(PlayerSpawn.DIR_DOWN), 2, 7.5f);

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

		add(new ExitPort(ExitPort.DIR_LEFT), 14.5f, 0);
	}
}
