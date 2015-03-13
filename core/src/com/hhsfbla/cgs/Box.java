package com.hhsfbla.cgs;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class Box extends AnimatedActor {

	private NinePatch patch = new NinePatch(Images.get("box.png"), 4, 4, 4, 4);
	private float borderWidth = 0.05f;
	private float width;
	private float height;

	public Box(float w, float h) {
		width = w;
		height = h;
		patch.setLeftWidth(borderWidth);
		patch.setRightWidth(borderWidth);
		patch.setTopHeight(borderWidth);
		patch.setBottomHeight(borderWidth);
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		patch.draw(batch, getX(), getY(), width, height);
	}

}
