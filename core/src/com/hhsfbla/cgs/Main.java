package com.hhsfbla.cgs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends Game {
	public Stage stage;

	@Override
	public void create() {
		boolean overworld = true;
		stage = new Stage(new FitViewport(1280, 720));
		if (overworld)
			setScreen(new OverworldScreen(stage, new TextureAtlas(), this));
		else {
			//enemy.addAction(new PathFindingAction(level, enemy, 7, 7, 1));
		}
	}
	
}
