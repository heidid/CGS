package com.hhsfbla.cgs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends Game {
	private Stage stage;

	@Override
	public void create() {
		stage = new Stage(new FitViewport(1280, 720)); //set resolution
		setScreen(new OverworldScreen(stage, new TextureAtlas(), this)); //start at overworld
	}
	
}
