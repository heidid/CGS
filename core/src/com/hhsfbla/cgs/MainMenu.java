package com.hhsfbla.cgs;

import java.util.Comparator;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MainMenu extends StageScreen {

	private Group background = new Group();
	private Group ui = new Group();
	private MainMenuLevel level = new MainMenuLevel();

	public MainMenu(Main game, Stage stage) {
		super(game, stage);
		level.setScreen(this);;
		addDialog(new MenuItem(430, 300, "Play", MenuItem.ENTER));
		addDialog(new MenuItem(330, 300, "Instructions", MenuItem.SPACE));
		addDialog(new MenuItem(230, 300, "Quit", MenuItem.ESC));
	}

	public void addDialog(MenuItem item) {
		item.setScreen(this);
		ui.addActor(item);
	}

	@Override
	public void show() {
		super.show();
		ui.addListener(new MenuInputListener());
		stage.setKeyboardFocus(ui);
		background.addActor(new Image(Images.get("background-start.png")));

		stage.addActor(background);
		stage.addActor(level);
		stage.addActor(ui);
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

	private class MenuInputListener extends InputListener {
		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			switch (keycode) {
			case Input.Keys.ENTER:
				game.setScreen(new OverworldScreen(game, stage, 0));
				break;
			case Input.Keys.SPACE:
				break;
			}
			return true;
		}
	}


}
