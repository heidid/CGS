package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class AttackFileStackAction extends SequenceAction {
	Enemy enemy;
	FileStack fileStack;
	boolean moving = true;
	KillFileStack kfs = null;

	void remove() {
		enemy.clearActions();
		enemy.setIdle();
	}
	
	public void createRemove() {
		remove();
		enemy.addAction(new AttackFileStackAction());
	}

	class ContinuallyKillFileStack extends Action {
		@Override
		public boolean act(float delta) {
			moving = false;
			if (fileStack != null) {
				if(fileStack.getHealth() > 0) {
					if(kfs == null || kfs.isFinished()) {
						kfs = new KillFileStack();
						enemy.addAction(kfs);
					}
				} else {
					fileStack = null;
				}
			}
			if(kfs == null || kfs.isFinished())
				return true;
			return false;
		}
	}
	
	class VirusInterrupt extends Action {

		@Override
		public boolean act(float delta) {
			if (!(enemy instanceof Virus))
				return true;
			for (Factory f : enemy.getLevel().factories) {
				if(!f.isInfected()) {
					remove();
					enemy.addAction(Actions.sequence(new InfectFactoryAction(), new AttackFileStackAction()));
					return true;
				}
			}
			return false;
		}
		
	}

	class AttackMoveInterrupt extends Action {
		@Override
		public boolean act(float delta) {
			if(!moving)
				return true;
			if (fileStack == null || fileStack.getHealth() <= 0 && (kfs == null || kfs.isFinished())) {
				createRemove();
				return true;
			}
			return false;
		}
	}

	class KillFileStack extends IsFinishedSequenceAction {
		public KillFileStack() {
			float ox = enemy.getX(), oy = enemy.getY();
			float x = (fileStack.getX() - ox) / 4;
			float y = (fileStack.getY() - oy) / 4;
			MoveByAction mba = new MoveByAction();
			mba.setAmountX(x);
			mba.setAmountY(y);
			mba.setDuration(0.5f);
			addAction(mba);
			this.addAction(new RunnableAction() {
				@Override
				public void run() {
					if(fileStack != null)
						fileStack.damage(enemy.getDamage());
				}
			});
			MoveByAction mba2 = new MoveByAction();
			mba2.setAmountX(-x);
			mba2.setAmountY(-y);
			mba2.setDuration(0.5f);
			addAction(mba2);
			addFinishedAction();
		}
	}

	public void init() {
		enemy = (Enemy) getActor();
		enemy.setCanCollide(false);
		if (enemy.getLevel().getFileStacks().size == 0) {
			return;
		}

		FileStack closest = null;
		CellPath shortest = null;
		int slot = 0;
		for (FileStack fs : enemy.getLevel().getFileStacks()) {
			if(fs.getHealth() <= 0)
				continue;
			SlottedCellPathContainer scpc = fs.getPathToObstacle(Math.round(fs.getX()), Math.round(fs.getY()), enemy);
			CellPath cp = scpc.cp;
			if (cp != null && cp.array.size != 0 && (shortest == null || cp.array.size < shortest.array.size)) {
				closest = fs;
				shortest = cp;
				slot = scpc.slot;
			}
		}
		if(closest == null) {
			return;
		}
		closest.slots[slot]++;
		this.fileStack = closest;
		addAction(new ParallelAction(new VirusInterrupt(), new AttackMoveInterrupt(), new SequenceAction(new MoveToAttack(closest.getX(), closest.getY(), shortest), new ContinuallyKillFileStack())));
		addAction(new RunnableAction() {
			@Override
			public void run() {
				createRemove();
			}
		});
	}

	@Override
	public boolean act(float delta) {
		if (getActions().size == 0) init();
		return super.act(delta);
	}
}
