package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;

public class ExitPort extends Port {
	public ExitPort() {
		this(DIR_RIGHT);
	}

	public ExitPort(int direction) {
		super(direction);
		setOpen(false);
		setOpenSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, Images.get("port-exit-up.png")));
			put(DIR_DOWN, new Animation(0, Images.get("port-exit-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("port-exit-left.png")));
			put(DIR_RIGHT, new Animation(0, Images.get("port-exit-right.png")));
		}});
		setAppearAnimation(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("port-exit-up-%d.png", 0, 17, 0.05f));
			put(DIR_DOWN, Images.getAnimation("port-exit-down-%d.png", 8, 0, 0.05f));
			put(DIR_LEFT, Images.getAnimation("port-exit-left-%d.png", 14, 0, 0.05f));
			put(DIR_RIGHT, Images.getAnimation("port-exit-right-%d.png", 14, 0, 0.05f));
		}});
		setDisappearAnimation(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("port-exit-up-%d.png", 17, 0, 0.05f));
			put(DIR_DOWN, Images.getAnimation("port-exit-down-%d.png", 0, 8, 0.05f));
			put(DIR_LEFT, Images.getAnimation("port-exit-left-%d.png", 0, 14, 0.05f));
			put(DIR_RIGHT, Images.getAnimation("port-exit-right-%d.png", 0, 14, 0.05f));
		}});
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (isOpen() && actor instanceof Player) {
			actor.addAction(((Player) actor).new ExitAction());
		}
	}
}
