package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Action;

public class Trojan extends Enemy {
	public Trojan() {
		this(DIR_RIGHT);
	}

	@SuppressWarnings("serial")
	public Trojan(int direction) {
		// TODO: Add Trojan action
		super(direction, new AttackFileStackAction());

		setIdleSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, Images.get("trojan-up.png")));
			put(DIR_DOWN, new Animation(0, Images.get("trojan-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("trojan-left.png")));
			put(DIR_RIGHT, new Animation(0, Images.get("trojan-right.png")));
			put(DIR_UP_LEFT, new Animation(0, Images.get("trojan-up-left.png")));
			put(DIR_UP_RIGHT, new Animation(0, Images.get("trojan-up-right.png")));
			put(DIR_DOWN_LEFT, new Animation(0, Images.get("trojan-down-left.png")));
			put(DIR_DOWN_RIGHT, new Animation(0, Images.get("trojan-down-right.png")));
		}});
		setMoveSprite(getIdleSprite());
		setAppearSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("trojan-up-die-%d.png", 0, 9, 0.03f));
			put(DIR_DOWN, Images.getAnimation("trojan-down-die-%d.png", 0, 9, 0.03f));
			put(DIR_LEFT, Images.getAnimation("trojan-left-die-%d.png", 0, 9, 0.03f));
			put(DIR_RIGHT, Images.getAnimation("trojan-right-die-%d.png", 0, 9, 0.03f));
			put(DIR_UP_LEFT, Images.getAnimation("trojan-up-left-die-%d.png", 0, 9, 0.03f));
			put(DIR_UP_RIGHT, Images.getAnimation("trojan-up-right-die-%d.png", 0, 9, 0.03f));
			put(DIR_DOWN_LEFT, Images.getAnimation("trojan-down-left-die-%d.png", 0, 9, 0.03f));
			put(DIR_DOWN_RIGHT, Images.getAnimation("trojan-down-right-die-%d.png", 0, 9, 0.03f));
		}});
		setDisappearSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("trojan-up-die-%d.png", 9, 0, 0.03f));
			put(DIR_DOWN, Images.getAnimation("trojan-down-die-%d.png", 9, 0, 0.03f));
			put(DIR_LEFT, Images.getAnimation("trojan-left-die-%d.png", 9, 0, 0.03f));
			put(DIR_RIGHT, Images.getAnimation("trojan-right-die-%d.png", 9, 0, 0.03f));
			put(DIR_UP_LEFT, Images.getAnimation("trojan-up-left-die-%d.png", 9, 0, 0.03f));
			put(DIR_UP_RIGHT, Images.getAnimation("trojan-up-right-die-%d.png", 9, 0, 0.03f));
			put(DIR_DOWN_LEFT, Images.getAnimation("trojan-down-left-die-%d.png", 9, 0, 0.03f));
			put(DIR_DOWN_RIGHT, Images.getAnimation("trojan-down-right-die-%d.png", 9, 0, 0.03f));
		}});
		setHurtSprite(new TreeMap<Integer, Animation>() {{
            put(DIR_UP, new Animation(0.05f,
                    Images.get("trojan-up copy.png"),
                    Images.get("trojan-up.png")));
            put(DIR_DOWN, new Animation(0.05f,
                    Images.get("trojan-down copy.png"),
                    Images.get("trojan-down.png")));
            put(DIR_LEFT, new Animation(0.05f,
                    Images.get("trojan-left copy.png"),
                    Images.get("trojan-left.png")));
            put(DIR_RIGHT, new Animation(0.05f,
                    Images.get("trojan-right copy.png"),
                    Images.get("trojan-right.png")));
            put(DIR_UP_LEFT, new Animation(0.05f,
                    Images.get("trojan-up-left copy.png"),
                    Images.get("trojan-up-left.png")));
            put(DIR_UP_RIGHT, new Animation(0.05f,
                    Images.get("trojan-up-right copy.png"),
                    Images.get("trojan-up-right.png")));
            put(DIR_DOWN_LEFT, new Animation(0.05f,
                    Images.get("trojan-down-left copy.png"),
                    Images.get("trojan-down-left.png")));
            put(DIR_DOWN_RIGHT, new Animation(0.05f,
                    Images.get("trojan-down-right copy.png"),
                    Images.get("trojan-down-right.png")));
    }});
	}
}
