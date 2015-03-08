package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class FactoryFile extends MovableActor {
	public FactoryFile() {
		setIdleSprite(Images.get("file.png"));
		setSize(2/3f, 5/6f);
		setOrigin(Align.bottom);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (getActions().size == 0) getLevel().remove(this);
	}
}
