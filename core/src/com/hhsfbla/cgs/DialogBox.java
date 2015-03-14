package com.hhsfbla.cgs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class DialogBox extends Label {
	private static Drawable background = new NinePatchDrawable(new NinePatch(
			Images.get("box.png"), 14, 14, 14, 14));

	private Level level;

	public DialogBox(Level level, float x, float y, String text) {
		super(text, new LabelStyle() {{
			font = Fonts.getFont("fonts/Laconic_Regular.otf");
			fontColor = Color.WHITE;
			background = DialogBox.background;
		}});
		this.level = level;
		level.setPaused(true);
		setPosition(x, y);
		addListener(new LevelInputListener());
	}

	public DialogBox(Level level, float x, float y, float w, float h, String text) {
		super(text, new LabelStyle() {{
			font = Fonts.getFont("fonts/Laconic_Regular.otf");
			fontColor = Color.WHITE;
			background = DialogBox.background;
		}});
		this.level = level;
		level.setPaused(true);
		setBounds(x, y, w, h);
		setWrap(true);
		addListener(new LevelInputListener());
	}

	@Override
	protected void setStage(Stage stage) {
		super.setStage(stage);
		if (stage != null) stage.setKeyboardFocus(this);
	}

	public void dismiss() {
		getStage().setKeyboardFocus(level.getPlayer());
		remove();
		level.setPaused(false);
	}

	private class LevelInputListener extends InputListener {
		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			if (keycode == Input.Keys.ENTER) dismiss();
			return true;
		}
	}
}
