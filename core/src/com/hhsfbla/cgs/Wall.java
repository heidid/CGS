package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Wall extends Obstacle {
	// TODO: Fix wall bounding box

	public Wall() {
		this(DIR_DOWN);
	}

	@SuppressWarnings("serial")
	public Wall(int direction) {
		super(new TreeMap<Integer, Animation>() {{
			final Animation horizontal = new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-h.png"))));
			final Animation vertical = new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-v.png"))));
			final Animation topRight = new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-corner-bl.png"))));
			final Animation topLeft = new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-corner-br.png"))));
			final Animation bottomRight = new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-corner-tl.png"))));
			final Animation bottomLeft = new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-corner-tr.png"))));

			put(DIR_UP, horizontal);
			put(DIR_DOWN, horizontal);
			put(DIR_LEFT, vertical);
			put(DIR_RIGHT, vertical);
			put(DIR_UP_RIGHT, topRight);
			put(DIR_UP_LEFT, topLeft);
			put(DIR_DOWN_RIGHT, bottomRight);
			put(DIR_DOWN_LEFT, bottomLeft);
		}}, direction);
	}
}
