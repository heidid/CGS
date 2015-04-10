package com.hhsfbla.cgs;

public class Level6 extends Level {
	public Level6() {
		id = 5;

		setSpawnPosition(7.5f, 0, ExitPort.DIR_UP);

		add(new WebBotBoss(), 7.5f, 5);

		add(new Wall(Wall.DIR_DOWN_LEFT), 6.5f, 4);
		add(new Wall(Wall.DIR_DOWN), 6.5f, 5);
		add(new Wall(Wall.DIR_UP_LEFT), 6.5f, 6);
		add(new Wall(Wall.DIR_RIGHT), 7.5f, 6);
		add(new Wall(Wall.DIR_UP_RIGHT), 8.5f, 6);
		add(new Wall(Wall.DIR_DOWN), 8.5f, 5);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 8.5f, 4);
		add(new PasswordDoor(3), 7.5f, 4);

		add(new FileStack(), 2, 0);
		add(new FileStack(), 4, 0);
		add(new FileStack(), 6, 0);
		add(new FileStack(), 9, 0);
		add(new FileStack(), 11, 0);
		add(new FileStack(), 13, 0);

		add(new EnemySpawn(), 2, 7.5f);
		add(new EnemySpawn(), 5, 7.5f);
		add(new EnemySpawn(), 10, 7.5f);
		add(new EnemySpawn(), 13, 7.5f);

		add(new TemporaryEnemySpawn(Port.DIR_RIGHT), 0.5f, 4);
		add(new TemporaryEnemySpawn(Port.DIR_RIGHT), 0.5f, 6);
		add(new TemporaryEnemySpawn(Port.DIR_LEFT), 14.5f, 4);
		add(new TemporaryEnemySpawn(Port.DIR_LEFT), 14.5f, 6);

		add(new Factory(Factory.DIR_RIGHT), 4, 3);
		add(new ConveyorBelt(ConveyorBelt.DIR_RIGHT, ConveyorBelt.DIR_UP), 5, 3);
		add(new ConveyorBelt(ConveyorBelt.DIR_UP), 5, 4);
		add(new ConveyorBelt(ConveyorBelt.DIR_UP), 5, 5);

		add(new Factory(Factory.DIR_LEFT), 11, 3);
		add(new ConveyorBelt(ConveyorBelt.DIR_LEFT, ConveyorBelt.DIR_UP), 10, 3);
		add(new ConveyorBelt(ConveyorBelt.DIR_UP), 10, 4);
		add(new ConveyorBelt(ConveyorBelt.DIR_UP), 10, 5);

		final FloorSwitch switch1 = new FloorSwitch();
		final FloorSwitch switch2 = new FloorSwitch();
		final FloorSwitch switch3 = new FloorSwitch();
		final FloorSwitch switch4 = new FloorSwitch();

		add(switch1, 3, 2);
		add(switch2, 12, 2);
		add(switch3, 3, 6);
		add(switch4, 12, 6);

		final Wormhole portal = new Wormhole();
		add(portal, 0, 1);

		add(new SwitchGroupDoor(switch1, switch2, switch3, switch4), 0, 3);
		add(new Wall(Wall.DIR_UP_RIGHT), 1, 3);
		add(new Wall(Wall.DIR_DOWN), 1, 2);
		add(new Wall(Wall.DIR_DOWN), 1, 1);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 1, 0);
		add(new Wall(Wall.DIR_RIGHT), 0, 0);

		add(new PasswordFile(3), 15, 2);
		add(new Wormhole(portal), 15, 1);

		add(new Wall(Wall.DIR_LEFT), 15, 3);
		add(new Wall(Wall.DIR_UP_LEFT), 14, 3);
		add(new Wall(Wall.DIR_DOWN), 14, 2);
		add(new Wall(Wall.DIR_DOWN), 14, 1);
		add(new Wall(Wall.DIR_DOWN_LEFT), 14, 0);
		add(new Wall(Wall.DIR_LEFT), 15, 0);

		setExitPosition(7.5f, 7, ExitPort.DIR_DOWN);
	}
}
