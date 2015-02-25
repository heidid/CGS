package com.hhsfbla.cgs;

public class FloorSwitch extends Switch {
	public FloorSwitch() {
		setOffSprite(Images.get("ground-switch.png"));
		setOnSprite(Images.get("ground-switch-pressed.png"));
		updateOn();
	}
}
