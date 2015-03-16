package com.hhsfbla.cgs;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class OverworldActor extends AnimatedActor {
	OverworldConnection l, r, u, d; //left right up and down connections
	private OverworldLine[] lines = new OverworldLine[8];
	private int numLines = 0;

	public OverworldActor(float x, float y, String img) {
		setX(x);
		setY(y);
		setSprite(Images.get(img));
	}

	public OverworldActor(float x, float y, String img, float w, float h) {
		this(x, y, img);
		setSize(w, h);
	}

	public void setConnections(OverworldConnection l, OverworldConnection r,
			OverworldConnection u, OverworldConnection d) {
		this.r = r;
		this.l = l;
		this.d = d;
		this.u = u;
	}

	public OverworldConnection conTo(OverworldActor other) {
		MoveToAction ma = new MoveToAction();
		ma.setDuration((float) 1.0);
		ma.setPosition(other.getX(), other.getY());
		int type = ((OverworldCircle)this).getType() < ((OverworldCircle)other).getType() ?
				((OverworldCircle)this).getType() : ((OverworldCircle)other).getType();
		lines[numLines] = new OverworldLine(getX(), getY(), other.getX(), other.getY(), type);
		numLines++;
		return new OverworldConnection(other, new SequenceAction(ma));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		for (OverworldLine line : lines) if (line != null) line.draw(batch, parentAlpha);
		super.draw(batch, parentAlpha);
	}

	static class Connector {
		static void connectH(OverworldActor a, OverworldActor b) { //horizontal
			a.r = a.conTo(b);
			b.l = b.conTo(a);
		}
		static void connectV(OverworldActor a, OverworldActor b) { //vertical
			a.u = a.conTo(b);
			b.d = b.conTo(a);
		}
	}

}
