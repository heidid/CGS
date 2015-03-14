package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Port extends Obstacle {
	private TreeMap<Integer, Animation> appearSprite;
	private TreeMap<Integer, Animation> disappearSprite;

	public Port() {
		this(DIR_RIGHT);
	}

	public Port(int direction) {
		setSize(new TreeMap<Integer, Vector2>() {{
			put(DIR_UP, new Vector2(4/3f, 1));
			put(DIR_DOWN, new Vector2(4/3f, 1));
			put(DIR_LEFT, new Vector2(2, 4/3f));
			put(DIR_RIGHT, new Vector2(2, 4/3f));
		}});
		setHitbox(new TreeMap<Integer, Hitbox>() {{
			put(DIR_UP, new Hitbox(new Rectangle(0, 1/12f, 1, 11/12f)));
			put(DIR_DOWN, new Hitbox(new Rectangle(0, 0, 1, 11/12f)));
			put(DIR_LEFT,  new Hitbox(new Rectangle(11/24f, 0, 13/24f, 1)));
			put(DIR_RIGHT,  new Hitbox(new Rectangle(0, 0, 13/24f, 1)));
		}});
		setDirection(direction);
		updateOrigin();
	}

	protected void updateOrigin() {
		switch (getDirection()) {
		case DIR_UP:
			setOrigin(1);
			break;
		case DIR_DOWN:
			setOriginY(3/4f);
			break;
		case DIR_LEFT:
		case DIR_RIGHT:
			setOriginY(7/6f);
			break;
		}
	}

	@Override
	protected void directionChanged() {
		super.directionChanged();
		updateOrigin();
	}

	public TreeMap<Integer, Animation> getAppearSprite() {
		return appearSprite;
	}

	public void setAppearSprite(TreeMap<Integer, Animation> appearSprite) {
		this.appearSprite = appearSprite;
	}

	public TreeMap<Integer, Animation> getDisappearSprite() {
		return disappearSprite;
	}

	public void setDisappearSprite(
			TreeMap<Integer, Animation> disappearSprite) {
		this.disappearSprite = disappearSprite;
	}

	public class AppearAction extends AnimatedAction {
		public AppearAction() {
			super(getAppearSprite());
		}
	}

	public class DisappearAction extends AnimatedAction {
		public DisappearAction() {
			super(getDisappearSprite());
		}
	}
}
