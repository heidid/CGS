package com.hhsfbla.cgs;

import com.badlogic.gdx.math.Rectangle;

public class FirewallSwitch extends Switch {
	private final Hitbox onHitbox = new Hitbox(new Rectangle(1/2f, 0, 1/2f, 1));
	private final Hitbox offHitbox = new Hitbox(new Rectangle(0, 0, 1/2f, 1));

	public FirewallSwitch() {
		this(false);
	}

	public FirewallSwitch(boolean on) {
		super(on);
		setSize(2, 1);
		setBlocked(true);
		setOffSprite(Images.get("switch-firewall-off.png"));
		setOnSprite(Images.get("switch-firewall-on.png"));
		updateOrientedHitbox();
	}

	@Override
	public void setOn(boolean on) {
		super.setOn(on);
		updateOrientedHitbox();
	}

	protected void updateOrientedHitbox() {
		setHitbox(isOn() ? onHitbox : offHitbox);
	};

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (actor instanceof Player) {
			final Player player = (Player) actor;
			if (!isOn() && player.getDirection() == Player.DIR_RIGHT
					|| isOn() && player.getDirection() == Player.DIR_LEFT) {
				setOn(!isOn());
			}
		}
	}
}
