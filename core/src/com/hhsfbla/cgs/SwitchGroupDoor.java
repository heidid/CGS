package com.hhsfbla.cgs;

public class SwitchGroupDoor extends UnblockableObstacle implements SwitchListener {
	private int switchCount;
	private int onCount;

	public SwitchGroupDoor() {
		this(true);
	}

	public SwitchGroupDoor(Switch...switches) {
		this(true, switches);
	}

	public SwitchGroupDoor(boolean blocked, Switch...switches) {
		super(blocked);
		// TODO: Add SwitchGroupDoor sprite
		setBlockedSprite(Images.get("door.png"));
		setUnblockedSprite(Images.get("doormat.png"));
		for (Switch swtch : switches) {
			swtch.addSwitchListener(this);
			switchCount++;
		}
	}

	@Override
	public void onSwitchStateChanged(boolean on) {
		onCount += on ? 1 : -1;
		if (onCount == switchCount) {
			if (isBlocked()) addActionOnce(new UnblockAction());
		} else {
			if (!isBlocked()) addActionOnce(new BlockAction());
		}
	}
}
