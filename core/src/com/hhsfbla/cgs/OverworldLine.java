package com.hhsfbla.cgs;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class OverworldLine {

	private TextureRegion unit = Images.get("overworld-line-unit.png");
	float x1, x2, y1, y2, len, dlen, dx, dy;

	public OverworldLine(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		len = (float)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
		dx = (x2-x1)/len;
		dy = (y2-y1)/len;
		dlen = (float)Math.sqrt(dx*dx+dy*dy);
	}

	public void draw(Batch batch, float parentAlpha) {
		for (float i=0; i<len; i+=dlen/120) {
			batch.draw(unit, x1+i*dx, y1+i*dy, 0.1f, 0.1f);
		}
	}

}
