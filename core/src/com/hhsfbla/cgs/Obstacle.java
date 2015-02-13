package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

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

	public Obstacle(TreeMap<Integer, Animation> orientedSprite, int direction,
			float width, float height, Hitbox hitbox) {
		super(orientedSprite, direction, width, height, hitbox);
	}

	public Obstacle(TreeMap<Integer, Animation> orientedSprite, int direction,
			float width, float height, TreeMap<Integer, Hitbox> orientedHitbox) {
		super(orientedSprite, direction, width, height, orientedHitbox);
	}

	public Obstacle(TreeMap<Integer, Animation> orientedSprite, int direction,
			TreeMap<Integer, Vector2> orientedSize) {
		super(orientedSprite, direction, orientedSize);
	}

	public Obstacle(TreeMap<Integer, Animation> orientedSprite, int direction,
			TreeMap<Integer, Vector2> orientedSize,
			TreeMap<Integer, Hitbox> orientedHitbox) {
		super(orientedSprite, direction, orientedSize, orientedHitbox);
	}

}
