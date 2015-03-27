package com.hhsfbla.cgs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class LevelCompleteBox extends DialogBox {
	private static LabelStyle style = new LabelStyle() {{
		font = Fonts.getFont("fonts/Laconic_Regular.otf", 48);
		fontColor = new Color(70, 233, 234, 1);
		background = new NinePatchDrawable(new NinePatch(
				Images.get("box.png"), 145, 36, 40, 85));
	}};
	private static Drawable overlay = new NinePatchDrawable(new NinePatch(
			Images.get("level-complete.png"), 0, 0, 0, 0));
	private TextureRegion unit = Images.get("level-complete-bar.png");

	private Level level;
	private float health;

	public LevelCompleteBox(Level level, int filesLeft, int filesTotal) {
		super(0, 0, filesLeft+"/"+filesTotal);
		this.level = level;
		setStyle(style);
		setSize(Math.max(getPrefWidth(), overlay.getMinWidth()),
				Math.max(getPrefHeight(), overlay.getMinHeight()));
		setOverlay(overlay);
		health = (float)filesLeft/filesTotal;
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
		batch.draw(unit, getX()+147, getY()+96, Math.round(health*193), unit.getRegionHeight());
	}

}
