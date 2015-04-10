package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;

public class Rootkit extends Enemy {
	public Rootkit() {
		super(null);
		setSize(1.5f, 1.5f);
		setMaxHealth(100);
		setIdleSprite(Images.get("rootkit.png"));
		setHurtSprite(new TreeMap<Integer, Animation>() {{
			put(0, new Animation(0.05f, Images.get("rootkit.png"),
					Images.get("rootkit-hurt.png")));
		}});
		setDisappearSprite(new TreeMap<Integer, Animation>() {{
			put(0, Images.getAnimation("rootkit-%d.png", 0, 9, 0.05f));
		}});
		getHealthBar().setYOffset(2);
	}
}
