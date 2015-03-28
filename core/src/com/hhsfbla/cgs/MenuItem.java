package com.hhsfbla.cgs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class MenuItem extends DialogBox {

	public static final String ENTER = "enter.png";
	public static final String SPACE = "space.png";
	public static final String ESC = "esc.png";

	private MainMenu screen;

	private static LabelStyle style = new LabelStyle() {{
		font = Fonts.getFont("fonts/Laconic_Regular.otf", 42);
		fontColor = new Color(70, 233, 234, 1);
		background = new NinePatchDrawable(new NinePatch(
				Images.get("box.png"), 34, 34, 14, 22));
	}};
	private Drawable overlay;
	private int y;

	public MenuItem(int y, int width, String text, String key) {
		super(0, y, text);
		overlay = new NinePatchDrawable(new NinePatch(
				Images.get(key), 0, 58, 0, 30));
		setStyle(style);
		setAlignment(Align.center);
		setSize(Math.max(width, overlay.getMinWidth()),
				Math.max(getPrefHeight(), overlay.getMinHeight()));
		setOverlay(overlay);
		this.y = y;
	}

	@Override
	protected void setStage(Stage stage) {
		super.setStage(stage);
		if (stage == null) return;
		setPosition(getStage().getWidth()/2-getWidth()/2, y);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		overlay.draw(batch, getX(), getY(), getWidth(), getHeight());
	}

	public void setScreen(MainMenu mainMenu) {
		screen = mainMenu;
	}
}

