package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Wall extends Obstacle {
	private boolean edge;

	public Wall() {
		this(DIR_LEFT);
	}

	public Wall(int direction) {
		this(direction, false);
	}

	@SuppressWarnings("serial")
	public Wall(int direction, final boolean edge) {
		super(new TreeMap<Integer, Animation>() {{
			final Animation horizontal = new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-h.png"))));
			final Animation vertical = new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-v-middle.png"))));

			put(DIR_UP, edge ? new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-v-half.png"))))
					: vertical);
			put(DIR_DOWN, edge ? new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-v-bottom.png"))))
					: vertical);
			put(DIR_LEFT, edge ? new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-h-half-left.png"))))
					: horizontal);
			put(DIR_RIGHT, edge ? new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-h-half-right.png"))))
					: horizontal);
			put(DIR_UP_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-corner-tl.png")))));
			put(DIR_UP_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-corner-tr.png")))));
			put(DIR_DOWN_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-corner-bl.png")))));
			put(DIR_DOWN_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-corner-br.png")))));

		}}, direction, new TreeMap<Integer, Vector2>() {{
			final Vector2 horizontal = new Vector2(1, 0.25f);
			final Vector2 vertical = new Vector2(0.25f, 1);
			final Vector2 diagonal = new Vector2(1, 1);

			put(DIR_UP, vertical);
			put(DIR_DOWN, vertical);
			put(DIR_LEFT, horizontal);
			put(DIR_RIGHT, horizontal);
			put(DIR_UP_LEFT, diagonal);
			put(DIR_UP_RIGHT, diagonal);
			put(DIR_DOWN_LEFT, diagonal);
			put(DIR_DOWN_RIGHT, diagonal);

		}}, new TreeMap<Integer, Hitbox>() {{
			final Hitbox dfault = new Hitbox();

			put(DIR_UP, edge ? new Hitbox(new Rectangle(0, 0, 0.5f, 1)) : dfault);
			put(DIR_DOWN, edge ? new Hitbox(new Rectangle(0.5f, 0, 0.5f, 1)) : dfault);
			put(DIR_LEFT, edge ? new Hitbox(new Rectangle(0, 0, 1, 0.5f)) : dfault);
			put(DIR_RIGHT, edge ? new Hitbox(new Rectangle(0, 0.5f, 1, 0.5f)) : dfault);
			// TODO: Add diagonal wall hitboxes
		}});
		this.edge = edge;
		directionChanged();
	}

	@Override
	protected void directionChanged() {
		super.directionChanged();
		final int dir = getDirection();
		if (dir == DIR_UP || !edge && dir == DIR_DOWN) {
			setOriginY(-0.5f);
		} else {
			setOriginY(0.5f);
		}
	}

	public boolean isEdge() {
		return edge;
	}

	public void setEdge(boolean edge) {
		this.edge = edge;
	}
}
