package com.hhsfbla.cgs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Array;

public class InventoryPanel extends Label {
	private static final LabelStyle STYLE = new LabelStyle() {{
		font = Fonts.getFont("fonts/Laconic_Regular.otf", 24);
		fontColor = Color.WHITE;
		background = new NinePatchDrawable(new NinePatch(
				Images.get("hudbox.png"), 22, 14, 14, 14));
	}};
	private static final float ITEM_WIDTH = 24;
	private static final float ITEM_SPACING = 12;

	private Player player;

	public InventoryPanel(Player player) {
		super("INVENTORY", STYLE);
		this.player = player;
		setPosition(-4, -4);
		setSize(400, getPrefHeight());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		final Array<Item> inventory = player.getInventory();
		for (int i = 0; i < inventory.size; i++) {
			final Item item = inventory.get(i);
			final TextureRegion sprite = item.getCurrentSpriteFrame();
			final float height = sprite.getRegionHeight() * ITEM_WIDTH
					/ sprite.getRegionWidth();

			batch.draw(sprite, 145 + i * (ITEM_WIDTH + ITEM_SPACING),
					(50 - height) / 2, ITEM_WIDTH, height);
		}
	}
}
