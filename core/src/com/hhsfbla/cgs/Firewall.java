package com.hhsfbla.cgs;

public class Firewall extends UnblockableObstacle implements SwitchListener {
	public Firewall(Switch swtch) {
		this(true, swtch);
	}

	public Firewall(boolean blocked, Switch swtch) {
		super(blocked);
		setBlockedSprite(Images.get("firewall.png"));
		setUnblockedSprite(Images.get("firewall-disabled.png"));
		setBlockingAnimation(Images.getAnimation("firewall-appear-%d.png", 1, 4, 0.03f));
		setUnblockingAnimation(Images.getAnimation("firewall-appear-%d.png", 4, 1, 0.03f));
		swtch.addSwitchListener(this);
		setSize(1, 1/6f);
		setOriginY(1/2f);
	}

	@Override
	public void onSwitchStateChanged(boolean on) {
		addActionOnce(on ? new BlockAction() : new UnblockAction());
	}
}
