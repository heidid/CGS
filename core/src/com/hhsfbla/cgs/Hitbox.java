package com.hhsfbla.cgs;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Hitbox extends Array<Rectangle> {

	private float x;
	private float y;
	private float width;
	private float height;

	public Hitbox() {
		this(new Rectangle[] {new Rectangle(-1, -1, 2, 2)});
	}

	public Hitbox(Hitbox hitbox) {
		x = hitbox.x;
		y = hitbox.y;
		width = hitbox.width;
		height = hitbox.height;
		for (Rectangle r : hitbox) super.add(new Rectangle(r));
	}

	public Hitbox(Array<? extends Rectangle> hitboxes) {
		this(hitboxes.items);
	}

	public Hitbox(Rectangle[] hitboxes) {
		super(false, hitboxes.length);
		this.width = 1;
		this.height = 1;
		addAll(hitboxes);
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
		for (Rectangle r1 : this) for (Rectangle r2 : hitbox) {
			if (r1.overlaps(r2)) return true;
		}
		return false;
	}

	private Rectangle convert(Rectangle r) {
		translate(r, x, y);
		scale(r, width / 2, height / 2);
		return r;
	}

	public Hitbox translate(float dx, float dy) {
		for (Rectangle r : this) translate(r, dx, dy);
		return this;
	}

	private void translate(Rectangle r, float dx, float dy) {
		r.setPosition(r.getX() + dx, r.getY() + dy);
	}

	private Hitbox scale(float scaleX, float scaleY) {
		for (Rectangle r : this) scale(r, scaleX, scaleY);
		return this;
	}

	private void scale(Rectangle r, float scaleX, float scaleY) {
		final float centerX = r.getX() + r.getWidth() / 2;
		final float centerY = r.getY() + r.getHeight() / 2;
		r.setSize(r.getWidth() * scaleX, r.getHeight() * scaleY);
		r.setCenter(centerX, centerY);
	}

	@Override
	public void add(Rectangle hitbox) {
		super.add(convert(hitbox));
	}

	@Override
	public void addAll(Array<? extends Rectangle> hitboxes) {
		for (Rectangle r : hitboxes) add(r);
	}

	@Override
	public void addAll(Rectangle[] hitboxes) {
		for (Rectangle r : hitboxes) add(r);
	}
}
