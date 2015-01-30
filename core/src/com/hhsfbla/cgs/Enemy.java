package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends AnimatedActor {

	public Enemy() {
		super(new TextureRegion(new Texture(Gdx.files.internal("minion.png"))));
	}
}
