package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public class AnimatedAction extends TemporalAction {
	private TreeMap<Integer, Animation> animation;
	private TreeMap<Integer, Animation> previousSprite;

	public AnimatedAction(TreeMap<Integer, Animation> animation) {
		this.animation = animation;
		if (animation != null && !animation.isEmpty()) {
			setDuration(animation.firstEntry().getValue().getAnimationDuration());
		}
	}

	public TreeMap<Integer, Animation> getPreviousSprite() {
		return previousSprite;
	}

	public void setPreviousSprite(TreeMap<Integer, Animation> previousSprite) {
		this.previousSprite = previousSprite;
	}

	@Override
	protected void begin() {
		AnimatedActor actor = (AnimatedActor) getActor();
		actor.startAnimatedAction(this);
		actor.setSprite(animation);
	}

	@Override
	protected void update(float percent) {}

	@Override
	protected void end() {
		final AnimatedActor actor = (AnimatedActor) getActor();
		actor.endAnimatedAction();
		actor.setSprite(previousSprite);
	}

	@Override
	public void restart() {
		final AnimatedActor actor = (AnimatedActor) getActor();
		actor.endAnimatedAction();
		actor.setSprite(previousSprite);
		super.restart();
	}
}
