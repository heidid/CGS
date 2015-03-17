package com.hhsfbla.cgs;

import java.util.Comparator;

import com.badlogic.gdx.Game;
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
	private Group ui;

	public LevelScreen(Game game, Stage stage, Level level) {
		super(game, stage);
		this.level = level;
		level.setScreen(this);
		background = new Group();
		ui = new Group();
		ui.addActor(new InventoryPanel(level.getPlayer()));
	}

	public Level getLevel() {
		return level;
	}

	public Group getUi() {
		return ui;
	}

	@Override
	public void show() {
		super.show();
		background.addActor(new Image(Images.get("background-grid.png")));

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
		// sort all actors based on their z-values
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
