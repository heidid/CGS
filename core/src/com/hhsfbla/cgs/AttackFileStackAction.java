package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public class AttackFileStackAction extends SequenceAction {
	Enemy enemy;
	FileStack fileStack;
	boolean moving = true;

	class ContinuallyKillFileStack extends Action {
		KillFileStack kfs = null;
		@Override
		public boolean act(float delta) {
			moving = false;
			if (fileStack != null && fileStack.getHealth() > 0) {
				if(kfs == null || (kfs.getTime() >= kfs.getDuration())) {
					kfs = new KillFileStack();
					enemy.addAction(kfs);
				}
				return false;
			}
			fileStack.enemiesTargettingMe--;
			return true;
		}
	}
	class AttackMoveInterrupt extends Action {
		float time;
		@Override
		public boolean act(float delta) {
			if(!moving)
				return true;
			time += delta;
			if(time > 0.1) {
				if (fileStack == null || fileStack.getHealth() <= 0) {
					enemy.clearActions();
					enemy.setIdle();
					enemy.addAction(new AttackFileStackAction());
					return true;
				}
				time = 0;
				
			}
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	class KillFileStack extends TemporalAction {
		public KillFileStack() {
			setDuration(1.0f);
		}

		@Override
		public void end(){
			if (fileStack != null && fileStack.getHealth() > 0) {
				fileStack.damage(enemy.getDamage());
			}
		}

		@Override
		protected void update(float percent) {}
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
			addAction(new DelayAction(0.1f));
			addAction(new AttackFileStackAction());
			return;
		}
		closest.enemiesTargettingMe++;
		this.fileStack = closest;
		addAction(new ParallelAction(new AttackMoveInterrupt(), new SequenceAction(new MoveToAttack(closest.getX(), closest.getY(), shortest), new ContinuallyKillFileStack())));
		addAction(new AttackFileStackAction());
	}

	@Override
	public boolean act(float delta) {
		if (getActions().size == 0) init();
		return super.act(delta);
	}
}
