package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Virus extends Enemy {
	private Factory factory;

	public Virus() {
		this(DIR_RIGHT);
	}
	
	class BlockAction extends Action {
		@Override
		public boolean act(float delta) {
			for (Obstacle o : getLevel().getObstacles()) {
				if(o instanceof ConveyorBelt) {
					if (getHitbox().overlaps(o.getHitbox())) {
						return false;
					}
				}
			}
			return true;
		}
	}

	@SuppressWarnings("serial")
	public Virus(int direction) {
		// TODO: Add Virus action
		super(direction, null);
		addAction(Actions.sequence(new BlockAction(), new InfectFactoryAction(), new AttackFileStackAction()));
		setIdleSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, Images.get("virus-up.png")));
			put(DIR_DOWN, new Animation(0, Images.get("virus-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("virus-left.png")));
			put(DIR_RIGHT, new Animation(0, Images.get("virus-right.png")));
			put(DIR_UP_LEFT, new Animation(0, Images.get("virus-up-left.png")));
			put(DIR_UP_RIGHT, new Animation(0, Images.get("virus-up-right.png")));
			put(DIR_DOWN_LEFT, new Animation(0, Images.get("virus-down-left.png")));
			put(DIR_DOWN_RIGHT, new Animation(0, Images.get("virus-down-right.png")));
		}});
		setMoveSprite(getIdleSprite());
		setAppearSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("virus-up-%d.png", 0, 9, 0.03f));
			put(DIR_DOWN, Images.getAnimation("virus-down-%d.png", 0, 9, 0.03f));
			put(DIR_LEFT, Images.getAnimation("virus-left-%d.png", 0, 9, 0.03f));
			put(DIR_RIGHT, Images.getAnimation("virus-right-%d.png", 0, 9, 0.03f));
			put(DIR_UP_LEFT, Images.getAnimation("virus-up-left-%d.png", 0, 9, 0.03f));
			put(DIR_UP_RIGHT, Images.getAnimation("virus-up-right-%d.png", 0, 9, 0.03f));
			put(DIR_DOWN_LEFT, Images.getAnimation("virus-down-left-%d.png", 0, 9, 0.03f));
			put(DIR_DOWN_RIGHT, Images.getAnimation("virus-down-right-%d.png", 0, 9, 0.03f));
		}});
		setDisappearSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, Images.getAnimation("virus-up-%d.png", 9, 0, 0.03f));
			put(DIR_DOWN, Images.getAnimation("virus-down-%d.png", 9, 0, 0.03f));
			put(DIR_LEFT, Images.getAnimation("virus-left-%d.png", 9, 0, 0.03f));
			put(DIR_RIGHT, Images.getAnimation("virus-right-%d.png", 9, 0, 0.03f));
			put(DIR_UP_LEFT, Images.getAnimation("virus-up-left-%d.png", 9, 0, 0.03f));
			put(DIR_UP_RIGHT, Images.getAnimation("virus-up-right-%d.png", 9, 0, 0.03f));
			put(DIR_DOWN_LEFT, Images.getAnimation("virus-down-left-%d.png", 9, 0, 0.03f));
			put(DIR_DOWN_RIGHT, Images.getAnimation("virus-down-right-%d.png", 9, 0, 0.03f));
		}});
		setHurtSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0.05f,
					Images.get("virus-up copy.png"),
					Images.get("virus-up.png")));
			put(DIR_DOWN, new Animation(0.05f,
					Images.get("virus-down copy.png"),
					Images.get("virus-down.png")));
			put(DIR_LEFT, new Animation(0.05f,
					Images.get("virus-left copy.png"),
					Images.get("virus-left.png")));
			put(DIR_RIGHT, new Animation(0.05f,
					Images.get("virus-right copy.png"),
					Images.get("virus-right.png")));
			put(DIR_UP_LEFT, new Animation(0.05f,
					Images.get("virus-up-left copy.png"),
					Images.get("virus-up-left.png")));
			put(DIR_UP_RIGHT, new Animation(0.05f,
					Images.get("virus-up-right copy.png"),
					Images.get("virus-up-right.png")));
			put(DIR_DOWN_LEFT, new Animation(0.05f,
					Images.get("virus-down-left copy.png"),
					Images.get("virus-down-left.png")));
			put(DIR_DOWN_RIGHT, new Animation(0.05f,
					Images.get("virus-down-right copy.png"),
					Images.get("virus-down-right.png")));
		}});
	}

	public void infect(Factory factory) {
		this.factory = factory;
		factory.addAction(factory.new InfectAction(this));
	}

	@Override
	public void setHealth(int health) {
		super.setHealth(health);
		if (health <= 0 && factory != null) {
			factory.clearActions();
			factory.setInfected(false);
			factory.setInfector(null);
		}
	}
}
