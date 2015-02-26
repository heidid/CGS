package com.hhsfbla.cgs;

public class Firewall extends UnblockableObstacle implements SwitchListener {
	public Firewall(Switch swtch) {
		setBlockedSprite(Images.get("firewall.png"));
		setUnblockedSprite(Images.get("firewall-disabled.png"));
		setBlockingAnimation(Images.getAnimation("firewall-appear-%d.png", 1, 4, 0.03f));
		setUnblockingAnimation(Images.getAnimation("firewall-appear-%d.png", 4, 1, 0.03f));
		swtch.addSwitchListener(this);
	}

	@Override
	public void onSwitchStateChanged(boolean on) {
		addActionOnce(on ? new BlockAction() : new UnblockAction());
	}
}
