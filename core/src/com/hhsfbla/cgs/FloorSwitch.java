package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FloorSwitch extends Obstacle {
	boolean pressed;
	public FloorSwitch(float x, float y) {
		super();
		setX(x);
		setY(y);
		setPressed(false);
	}
	
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
		if (pressed)
			setSprite(new TextureRegion(new Texture(Gdx.files.internal("ground-switch-pressed.png"))));
		else
			setSprite(new TextureRegion(new Texture(Gdx.files.internal("ground-switch.png"))));
	}
}
