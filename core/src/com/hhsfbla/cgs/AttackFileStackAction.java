package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class AttackFileStackAction extends SequenceAction {
	Enemy enemy;
	FileStack fileStack;
	boolean moving = true;
	KillFileStack kfs = null;
	
	public void createRemove() {
		for(Action a : this.getActions()) {
			enemy.removeAction(a);
		}
		enemy.removeAction(this);
		enemy.setIdle();
		enemy.act(0);
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
					fileStack.enemiesTargettingMe--;
					fileStack = null;
				}
			}
			if(kfs == null || kfs.isFinished())
				return true;
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
			enemy.setCanCollide(false);
			this.addAction(new AnimatedMoveToAction(fileStack.getX(), fileStack.getY()));
			this.addAction(new RunnableAction() {
				@Override
				public void run() {
					if(fileStack != null)
						fileStack.damage(enemy.getDamage());
				}
			});
			this.addAction(new AnimatedMoveToAction(ox, oy));
			super.addFinishedAction(new RunnableAction() {
				public void run() {
					enemy.setCanCollide(true);
				}
			});
		}
	}

	public void init() {
		enemy = (Enemy) getActor();
		if (enemy.getLevel().getFileStacks().size == 0) return;

		FileStack closest = null;
		CellPath shortest = null;
		for (FileStack fs : enemy.getLevel().getFileStacks()) {
			if(fs.getHealth() <= 0)
				continue;
			CellPath cp = enemy.getLevel().grid.getPathToObstacle((int) fs.getX(), (int) fs.getY(), enemy);
			if (fs.enemiesTargettingMe != fs.maxEnemiesTargettingMe && cp != null && cp.array.size != 0 && (shortest == null || cp.array.size < shortest.array.size)) {
				closest = fs;
				shortest = cp;
			}
		}
		if(closest == null) {
			createRemove();
			return;
		}
		closest.enemiesTargettingMe++;
		this.fileStack = closest;
		addAction(new ParallelAction(new AttackMoveInterrupt(), new SequenceAction(new MoveToAttack(closest.getX(), closest.getY(), shortest), new ContinuallyKillFileStack())));
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
