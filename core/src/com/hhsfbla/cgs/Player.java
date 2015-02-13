package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class Player extends MovableActor {
	final static float SHOOT_DELAY = 1;
	boolean canShoot = true;
	@SuppressWarnings("serial")
	public Player() {
		setIdleSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("tracker-left.png")))));
			put(DIR_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("tracker-right.png")))));
		}});
		setMoveSprite(getIdleSprite());
		setOrigin(Align.bottom);
		setSpeed(2);
	}
	
	public class ShootAction extends TemporalAction {
		public ShootAction() {
			this.setDuration(SHOOT_DELAY);
		}
		@Override
		protected void begin() {
			canShoot = false;
			getLevel().addAnimatedActor(new Disc(getDirection()));
			
		}
		@Override
		protected void update(float percent) {
			
		}
		@Override
		public void end() {
			canShoot = true;
		}
	}
	
	public class LevelInputListener extends InputListener {
		private boolean up;
		private boolean down;
		private boolean left;
		private boolean right;
		private boolean space;

		private void handleInput() {
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
			} else if (space) {
				if (canShoot)
					addAction(new ShootAction());
			} else {
				setIdle();
			}
		}

		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			switch (keycode) {
			case Input.Keys.UP:
				up = true;
				break;
			case Input.Keys.DOWN:
				down = true;
				break;
			case Input.Keys.LEFT:
				left = true;
				break;
			case Input.Keys.RIGHT:
				right = true;
				break;
			case Input.Keys.SPACE:
				space = true;
				break;
			}
			handleInput();
			return true;
		}

		@Override
		public boolean keyUp(InputEvent event, int keycode) {
			switch (keycode) {
			case Input.Keys.UP:
				up = false;
				break;
			case Input.Keys.DOWN:
				down = false;
				break;
			case Input.Keys.LEFT:
				left = false;
				break;
			case Input.Keys.RIGHT:
				right = false;
				break;
			case Input.Keys.SPACE:
				space = false;
				break;
			}
			handleInput();
			return true;
		}
	}
}
