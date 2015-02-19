package com.hhsfbla.cgs;

import java.util.Comparator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * The overworld screen where the use can select a level.
 */
public class OverworldScreen extends StageScreen {
	private OverworldLevel level;
	private Group background;
	private Group foreground;
	private Group ui;
	Game g;

	public OverworldScreen(Stage stage, TextureAtlas atlas, Game g) {
		super(stage, atlas);
		this.level = new OverworldLevel();
		level.setScreen(this);
		background = new Group();
		foreground = new Group();
		ui = new Group();
		this.g = g;
	}

	@Override
	public void show() {
		super.show();
		background.addActor(new Image(Images.get("background.png")));

		for (Actor actor : level.getActors()) {
			foreground.addActor(actor);
		}
		foreground.addActor(level.getPlayer());
		stage.addActor(background);
		stage.addActor(foreground);
		stage.addActor(ui);

		stage.setKeyboardFocus(level.getPlayer());
	}

	@Override
	public void render(float delta) {
		foreground.getChildren().sort(new Comparator<Actor>() {
			@Override
			public int compare(Actor a, Actor b) {
				return Float.compare(b.getY(), a.getY());
			}
		});

		super.render(delta);
	}
}
