package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Fonts {

	private static FreeTypeFontGenerator generator;
	private static FreeTypeFontParameter parameter = new FreeTypeFontParameter();

	public static BitmapFont getFont(String file, int size) {
		generator = new FreeTypeFontGenerator(Gdx.files.internal(file));
		parameter.size = size;
		BitmapFont font = generator.generateFont(parameter);
		generator.dispose();
		return font;
	}

}
