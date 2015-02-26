package com.hhsfbla.cgs;

public class Firewall extends UnblockableObstacle implements SwitchListener {
	public Firewall(Switch swtch) {
		setBlockedSprite(Images.get("firewall.png"));
		setUnblockedSprite(Images.get("firewall-disabled.png"));
		setBlockingAnimation(Images.getAnimation("firewall-appear-%d.png", 1, 4, 0.03f));
		setUnblockingAnimation(Images.getAnimation("firewall-appear-%d.png", 4, 1, 0.03f));
		swtch.addSwitchListener(this);
		updateBlocked();
	}

	@Override
	public void onSwitchStateChanged(boolean on) {
		addActionOnce(on ? new UnblockAction() : new BlockAction());
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (actor instanceof Player) {
			if (isBlocked()) addActionOnce(new UnblockAction());
		}
	}
}
