package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

public class Port extends Obstacle {
	public Port() {
		this(DIR_RIGHT);
	}

	public Port(int direction) {
		setSprite(new TreeMap<Integer, Animation>() {{
//			put(DIR_UP, Images.get("port-exit-up.png"));
			put(DIR_DOWN, new Animation(0, Images.get("port-exit-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("port-exit-left.png")));
			put(DIR_RIGHT, new Animation(0, Images.get("port-exit-right.png")));
		}});
		setSize(new TreeMap<Integer, Vector2>(){{
			put(DIR_UP, new Vector2(4/3f, 1));
			put(DIR_DOWN, new Vector2(4/3f, 1));
			put(DIR_LEFT, new Vector2(1, 4/3f));
			put(DIR_RIGHT, new Vector2(1, 4/3f));
		}});
		// TODO: Fix Port hitbox
		setDirection(direction);
		updateOrigin();
	}

	protected void updateOrigin() {
		switch (getDirection()) {
		case DIR_DOWN:
			setOriginY(1/2f);
			break;
		case DIR_LEFT:
		case DIR_RIGHT:
			setOriginY(11/12f);
			break;
		}
	}

	@Override
	protected void directionChanged() {
		super.directionChanged();
		updateOrigin();
	}
}
