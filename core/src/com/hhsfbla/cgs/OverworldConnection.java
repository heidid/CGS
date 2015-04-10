package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class OverworldConnection {
	OverworldActor oa;
	SequenceAction sa;

	public OverworldConnection(OverworldActor oa, SequenceAction sa) {
		this.oa = oa;
		this.sa = sa;
	}

}
