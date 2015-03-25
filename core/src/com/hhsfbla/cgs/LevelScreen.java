package com.hhsfbla.cgs;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * The main screen for displaying levels
 */
public class LevelScreen extends StageScreen {
	private Level level;
	private Group background = new Group();
	private Group ui = new Group();
	private Queue<DialogBox> dialogs = new ArrayDeque<>();
	private OrthographicCamera cam;

	public LevelScreen(Main game, Stage stage, Level level) {
		super(game, stage);
		this.level = level;
		level.setScreen(this);
        cam = new OrthographicCamera(Level.GRID_COLS, Level.GRID_ROWS);
        cam.position.set(stage.getWidth()/2,stage.getHeight()/2,0);
        cam.update();
		//stage.getViewport().setCamera(cam);
		//cam.zoom = 0.5f;
		ui.addActor(new InventoryPanel(level.getPlayer()));
	}

	public Level getLevel() {
		return level;
	}

	public Group getUi() {
		return ui;
	}

	public void addDialog(DialogBox dialog) {
		dialogs.add(dialog);
		if (dialogs.size() == 1) showDialog(dialog);
	}

	public void dismissDialog() {
		dialogs.remove();
		final DialogBox dialog = dialogs.peek();

		if (dialog != null) {
			showDialog(dialog);
		} else {
			level.getPlayer().setDialog(null);
			level.setPaused(false);
		}
	}

	private void showDialog(DialogBox dialog) {
		dialog.setScreen(this);
		ui.addActor(dialog);
		level.getPlayer().setDialog(dialog);
		level.setPaused(true);
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
