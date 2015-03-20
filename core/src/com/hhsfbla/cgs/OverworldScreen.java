package com.hhsfbla.cgs;

import java.util.Comparator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * The overworld screen where the use can select a level.
 */
public class OverworldScreen extends StageScreen {
	private OverworldLevel level;
	private Group background = new Group();
	private Group ui = new Group();

	public OverworldScreen(Game game, Stage stage, int state, int current) {
		super(game, stage);
		this.level = new OverworldLevel(state, current);
		level.setScreen(this);
	}

	@Override
	public void show() {
		super.show();
		background.addActor(new Image(Images.get("background-overworld.png")));

		stage.addActor(background);
		stage.addActor(level);
		stage.addActor(ui);

		stage.setKeyboardFocus(level.getPlayer());
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		final float gridWidth = stage.getWidth() / Level.GRID_COLS;
		final float gridHeight = stage.getHeight() / Level.GRID_ROWS;

		level.setScale(gridWidth, gridHeight);
		level.setPosition(gridWidth / 2, gridHeight / 2);
	}

	@Override
	public void render(float delta) {
		level.getChildren().sort(new Comparator<Actor>() {
			@Override
			public int compare(Actor a, Actor b) {
				return Float.compare(((AnimatedActor) b).getZValue(),
						((AnimatedActor) a).getZValue());
			}
		});

		super.render(delta);
	}
}
