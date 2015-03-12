package com.hhsfbla.cgs;

public class OverworldCircle extends OverworldActor {

	public OverworldCircle(float x, float y, int type) {
		super(x, y, "overworld-level-"+type+".png");
		setSize(0.5f, 0.3f);
	}

}
