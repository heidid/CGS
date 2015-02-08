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

	public Wall(int direction) {
		super(new TreeMap<Integer, Animation>() {{
			final Animation horizontal = new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-h.png"))));
			final Animation vertical = new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("wall-v.png"))));

			put(DIR_UP, horizontal);
			put(DIR_DOWN, horizontal);
			put(DIR_LEFT, vertical);
			put(DIR_RIGHT, vertical);
		}}, direction);
	}
}
