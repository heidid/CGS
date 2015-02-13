package com.hhsfbla.cgs;

import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

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
		foreground = new Group();
		ui = new Group();
	}

	@Override
	public void show() {
		super.show();
		background.addActor(new Image(new Texture(Gdx.files.internal("background.png"))));

		for (Actor actor : level.getActors()) foreground.addActor(actor);

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
				float za = (a instanceof Obstacle && !((Obstacle) a).isBlocked())
						? Float.MAX_VALUE : a.getY();
				float zb = (b instanceof Obstacle && !((Obstacle) b).isBlocked())
						? Float.MAX_VALUE : b.getY();
				return Float.compare(zb, za);
			}
		});

		super.render(delta);
	}
}
