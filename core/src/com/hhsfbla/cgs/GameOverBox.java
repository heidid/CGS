package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class GameOverBox extends DialogBox {

	private Level level;

	private static LabelStyle style = new LabelStyle() {{
		font = Fonts.getFont("fonts/Laconic_Regular.otf", 26);
		fontColor = Color.WHITE;
		background = new NinePatchDrawable(new NinePatch(
				Images.get("box-enter-network.png"), 36, 36, 40, 85));
	}};

	public GameOverBox(Level level) {
		super(0, 0, "gg your files kinda all died");
		this.level = level;
		setStyle(style);
		setSize(getPrefWidth(), getPrefHeight());
	}

	@Override
	protected void setStage(Stage stage) {
		// TODO Auto-generated method stub
		super.setStage(stage);
		if (stage == null) return;
		setPosition(getStage().getWidth()/2-getWidth()/2, getStage().getHeight()/2-getHeight()/2);
	}

	public void dismiss() {
		super.dismiss();
		level.end();
	}

}
