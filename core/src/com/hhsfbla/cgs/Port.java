package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Port extends UnblockableObstacle {
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
		setUnblockedSprite(new Animation(0, Images.get("blank.png")));
		setDirection(direction);
		updateOrigin();
	}

	public boolean isOpen() {
		return isBlocked();
	}

	public void setOpen(boolean open) {
		setBlocked(open);
	}

	public TreeMap<Integer, Animation> getOpenSprite() {
		return getBlockedSprite();
	}

	public void setOpenSprite(TreeMap<Integer, Animation> sprite) {
		setBlockedSprite(sprite);
	}

	public TreeMap<Integer, Animation> getAppearAnimation() {
		return getBlockingAnimation();
	}

	public void setAppearAnimation(TreeMap<Integer, Animation> animation) {
		setBlockingAnimation(animation);
	}

	public TreeMap<Integer, Animation> getDisappearAnimation() {
		return getUnblockingAnimation();
	}

	public void setDisappearAnimation(TreeMap<Integer, Animation> animation) {
		setUnblockingAnimation(animation);
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

	public class AppearAction extends BlockAction {}

	public class DisappearAction extends UnblockAction {}
}
