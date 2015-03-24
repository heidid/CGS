package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;

public class Player extends MovableActor {
	private static final float SHOOT_DELAY = 0.3f; // number of seconds between firing CDs

	private Array<Item> inventory = new Array<>();
	private boolean canShoot = true;
	private PlayerInputListener inputListener = new PlayerInputListener();
	private DialogBox dialog;

	@SuppressWarnings("serial")
	public Player() {
		addListener(inputListener);

		setIdleSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, Images.get("player-up.png")));
			put(DIR_DOWN, new Animation(0, Images.get("player-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("player-left.png")));
			put(DIR_RIGHT, new Animation(0, Images.get("player-right.png")));
			put(DIR_UP_LEFT, new Animation(0, Images.get("player-up-left.png")));
			put(DIR_UP_RIGHT, new Animation(0, Images.get("player-up-right.png")));
			put(DIR_DOWN_LEFT, new Animation(0, Images.get("player-down-left.png")));
			put(DIR_DOWN_RIGHT, new Animation(0, Images.get("player-down-right.png")));
		}});
		setMoveSprite(getIdleSprite());
		setAppearSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("player-up-%d.png", 9, 0, 0.03f));
			put(DIR_DOWN, Images.getAnimation("player-down-%d.png", 9, 0, 0.03f));
			put(DIR_LEFT, Images.getAnimation("player-left-%d.png", 9, 0, 0.03f));
			put(DIR_RIGHT, Images.getAnimation("player-right-%d.png", 9, 0, 0.03f));
			put(DIR_UP_LEFT, Images.getAnimation("player-up-left-%d.png", 9, 0, 0.03f));
			put(DIR_UP_RIGHT, Images.getAnimation("player-up-right-%d.png", 9, 0, 0.03f));
			put(DIR_DOWN_LEFT, Images.getAnimation("player-down-left-%d.png", 9, 0, 0.03f));
			put(DIR_DOWN_RIGHT, Images.getAnimation("player-down-right-%d.png", 9, 0, 0.03f));
		}});
		setDisappearSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("player-up-%d.png", 0, 9, 0.03f));
			put(DIR_DOWN, Images.getAnimation("player-down-%d.png", 0, 9, 0.03f));
			put(DIR_LEFT, Images.getAnimation("player-left-%d.png", 0, 9, 0.03f));
			put(DIR_RIGHT, Images.getAnimation("player-right-%d.png", 0, 9, 0.03f));
			put(DIR_UP_LEFT, Images.getAnimation("player-up-left-%d.png", 0, 9, 0.03f));
			put(DIR_UP_RIGHT, Images.getAnimation("player-up-right-%d.png", 0, 9, 0.03f));
			put(DIR_DOWN_LEFT, Images.getAnimation("player-down-left-%d.png", 0, 9, 0.03f));
			put(DIR_DOWN_RIGHT, Images.getAnimation("player-down-right-%d.png", 0, 9, 0.03f));
		}});

		setOrigin(Align.bottom);
		setSpeed(2);
	}

	public Array<Item> getInventory() {
		return inventory;
	}

	public void addItem(Item item) {
		inventory.add(item);
	}

	public void removeItem(Item item) {
		inventory.removeValue(item, true);
	}

	public DialogBox getDialog() {
		return dialog;
	}

	public void setDialog(DialogBox dialog) {
		this.dialog = dialog;
	}

	@Override
	protected boolean detectCollisions(float dx, float dy) {
		final float x = getX() + dx;
		final float y = getY() + dy;
		if (x <= 0 || x >= Level.GRID_COLS - 1
				|| y <= 0 || y >= Level.GRID_ROWS - 2) {
			return true;
		}
		return super.detectCollisions(dx, dy);
	}

	@Override
	public void act(float delta) {
		inputListener.handleInput();
		super.act(delta);
	}

	public class ShootAction extends TemporalAction {
		public ShootAction() {
			super(SHOOT_DELAY);
		}

		@Override
		protected void begin() {
			canShoot = false;
			getLevel().add(new Disc(getDirection()), getX() + (float) Math.cos(
					Math.toRadians(getDirection())) * 1/2f, getY());
		}

		@Override
		protected void update(float percent) {}

		@Override
		public void end() {
			canShoot = true;
		}
	}

	public class ExitAction extends DisappearAction {
		@Override
		protected void end() {
			super.end();
			getLevel().win();
		}
	}

	private class PlayerInputListener extends InputListener {
		private boolean up;
		private boolean down;
		private boolean left;
		private boolean right;
		private byte shoot;

		public void handleInput() {
			if (up && left) {
				setMoving(DIR_UP_LEFT);
			} else if (up && right) {
				setMoving(DIR_UP_RIGHT);
			} else if (down && left) {
				setMoving(DIR_DOWN_LEFT);
			} else if (down && right) {
				setMoving(DIR_DOWN_RIGHT);
			} else if (up) {
				setMoving(DIR_UP);
			} else if (down) {
				setMoving(DIR_DOWN);
			} else if (left) {
				setMoving(DIR_LEFT);
			} else if (right) {
				setMoving(DIR_RIGHT);
			} else {
				setIdle();
			}
			if (shoot == 1 && canShoot) {
				shoot = 2;
				addAction(new ShootAction());
			}
		}

		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			switch (keycode) {
			case Input.Keys.W:
				up = true;
				break;
			case Input.Keys.S:
				down = true;
				break;
			case Input.Keys.A:
				left = true;
				break;
			case Input.Keys.D:
				right = true;
				break;
			case Input.Keys.ENTER:
				if (dialog != null) dialog.dismiss();
				else if (shoot == 0) shoot = 1;
				break;
			}
			return true;
		}

		@Override
		public boolean keyUp(InputEvent event, int keycode) {
			switch (keycode) {
			case Input.Keys.W:
				up = false;
				break;
			case Input.Keys.S:
				down = false;
				break;
			case Input.Keys.A:
				left = false;
				break;
			case Input.Keys.D:
				right = false;
				break;
			case Input.Keys.ENTER:
				shoot = 0;
				break;
			}
			return true;
		}
	}
}
