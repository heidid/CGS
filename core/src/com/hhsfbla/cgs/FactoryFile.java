package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class FactoryFile extends MovableActor {
	public FactoryFile() {
		setIdleSprite(Images.get("file.png"));
		setAppearSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("file-poof-%d.png", 8, 0, 0.03f));
			put(DIR_DOWN, Images.getAnimation("file-poof-%d.png", 8, 0, 0.03f));
			put(DIR_LEFT, Images.getAnimation("file-poof-%d.png", 8, 0, 0.03f));
			put(DIR_RIGHT, Images.getAnimation("file-poof-%d.png", 8, 0, 0.03f));
			put(DIR_UP_LEFT, Images.getAnimation("file-poof-%d.png", 8, 0, 0.03f));
			put(DIR_UP_RIGHT, Images.getAnimation("file-poof-%d.png", 8, 0, 0.03f));
			put(DIR_DOWN_LEFT, Images.getAnimation("file-poof-%d.png", 8, 0, 0.03f));
			put(DIR_DOWN_RIGHT, Images.getAnimation("file-poof-%d.png", 8, 0, 0.03f));
		}});
		setDisappearSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("file-poof-%d.png", 0, 8, 0.03f));
			put(DIR_DOWN, Images.getAnimation("file-poof-%d.png", 0, 8, 0.03f));
			put(DIR_LEFT, Images.getAnimation("file-poof-%d.png", 0, 8, 0.03f));
			put(DIR_RIGHT, Images.getAnimation("file-poof-%d.png", 0, 8, 0.03f));
			put(DIR_UP_LEFT, Images.getAnimation("file-poof-%d.png", 0, 8, 0.03f));
			put(DIR_UP_RIGHT, Images.getAnimation("file-poof-%d.png", 0, 8, 0.03f));
			put(DIR_DOWN_LEFT, Images.getAnimation("file-poof-%d.png", 0, 8, 0.03f));
			put(DIR_DOWN_RIGHT, Images.getAnimation("file-poof-%d.png", 0, 8, 0.03f));
		}});
		setSize(3/5f, 4/5f);
		setOrigin(Align.bottom);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (getActions().size == 0) addAction(new PoofAction());
	}

	public class PoofAction extends DisappearAction {
		@Override
		protected void end() {
			super.end();
			getLevel().remove(FactoryFile.this);
		}
	}
}
