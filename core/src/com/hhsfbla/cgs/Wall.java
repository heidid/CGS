package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Wall extends Obstacle {
	public Wall() {
		this(DIR_DOWN);
	}

	@SuppressWarnings("serial")
	public Wall(int direction) {
		super(new TreeMap<Integer, Animation>() {{
			final Animation horizontal = new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-h.png"))));
			final Animation vertical = new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-v-middle.png"))));
			put(DIR_UP, horizontal);
			put(DIR_DOWN, horizontal);
			put(DIR_LEFT, vertical);
			put(DIR_RIGHT, vertical);
			put(DIR_UP_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-corner-bl.png")))));
			put(DIR_UP_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-corner-br.png")))));
			put(DIR_DOWN_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-corner-tl.png")))));
			put(DIR_DOWN_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-corner-tr.png")))));
		}}, direction, new TreeMap<Integer, Vector2>() {{
			final Vector2 horizontal = new Vector2(1, 0.25f);
			final Vector2 vertical = new Vector2(0.25f, 1);
			put(DIR_UP, horizontal);
			put(DIR_DOWN, horizontal);
			put(DIR_LEFT, vertical);
			put(DIR_RIGHT, vertical);
		}});
		directionChanged();
	}

	@Override
	protected void directionChanged() {
		super.directionChanged();
		final int dir = getDirection();
		if (dir == DIR_UP || dir == DIR_DOWN) {
			setOriginY(0.5f);
		} else if (dir == DIR_LEFT || dir == DIR_RIGHT) {
			setOriginY(-1);
		}
	}
}
