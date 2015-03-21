package com.hhsfbla.cgs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class DialogBox extends Label {
	private static LabelStyle style = new LabelStyle() {{
		font = Fonts.getFont("fonts/Laconic_Regular.otf", 16);
		fontColor = Color.WHITE;
		background = new NinePatchDrawable(new NinePatch(
				Images.get("box-enter.png"), 14, 59, 14, 32));
	}};

	private LevelScreen screen;

	public DialogBox(float x, float y, String text) {
		super(text, style);
		setPosition(x, y);
		addListener(new LevelInputListener());
	}

	public DialogBox(float x, float y, float w, float h, String text) {
		super(text, style);
		setBounds(x, y, w, h);
		setWrap(true);
		addListener(new LevelInputListener());
	}

	public LevelScreen getScreen() {
		return screen;
	}

	public void setScreen(LevelScreen screen) {
		this.screen = screen;
	}

	@Override
	protected void setStage(Stage stage) {
		super.setStage(stage);
		if (stage != null) stage.setKeyboardFocus(this);
	}

	public void dismiss() {
		remove();
		screen.dismissDialog();
	}

	// TODO: Fix Player move getting stuck after dismissing DialogBox
	private class LevelInputListener extends InputListener {
		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			if (keycode == Input.Keys.ENTER) dismiss();
			return true;
		}
	}
}
