package com.hhsfbla.cgs;

public class Item extends AnimatedActor {

	public void addTo(Player player) {
		player.addItem(this);
		getLevel().remove(this);
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (actor instanceof Player) addTo((Player) actor);
	}

}
