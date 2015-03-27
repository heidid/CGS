package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class Level1 extends Level {
	private boolean completeDialog;

	public Level1() {
		id = 0;

		setSpawnPosition(1, 6, PlayerSpawn.DIR_RIGHT);

		add(new Enemy(new AttackFileStackAction()), 7, 1);

		for (int i = 0; i < 11; i++) {
			add(new Wall(), i, 5);
			add(new Wall(), i, 2);
		}

		add(new Wall(Wall.DIR_UP_RIGHT), 11, 5);
		for (int i = 0; i < 2; i++) add(new Wall(Wall.DIR_UP), 11, 3 + i);
		add(new Wall(Wall.DIR_DOWN_RIGHT), 11, 2);

		add(new FileStack(), 13, 3);

		setExitPosition(1, 1, ExitPort.DIR_RIGHT);
	}

	@Override
	public void onSpawn() {
		screen.addDialog(new DialogBox(100, 600,
				"Use the WASD keys to move and press Enter to shoot antivirus disks."));
		screen.addDialog(new DialogBox(500, 100,
				"A malware minion has infiltrated this computer!\nShoot it using your antivirus disks."));
		screen.addDialog(new DialogBox(1200, 400, Align.right,
				"These are your files.\nPrevent the malware minions from damaging them at all costs."));
	}

	@Override
	public void onComplete() {
		if (!completeDialog) {
			screen.addDialog(new DialogBox(100, 100,
					"You've cleared this computer of malware!\nExit through this port."));
			completeDialog = false;
		}
	}
}
