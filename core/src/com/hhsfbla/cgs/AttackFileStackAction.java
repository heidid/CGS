package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public class AttackFileStackAction extends SequenceAction {
	Enemy enemy;
	FileStack fileStack;
	class ContinuallyKillFileStack extends Action {
		@Override
		public boolean act(float delta) {
			if(enemy.getActions().size == 0 && fileStack != null && fileStack.getHealth() > 0) {
				enemy.addAction(new KillFileStack());
				return false;
			}
			return true;
		}
	}
	class KillFileStack extends TemporalAction {
		public KillFileStack(){
			setDuration(1.0f);
		}
		@Override
		public void end(){
			if(fileStack != null && fileStack.getHealth() > 0)
				return;
			fileStack.damage(enemy.getDamage());
		}
		@Override
		protected void update(float percent) {}
	}
	public AttackFileStackAction(Enemy enemy) {
		if (enemy.getLevel().getFileStacks().size == 0)
			return;
		this.enemy = enemy;
		int shortest = Integer.MAX_VALUE;
		FileStack closest = null;
		for(FileStack fs : enemy.getLevel().getFileStacks()) {
			CellPath cp = enemy.getLevel().grid.getPath((int)enemy.getX(), (int)enemy.getY(), (int)fs.getX(), (int)fs.getY());
			if (cp.array.size < shortest){
				shortest = cp.array.size;
				closest = fs;
			}
		}
		addAction(new MoveToAttack(closest.getX(), closest.getY()));
		this.fileStack = closest;
		addAction(new ContinuallyKillFileStack());
	}
}
