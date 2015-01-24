package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends AnimatedActor {

	public Player() {
		super(new TextureRegion(new Texture(Gdx.files.internal("router.png"))));
	}

	public Player(float x, float y) {
		this();
		setOrigin(x, y);
	}

}
