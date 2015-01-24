package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LevelScreen extends StageScreen {
	private Level level;

	public LevelScreen(Stage stage, Level level) {
		super(stage);
		this.level = level;
	}

	@Override
	public void show() {
		super.show();
		for (Actor actor : level.getActors()) {
			stage.addActor(actor);
		}
	}
}
