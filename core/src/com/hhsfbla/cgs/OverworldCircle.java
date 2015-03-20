package com.hhsfbla.cgs;

public class OverworldCircle extends OverworldActor {
	private int type;

	public OverworldCircle(float x, float y, int type, Level level) {
		super(x, y, "overworld-level-"+type+".png");
		this.type = type;
		setLevel(level);
		setSize(0.5f, 0.3f);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		setSprite(Images.get("overworld-level-"+type+".png"));
	}

	@Override
	public float getZValue() {
		return Float.MAX_VALUE;
	}
}
