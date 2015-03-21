package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class InfectFactoryAction extends SequenceAction {
	Virus virus;
	Factory factory;
	boolean moving = true;

	public void createRemove() {
		virus.clearActions();
		virus.setIdle();
		virus.infect(factory);
	}

	class AttackMoveInterrupt extends Action {
		@Override
		public boolean act(float delta) {
			if(!moving)
				return true;
			if (factory == null || factory.isInfected()) {
				createRemove();
				return true;
			}
			return false;
		}
	}
	
	public void init() {
		virus = (Virus) getActor();
		if (virus.getLevel().getFactories().size == 0) return;

		Factory closest = null;
		CellPath shortest = null;
		int slot = 0;
		for (Factory factory : virus.getLevel().getFactories()) {
			if(factory.isInfected())
				continue;
			SlottedCellPathContainer scpc = factory.getPathToObstacle(Math.round(factory.getX()), Math.round(factory.getY()), virus);
			CellPath cp = scpc.cp;
			if (cp != null && cp.array.size != 0 && (shortest == null || cp.array.size < shortest.array.size)) {
				closest = factory;
				shortest = cp;
				slot = scpc.slot;
			}
		}
		if(closest == null) {
			return;
		}
		closest.slots[slot]++;
		this.factory = closest;
		addAction(new ParallelAction(new AttackMoveInterrupt(), 
				new SequenceAction(new MoveToAttack(closest.getX(), closest.getY(), shortest), new RunnableAction() {
					@Override
					public void run() {
						createRemove();
					}
				}
		)));
	}

	@Override
	public boolean act(float delta) {
		if (getActions().size == 0) init();
		return super.act(delta);
	}
}
