package com.hhsfbla.cgs;

public class OverworldCircle extends OverworldActor {

	public OverworldCircle(float x, float y, int type) {
		super(x, y, "overworld-level-"+type+".png");
		setSize(0.5f, 0.3f);
	}

	public void setType(int type) {
		setSprite(Images.get("overworld-level-"+type+".png"));
	}

	@Override
	public float getZValue() {
		return Float.MAX_VALUE;
	}
}
