package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

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
		setBlockedSprite(Images.get("door-square.png"));
		setUnblockedSprite(Images.get("doormat-square.png"));
		setOriginY(5/12f);
		setBlockingAnimation(Images.getAnimation("door-square-%d.png", 9, 0, 0.05f));
		setUnblockingAnimation(Images.getAnimation("door-square-%d.png", 0, 9, 0.05f));
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
