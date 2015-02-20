package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

public class AnimatedActorGroup extends Group {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SnapshotArray<AnimatedActor> getActors() {
	   return (SnapshotArray) getChildren();
	}
}
