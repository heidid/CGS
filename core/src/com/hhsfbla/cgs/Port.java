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
		setSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, Images.get("port-exit-up.png")));
			put(DIR_DOWN, new Animation(0, Images.get("port-exit-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("port-exit-left.png")));
			put(DIR_RIGHT, new Animation(0, Images.get("port-exit-right.png")));
		}});
		setAppearSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("port-exit-up-%d.png", 0, 17, 0.05f));
			put(DIR_DOWN, Images.getAnimation("port-exit-down-%d.png", 0, 8, 0.05f));
			put(DIR_LEFT, Images.getAnimation("port-exit-left-%d.png", 0, 14, 0.05f));
			put(DIR_RIGHT, Images.getAnimation("port-exit-right-%d.png", 0, 14, 0.05f));
		}});
		setDisappearSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("port-exit-up-%d.png", 17, 0, 0.05f));
			put(DIR_DOWN, Images.getAnimation("port-exit-down-%d.png", 8, 0, 0.05f));
			put(DIR_LEFT, Images.getAnimation("port-exit-left-%d.png", 14, 0, 0.05f));
			put(DIR_RIGHT, Images.getAnimation("port-exit-right-%d.png", 14, 0, 0.05f));
		}});
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
			setOriginY(11/12f);
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
