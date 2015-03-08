package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class ConveyorBelt extends Obstacle {
	private static final float BELT_SPEED = 4;

	private int direction1;
	private int direction2;

	public ConveyorBelt() {
		this(DIR_RIGHT);
	}

	public ConveyorBelt(int direction) {
		this(direction, direction);
	}

	public ConveyorBelt(int direction1, int direction2) {
		this.direction1 = direction1;
		this.direction2 = direction2;

		final double ang1 = Math.toRadians(direction1);
		final double ang2 = Math.toRadians(direction2 + 180);
		setDirection(direction1 == direction2 ? direction1
				: ((int) Math.round(Math.toDegrees(Math.atan2(
				Math.sin(ang1) + Math.sin(ang2),
				Math.cos(ang1) + Math.cos(ang2)))) + 360) % 360);

		setSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, Images.get("conveyor-belt-up.png")));
			put(DIR_DOWN, new Animation(0, Images.get("conveyor-belt-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("conveyor-belt-left.png")));s
			put(DIR_RIGHT, Images.getAnimation("batch/conveyorbelt-%d.png", 24, 0, 0.05f, PlayMode.LOOP));
			put(DIR_UP_LEFT, new Animation(0, Images.get("conveyor-belt-br.png")));
			put(DIR_UP_RIGHT, new Animation(0, Images.get("conveyor-belt-bl.png")));
			put(DIR_DOWN_LEFT, new Animation(0, Images.get("conveyor-belt-tr.png")));
			put(DIR_DOWN_RIGHT, new Animation(0, Images.get("conveyor-belt-tl.png")));
		}});

		setSize(new TreeMap<Integer, Vector2>() {{
			final Vector2 horizontal = new Vector2(1, 1/2f);
			final Vector2 vertical = new Vector2(1, 1);
			final Vector2 corner = new Vector2(1, 1);

			put(DIR_UP, vertical);
			put(DIR_DOWN, vertical);
			put(DIR_LEFT, horizontal);
			put(DIR_RIGHT, horizontal);
			put(DIR_UP_LEFT, corner);
			put(DIR_UP_RIGHT, corner);
			put(DIR_DOWN_LEFT, corner);
			put(DIR_DOWN_RIGHT, corner);
		}});

		setHitbox(new TreeMap<Integer, Hitbox>() {{
			final Hitbox dfault = new Hitbox();

			put(DIR_UP, dfault);
			put(DIR_DOWN, dfault);
			put(DIR_LEFT, dfault);
			put(DIR_RIGHT, dfault);
			put(DIR_UP_LEFT, new Hitbox(new Rectangle(1/4f, 0, 1/2f, 3/4f),
					new Rectangle(1/4f, 1/4f, 3/4f, 1/2f)));
			put(DIR_UP_RIGHT, new Hitbox(new Rectangle(1/4f, 0, 1/2f, 3/4f),
					new Rectangle(0, 1/4f, 3/4f, 1/2f)));
			put(DIR_DOWN_LEFT, new Hitbox(new Rectangle(1/4f, 1/4f, 1/2f, 3/4f),
					new Rectangle(1/4f, 1/4f, 3/4f, 1/2f)));
			put(DIR_DOWN_RIGHT, new Hitbox(new Rectangle(1/4f, 1/4f, 1/2f, 3/4f),
					new Rectangle(0, 1/4f, 3/4f, 1/2f)));
		}});

		setBlocked(false);
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (actor instanceof MovableActor
				&& !(actor instanceof Projectile)) {
			final MovableActor a = (MovableActor) actor;
			final float distX = Math.abs(a.getX() - getX());
			final float distY = Math.abs(a.getY() - getY());

			if (direction2 == DIR_UP || direction2 == DIR_DOWN) {
				// vertical belt or vertical 2nd half of corner
				if (distX < getWidth() / 2) {
					a.addActionOnce(new ConveyorBeltAction(direction2));

				// corner with initial half vertical
				} else if (direction1 != direction2 && distY < getHeight() / 2
						&& (direction1 == DIR_LEFT && a.getX() > getX()
						|| direction2 == DIR_RIGHT && a.getX() < getX())) {
					a.addActionOnce(new ConveyorBeltAction(direction1, direction2));
				}
			} else if (direction2 == DIR_LEFT || direction2 == DIR_RIGHT) {
				// horizontal belt or horizontal 2nd half of corner
				if (distY < getHeight() / 2) {
					a.addActionOnce(new ConveyorBeltAction(direction2));

				// corner with initial half horizontal
				} else if (direction1 != direction2 && distX < getWidth() / 2
						&& (direction1 == DIR_UP && a.getY() < getY()
						|| direction2 == DIR_DOWN && a.getY() > getY())) {
					a.addActionOnce(new ConveyorBeltAction(direction1, direction2));
				}
			}
		}
	}

	public class ConveyorBeltAction extends SequenceAction {
		public ConveyorBeltAction(int direction) {
			final double ang = Math.toRadians(direction);

			addAction(Actions.run(new Runnable() {
				@Override
				public void run() {
					((MovableActor) actor).setCanMove(false);
				}
			}));
			addAction(new ConveyorBeltMoveToAction(getX() + (float) Math.cos(ang),
					getY() + (float) Math.sin(ang), direction));
			addAction(Actions.run(new Runnable() {
				@Override
				public void run() {
					final MovableActor a = (MovableActor) actor;
					a.setCanMove(true);
					a.detectCollisions(0, 0);
				}
			}));
		}

		public ConveyorBeltAction(int direction1, int direction2) {
			final double ang1 = Math.toRadians(direction1);
			final double ang2 = Math.toRadians(direction2);

			addAction(Actions.run(new Runnable() {
				@Override
				public void run() {
					((MovableActor) actor).setCanMove(false);
				}
			}));
			addAction(new ConveyorBeltMoveToAction(getX(), getY(), direction1));
			addAction(new ConveyorBeltMoveToAction(
					getX() + (float) Math.cos(ang2),
					getY() + (float) Math.sin(ang2), direction2));
			addAction(Actions.run(new Runnable() {
				@Override
				public void run() {
					final MovableActor a = (MovableActor) actor;
					a.setCanMove(true);
					a.detectCollisions(0, 0);

				}
			}));
		}

		@Override
		public String toString() {
			return super.toString() + "@" + Integer.toHexString(
					ConveyorBelt.this.hashCode());
		}
	}

	private static class ConveyorBeltMoveToAction extends MoveToAction {
		private int direction;

		public ConveyorBeltMoveToAction(float x, float y, int direction) {
			setPosition(x, y);
			this.direction = direction;
		}

		@Override
		protected void begin() {
			final MovableActor a = (MovableActor) actor;

			if (direction == DIR_UP || direction == DIR_DOWN) {
				setX(a.getX());
			} else if (direction == DIR_LEFT || direction == DIR_RIGHT) {
				setY(a.getY());
			}

			setDuration((float) Math.sqrt(Math.pow(getX() - a.getX(), 2)
					+ Math.pow(getY() - a.getY(), 2)) / BELT_SPEED);

			super.begin();
		}
	}
}
