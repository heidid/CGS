package com.hhsfbla.cgs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class GameOverBox extends DialogBox {
	private static LabelStyle style = new LabelStyle() {{
		font = Fonts.getFont("fonts/Laconic_Regular.otf", 26);
		fontColor = Color.WHITE;
		background = new NinePatchDrawable(new NinePatch(
				Images.get("box.png"), 36, 36, 40, 85));
	}};
	private static Drawable overlay = new NinePatchDrawable(new NinePatch(
			Images.get("space-network.png"), 245, 108, 0, 85));

	private Level level;

	public GameOverBox(Level level) {
		super(0, 0, "Game Over");
		this.level = level;
		setStyle(style);
		setSize(Math.max(getPrefWidth(), overlay.getMinWidth()),
				Math.max(getPrefHeight(), overlay.getMinHeight()));
		setOverlay(overlay);
	}

	@Override
	protected void setStage(Stage stage) {
		super.setStage(stage);
		if (stage == null) return;
		setPosition(getStage().getWidth()/2-getWidth()/2, getStage().getHeight()/2-getHeight()/2);
	}

	@Override
	public void dismiss() {
		super.dismiss();
		level.end();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		overlay.draw(batch, getX(), getY(), getWidth(), getHeight());
	}

}
