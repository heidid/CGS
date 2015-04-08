package com.hhsfbla.cgs;

public class Firewall extends UnblockableObstacle implements SwitchListener {

	public static final String BLUE = "blue";
	public static final String GREEN = "green";
	public static final String PURPLE = "purple";

	public Firewall(Switch swtch) {
		this(true, swtch);
	}

	public Firewall(Switch swtch, String color) {
		this(true, swtch, color);
	}

	public Firewall(boolean blocked, Switch swtch) {
		super(blocked);
		setBlockedSprite(Images.get("firewall.png"));
		setUnblockedSprite(Images.get("firewall-disabled.png"));
		setBlockingAnimation(Images.getAnimation("firewall-%d.png", 9, 0, 0.03f));
		setUnblockingAnimation(Images.getAnimation("firewall-%d.png", 0, 9, 0.03f));
		swtch.addSwitchListener(this);
		setSize(1, 1/6f);
		setOriginY(5/12f);
	}

	public Firewall(boolean blocked, Switch swtch, String color) {
		super(blocked);
		setBlockedSprite(Images.get("firewall-"+color+".png"));
		setUnblockedSprite(Images.get("firewall-disabled.png"));
		setBlockingAnimation(Images.getAnimation("firewall-"+color+"-%d.png", 9, 0, 0.03f));
		setUnblockingAnimation(Images.getAnimation("firewall-"+color+"-%d.png", 0, 9, 0.03f));
		swtch.addSwitchListener(this);
		setSize(1, 1/6f);
		setOriginY(5/12f);
	}

	@Override
	public void onSwitchStateChanged(boolean on) {
		if (on && !isBlocked()) addActionOnce(new BlockAction());
		else if (!on && isBlocked()) addActionOnce(new UnblockAction());
	}
}
