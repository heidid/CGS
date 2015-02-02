package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Obstacle extends AnimatedActor {
	public Obstacle() {
		super();
	}

	public Obstacle(Animation sprite) {
		super(sprite);
	}

	public Obstacle(TextureRegion sprite) {
		super(sprite);
	}

	public Obstacle(TreeMap<Integer, Animation> orientedSprite) {
		super(orientedSprite);
	}

	public Obstacle(TreeMap<Integer, Animation> orientedSprite, int direction) {
		super(orientedSprite, direction);
	}

	public Obstacle(TreeMap<Integer, Animation> orientedSprite, int direction,
			float width, float height) {
		super(orientedSprite, direction, width, height);
	}
}
