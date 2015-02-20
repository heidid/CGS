package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class AttackFileStackAction extends SequenceAction {
	Enemy enemy;
	FileStack fileStack;
	class KillFileStack extends Action {
		@Override
		public boolean act(float delta) {
			if(fileStack.getHealth() <= 0)
				return true;
			fileStack.damage(enemy.getDamage() * delta);
			return false;
		}
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
		addAction(new MoveToAttack(enemy.getLevel(), enemy, closest.getX(), closest.getY()));
		this.fileStack = closest;
		addAction(new KillFileStack());
	}
}
