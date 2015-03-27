package com.hhsfbla.cgs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class DialogBox extends Label {
	private static LabelStyle style = new LabelStyle() {{
		font = Fonts.getFont("fonts/Laconic_Regular.otf", 16);
		fontColor = Color.WHITE;
		background = new NinePatchDrawable(new NinePatch(
				Images.get("box.png"), 14, 14, 14, 32));
	}};

	private LevelScreen screen;
	private Drawable overlay = new NinePatchDrawable(new NinePatch(
			Images.get("space.png"), 0, 58, 0, 30));

	public DialogBox(float x, float y, String text) {
		super(text, style);
		setPosition(x, y);
	}

	public DialogBox(float x, float y, int align, String text) {
		super(text, style);
		setPosition(x, y, align);
	}

	public DialogBox(float x, float y, float w, float h, String text) {
		super(text, style);
		setBounds(x, y, w, h);
		setWrap(true);
	}

	public DialogBox(float x, float y, int align, float w, float h, String text) {
		super(text, style);
		setPosition(x, y, align);
		setSize(w, h);
		setWrap(true);
	}

	public LevelScreen getScreen() {
		return screen;
	}

	public void setScreen(LevelScreen screen) {
		this.screen = screen;
	}

	public Drawable getOverlay() {
		return overlay;
	}

	public void setOverlay(Drawable overlay) {
		this.overlay = overlay;
	}

	public void dismiss() {
		remove();
		screen.dismissDialog();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		overlay.draw(batch, getX(), getY(), getWidth(), getHeight());
	}
}
