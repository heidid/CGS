package com.hhsfbla.cgs;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Hitbox {

	private float x;
	private float y;
	private float width;
	private float height;
	private Array<Rectangle> regions;

	public Hitbox() {
		this(new Rectangle[] {new Rectangle(-1, -1, 2, 2)});
	}

	public Hitbox(Hitbox hitbox) {
		x = hitbox.x;
		y = hitbox.y;
		width = hitbox.width;
		height = hitbox.height;
		regions = new Array<Rectangle>();
		for (Rectangle r : hitbox.regions) regions.add(new Rectangle(r));
	}

	public Hitbox(Array<? extends Rectangle> regions) {
		this(regions.items);
	}

	public Hitbox(Rectangle[] regions) {
		this.width = 1;
		this.height = 1;
		this.regions = new Array<Rectangle>();
		for (Rectangle r : regions) this.regions.add(scale(r, 0.5f, 0.5f));
	}

	public void setCenter(float x, float y) {
		translate(x - this.x, y - this.y);
		this.x = x;
		this.y = y;
	}

	public void setSize(float width, float height) {
		scale(width / this.width, height / this.height);
		this.width = width;
		this.height = height;
	}

	public boolean overlaps(Hitbox hitbox) {
		for (Rectangle r1 : regions) for (Rectangle r2 : hitbox.regions) {
			if (r1.overlaps(r2)) return true;
		}
		return false;
	}

	public Hitbox translate(float dx, float dy) {
		for (Rectangle r : regions) translate(r, dx, dy);
		return this;
	}

	private Rectangle translate(Rectangle r, float dx, float dy) {
		r.setPosition(r.getX() + dx, r.getY() + dy);
		return r;
	}

	private Hitbox scale(float scaleX, float scaleY) {
		for (Rectangle r : regions) scale(r, scaleX, scaleY);
		return this;
	}

	private Rectangle scale(Rectangle r, float scaleX, float scaleY) {
		final float centerX = r.getX() + r.getWidth() / 2;
		final float centerY = r.getY() + r.getHeight() / 2;
		r.setSize(r.getWidth() * scaleX, r.getHeight() * scaleY);
		r.setCenter(centerX, centerY);
		return r;
	}
}
