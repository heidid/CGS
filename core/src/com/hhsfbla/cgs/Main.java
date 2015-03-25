package com.hhsfbla.cgs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends Game {
	private Stage stage;
	private int state;

	@Override
	public void create() {
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) state = 5;
		stage = new Stage(new FitViewport(1280, 720)); //set resolution
		setScreen(new OverworldScreen(this, stage, state)); //start at overworld
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
