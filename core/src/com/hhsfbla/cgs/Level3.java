package com.hhsfbla.cgs;

public class Level3 extends Level {
	private boolean completeDialog;

	public Level3() {
		id = 6;

		setSpawnPosition(1, 6, PlayerSpawn.DIR_RIGHT);

		add(new EnemySpawn(), 13, 7.5f);
		final Switch swtch = new FirewallSwitch();
		add(swtch, 13, 1);
		add(new Firewall(false, swtch), 13, 7);

		final Factory factory = new Factory();
		add(factory, 7, 6);
		for (int i = 8; i <= 10; i++) {
			add(new ConveyorBelt(ConveyorBelt.DIR_RIGHT), i, 6);
		}
		add(new Virus(), 5, 6);

		for (int i = 0; i < 11; i++) {
			add(new Wall(), i, 5);
			add(new Wall(), i, 2);
		}

		add(new Wall(Wall.DIR_UP_RIGHT), 11, 5);
		for (int i = 0; i < 2; i++) add(new Wall(Wall.DIR_UP), 11, 3 + i);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 11, 2);

		add(new FileStack(), 13, 3);
//		add(new FileStack(), 9, 1);

		setExitPosition(1, 1, ExitPort.DIR_RIGHT);
	}

	@Override
	public void onSpawn() {
		screen.addDialog(new DialogBox(100, 600,
				"Use the WASD keys to move and press Enter to shoot."));
		screen.addDialog(new DialogBox(500, 100, "These are your files.\nPrevent the malware minions from damaging them at all costs."));
	}

	@Override
	public void onComplete() {
		if (!completeDialog) {
			screen.addDialog(new DialogBox(100, 100, "You've cleared this computer of malware!\nExit through this port."));
			completeDialog = false;
		}
	}
}
