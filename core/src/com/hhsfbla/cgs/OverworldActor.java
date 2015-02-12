package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class OverworldActor extends AnimatedActor {
	OverworldConnection l, r, u, d;
	boolean unloked = false;

	public OverworldActor(float x, float y) {
		setX(x);
		setY(y);
		setSprite(new TextureRegion(new Texture(
				Gdx.files.internal("router.png"))));
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
		return new OverworldConnection(other, new SequenceAction(ma));
	}

}
