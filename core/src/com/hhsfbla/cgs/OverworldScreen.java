package com.hhsfbla.cgs;

import java.util.Comparator;

import com.badlogic.gdx.graphics.OrthographicCamera;
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
	private OrthographicCamera cam;

	public OverworldScreen(Main game, Stage stage, int current) {
		super(game, stage);
		level = new OverworldLevel(game.getState(), current);
        cam = new OrthographicCamera(16, 9);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
		//stage.getViewport().setCamera(cam);
		cam.zoom = 1f/2f;
		level.setScreen(this);
	}

	public Group getUi() {
		return ui;
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
		cam.position.set(level.getPlayer().getX(), level.getPlayer().getY(), 0);
		cam.update();
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
