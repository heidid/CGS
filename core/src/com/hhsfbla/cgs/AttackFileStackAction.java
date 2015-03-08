package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public class AttackFileStackAction extends SequenceAction {
	Enemy enemy;
	FileStack fileStack;

	class ContinuallyKillFileStack extends Action {
		KillFileStack kfs = null;
		@Override
		public boolean act(float delta) {
			if (fileStack != null && fileStack.getHealth() > 0) {
				if(kfs == null || (kfs.getTime() >= kfs.getDuration())) {
					kfs = new KillFileStack();
					enemy.addAction(kfs);
				}
				return false;
			}
			return true;
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

		int shortest = Integer.MAX_VALUE;
		FileStack closest = null;
		for (FileStack fs : enemy.getLevel().getFileStacks()) {
			if(fs.getHealth() <= 0)
				continue;
			CellPath cp = enemy.getLevel().grid.getPath(
					(int) enemy.getX(), (int) enemy.getY(),
					(int) fs.getX(), (int) fs.getY());
			if (cp.array.size < shortest) {
				shortest = cp.array.size;
				closest = fs;
			}
		}
		if(closest == null) {
			addAction(new DelayAction(0.1f));
			addAction(new AttackFileStackAction());
			return;
		}
		addAction(new MoveToAttack(closest.getX(), closest.getY()));
		this.fileStack = closest;
		addAction(new ContinuallyKillFileStack());
		addAction(new AttackFileStackAction());
	}

	@Override
	public boolean act(float delta) {
		if (getActions().size == 0) init();
		return super.act(delta);
	}
}
