package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Virus extends Enemy {

	public Virus() {

		setIdleSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-up.png")))));
			put(DIR_DOWN, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-down.png")))));
			put(DIR_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-left.png")))));
			put(DIR_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-right.png")))));
			put(DIR_UP_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-up-left.png")))));
			put(DIR_UP_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-up-right.png")))));
			put(DIR_DOWN_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-down-left.png")))));
			put(DIR_DOWN_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-down-right.png")))));
		}});
		setMoveSprite(getIdleSprite());
	}
}
