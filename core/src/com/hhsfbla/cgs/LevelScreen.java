package com.hhsfbla.cgs;

import java.util.Comparator;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * The main screen for displaying levels
 */
public class LevelScreen extends StageScreen {
	private Level level;
	private Group background;
	private Group foreground;
	private Group ui;

	public LevelScreen(Stage stage, TextureAtlas atlas, Level level) {
		super(stage, atlas);
		this.level = level;
		level.setScreen(this);
		background = new Group();
		foreground = level;
		ui = new Group();
	}

	@Override
	public void show() {
		super.show();
		background.addActor(new Image(Images.get("background.png")));

		stage.addActor(background);
		stage.addActor(foreground);
		stage.addActor(ui);

		stage.setKeyboardFocus(level.getPlayer());
	}

	@Override
	public void render(float delta) {
		// sort all actors based on their z-values
		foreground.getChildren().sort(new Comparator<Actor>() {
			@Override
			public int compare(Actor a, Actor b) {
				return Float.compare(((AnimatedActor) b).getZValue(),
						((AnimatedActor) a).getZValue());
			}
		});

		super.render(delta);
	}
}
