package com.hhsfbla.cgs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class OverworldDialogBox extends Label {
	private static LabelStyle style = new LabelStyle() {{
		font = Fonts.getFont("fonts/Laconic_Regular.otf", 16);
		fontColor = Color.WHITE;
		background = new NinePatchDrawable(new NinePatch(
				Images.get("box.png"), 14, 14, 14, 14));
	}};

	public OverworldDialogBox(float x, float y, String text) {
		super(text, style);
		setPosition(x, y);
	}

	public OverworldDialogBox(float x, float y, float w, float h, String text) {
		super(text, style);
		setBounds(x, y, w, h);
	}
}
