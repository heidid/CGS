package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

public class Wormhole extends Obstacle {
	private static TreeMap<Integer, Animation> teleportInAnimation;
	private static TreeMap<Integer, Animation> teleportOutAnimation;

	private Wormhole link;
	private Array<MovableActor> resolvedActors;

	public Wormhole() {
		this(null);
	}

	public Wormhole(Wormhole link) {
		resolvedActors = new Array<>();
		setSprite(Images.get("wormhole.png"));
		setBlocked(false);
		setLink(link);
	}

	public Wormhole getLink() {
		return link;
	}

	public void setLink(Wormhole link) {
		this.link = link;
		if (link != null) link.setOtherLink(this);
	}

	private void setOtherLink(Wormhole link) {
		this.link = link;
	}

	public Array<MovableActor> getResolvedActors() {
		return resolvedActors;
	}

	public void addResolvedActor(MovableActor actor) {
		resolvedActors.add(actor);
	}

	public void removeResolvedActor(MovableActor actor) {
		resolvedActors.removeValue(actor, true);
	}

	@Override
	protected void resolveCollision(AnimatedActor actor) {
		if (actor instanceof MovableActor) {
			final MovableActor a = (MovableActor) actor;

			if (!resolvedActors.contains(a, true)
					&& getHitbox().contains(a.getX(), a.getY())) {
				addResolvedActor(a);
				addAction(new TeleportAction(a));
			}
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		for (MovableActor actor : resolvedActors) {
			if (!getHitbox().contains(actor.getX(), actor.getY())) {
				removeResolvedActor(actor);
			}
		}
	}

	public class TeleportAction extends SequenceAction {
		public TeleportAction(final MovableActor actor) {
			addAction(new TeleportInAction(actor));
			addAction(Actions.addAction(link.new TeleportOutAction(actor), link));
		}
	}

	public class TeleportInAction extends AnimatedAction {
		private MovableActor actor;

		public TeleportInAction(MovableActor actor) {
			super(teleportInAnimation);
			this.actor = actor;
		}

		@Override
		protected void begin() {
			actor.addAction(actor.new AppearAction());
			super.begin();
		}
	}

	public class TeleportOutAction extends AnimatedAction {
		private MovableActor actor;

		public TeleportOutAction(MovableActor actor) {
			super(teleportOutAnimation);
			this.actor = actor;
		}

		@Override
		protected void begin() {
			actor.setPosition(getX(), getY());
			actor.addAction(actor.new AppearAction());
			addResolvedActor(actor);

			super.begin();
		}
	}
}
