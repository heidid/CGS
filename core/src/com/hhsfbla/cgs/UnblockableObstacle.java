package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public class UnblockableObstacle extends Obstacle {
	private TreeMap<Integer, Animation> blockedSprite;
	private TreeMap<Integer, Animation> unblockedSprite;
	private TreeMap<Integer, Animation> blockingAnimation;
	private TreeMap<Integer, Animation> unblockingAnimation;

	public UnblockableObstacle() {
		this(true);
	}

	public UnblockableObstacle(boolean blocked) {
		setBlocked(blocked);
	}

	public TreeMap<Integer, Animation> getBlockedSprite() {
		return blockedSprite;
	}

	public TreeMap<Integer, Animation> getUnblockedSprite() {
		return unblockedSprite;
	}

	public void setBlockedSprite(TreeMap<Integer, Animation> blockedSprite) {
		this.blockedSprite = blockedSprite;
		updateOrientedSprite();
	}

	public void setBlockedSprite(final Animation blockedSprite) {
		setBlockedSprite(new TreeMap<Integer, Animation>(){{
			put(0, blockedSprite);
		}});
	}

	public void setBlockedSprite(TextureRegion blockedSprite) {
		setBlockedSprite(new Animation(0, blockedSprite));
	}

	public void setUnblockedSprite(TreeMap<Integer, Animation> unblockedSprite) {
		this.unblockedSprite = unblockedSprite;
		updateOrientedSprite();
	}

	public void setUnblockedSprite(final Animation unblockedSprite) {
		setUnblockedSprite(new TreeMap<Integer, Animation>(){{
			put(0, unblockedSprite);
		}});
	}

	public void setUnblockedSprite(TextureRegion unblockedSprite) {
		setUnblockedSprite(new Animation(0, unblockedSprite));
	}

	public TreeMap<Integer, Animation> getBlockingAnimation() {
		return blockingAnimation;
	}

	public TreeMap<Integer, Animation> getUnblockingAnimation() {
		return unblockingAnimation;
	}

	public void setBlockingAnimation(TreeMap<Integer, Animation> blockingAnimation) {
		this.blockingAnimation = blockingAnimation;
	}

	public void setBlockingAnimation(final Animation blockingAnimation) {
		setBlockingAnimation(new TreeMap<Integer, Animation>(){{
			put(0, blockingAnimation);
		}});
	}

	public void setUnblockingAnimation(
			TreeMap<Integer, Animation> unblockingAnimation) {
		this.unblockingAnimation = unblockingAnimation;
	}

	public void setUnblockingAnimation(final Animation unblockingAnimation) {
		setUnblockingAnimation(new TreeMap<Integer, Animation>(){{
			put(0, unblockingAnimation);
		}});
	}

	@Override
	public void setBlocked(boolean blocked) {
		super.setBlocked(blocked);
		updateOrientedSprite();
	}

	protected void updateOrientedSprite() {
		setSprite(isBlocked() ? getBlockedSprite() : getUnblockedSprite());
	}

	public class UnblockAction extends AnimatedAction {
		public UnblockAction() {
			super(getUnblockingAnimation());
		}

		@Override
		protected void end() {
			super.end();
			setBlocked(false);
		}
	}

	public class BlockAction extends AnimatedAction {
		public BlockAction() {
			super(getBlockingAnimation());
		}

		@Override
		protected void begin() {
			setBlocked(true);
			super.begin();
		}
	}
}
