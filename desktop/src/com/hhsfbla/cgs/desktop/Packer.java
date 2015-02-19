package com.hhsfbla.cgs.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class Packer {
	public static void main (String[] arg) {
	    Settings settings = new Settings();
	    settings.maxHeight = 4096;
	    settings.maxWidth = 4096;
	    TexturePacker.process(settings, "../core/assets/images", "../core/assets/packed", "atlas");
	}
}
