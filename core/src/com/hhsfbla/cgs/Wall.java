package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * The most basic obstacle. The type of wall changes based on its orientation.
 */
public class Wall extends Obstacle {
	private boolean edge;

	public Wall() {
		this(DIR_RIGHT);
	}

	public Wall(int direction) {
		this(direction, false);
	}

	@SuppressWarnings("serial")
	public Wall(int direction, final boolean edge) {
		setSprite(new TreeMap<Integer, Animation>() {{
			final Animation horizontal = new Animation(0, Images.get("wall-h.png"));
			final Animation vertical = new Animation(0, Images.get("wall-v-middle.png"));

			put(DIR_UP, edge ? new Animation(0, Images.get("wall-v-half.png"))
					: vertical);
			put(DIR_DOWN, edge ? new Animation(0, Images.get("wall-v-bottom.png"))
					: vertical);
			put(DIR_LEFT, edge ? new Animation(0, Images.get("wall-h-half-left.png"))
					: horizontal);
			put(DIR_RIGHT, edge ? new Animation(0, Images.get("wall-h-half-right.png"))
					: horizontal);
			put(DIR_UP_LEFT, new Animation(0, Images.get("wall-corner-tl.png")));
			put(DIR_UP_RIGHT, new Animation(0, Images.get("wall-corner-tr.png")));
			put(DIR_DOWN_LEFT, new Animation(0, Images.get("wall-corner-bl.png")));
			put(DIR_DOWN_RIGHT, new Animation(0, Images.get("wall-corner-br.png")));
		}});

		setSize(new TreeMap<Integer, Vector2>() {{
			final Vector2 horizontal = new Vector2(1, 1/6f);
			final Vector2 vertical = new Vector2(1/6f, 1);
			final Vector2 diagonal = new Vector2(1, 1);

			put(DIR_UP, vertical);
			put(DIR_DOWN, vertical);
			put(DIR_LEFT, horizontal);
			put(DIR_RIGHT, horizontal);
			put(DIR_UP_LEFT, diagonal);
			put(DIR_UP_RIGHT, diagonal);
			put(DIR_DOWN_LEFT, diagonal);
			put(DIR_DOWN_RIGHT, diagonal);
		}});

		setHitbox(new TreeMap<Integer, Hitbox>() {{
			final Hitbox dfault = new Hitbox();

			put(DIR_UP, edge ? new Hitbox(new Rectangle(0, 0, 1, 7/12f)) : dfault);
			put(DIR_DOWN, edge ? new Hitbox(new Rectangle(0, 5/12f, 1, 7/12f)) : dfault);
			put(DIR_LEFT, edge ? new Hitbox(new Rectangle(5/12f, 0, 7/12f, 1)) : dfault);
			put(DIR_RIGHT, edge ? new Hitbox(new Rectangle(0, 0, 7/12f, 1)) : dfault);
			put(DIR_UP_LEFT, new Hitbox(new Rectangle(5/12f, 0, 1/6f, 7/12f),
					new Rectangle(5/12f, 5/12f, 7/12f, 1/6f)));
			put(DIR_UP_RIGHT, new Hitbox(new Rectangle(5/12f, 0, 1/6f, 7/12f),
					new Rectangle(0, 5/12f, 7/12f, 1/6f)));
			put(DIR_DOWN_LEFT, new Hitbox(new Rectangle(5/12f, 5/12f, 1/6f, 7/12f),
					new Rectangle(5/12f, 5/12f, 7/12f, 1/6f)));
			put(DIR_DOWN_RIGHT, new Hitbox(new Rectangle(5/12f, 5/12f, 1/6f, 7/12f),
					new Rectangle(0, 5/12f, 7/12f, 1/6f)));
		}});

		this.edge = edge;
		setDirection(direction);
		updateOrigin();
	}

	private void updateOrigin() {
		final int dir = getDirection();
		if (dir == DIR_UP || !edge && dir == DIR_DOWN) {
			setOriginY(-1/2f);
		} else {
			setOriginY(5/12f);
		}
	}

	@Override
	protected void directionChanged() {
		super.directionChanged();
		updateOrigin();
	}

	public boolean isEdge() {
		return edge;
	}

	public void setEdge(boolean edge) {
		this.edge = edge;
	}
}
