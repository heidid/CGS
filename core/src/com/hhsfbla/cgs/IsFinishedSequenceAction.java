package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class IsFinishedSequenceAction extends SequenceAction {
	private boolean finished = false;

	public void addFinishedAction() {
		addAction(new RunnableAction() {
			@Override
			public void run() {
				finished = true;
			}
		});
	}
	
	public void addFinishedAction(RunnableAction ra) {
		addAction(ra);
		addFinishedAction();
	}

	public boolean isFinished() {
		return finished;
	}
}
