package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class DialogBox extends Label {
	private static Drawable background = new NinePatchDrawable(new NinePatch(
			Images.get("box.png"), 14, 14, 14, 14));

	public DialogBox(float x, float y, String text) {
		super(text, new LabelStyle() {{
			font = Fonts.getFont("fonts/Laconic_Regular.otf");
			fontColor = Color.WHITE;
			background = DialogBox.background;
		}});
		setPosition(x, y);
	}

	public DialogBox(float x, float y, float w, float h, String text) {
		super(text, new LabelStyle() {{
			font = new BitmapFont();
			fontColor = Color.WHITE;
			background = DialogBox.background;
		}});
		setBounds(x, y, w, h);
		setWrap(true);
	}
}
