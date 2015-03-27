package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class File extends Item {
	public File() {
		setSprite(Images.get("file.png"));
		setSize(3/5f, 4/5f);
		setOrigin(Align.bottom);
	}
}
