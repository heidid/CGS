package com.hhsfbla.cgs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class OverworldPlayer extends Player {

	OverworldActor current;
	OverworldActor next;
	
	public OverworldPlayer() {
		super();
	}

	public void setOverworldActor(OverworldActor current) {
		this.current = current;
		this.next = current;
		setX(current.getX());
		setY(current.getY());
	}

	public class OverworldLevelInputListener extends InputListener {
		private boolean up;
		private boolean down;
		private boolean left;
		private boolean right;
		
		public void tryConnection(OverworldConnection con) {
			if(con != null) {
				next = con.oa;
				con.sa.restart();
				addAction(con.sa);
			}
		}
		
		private void handleInput() {
			if (getActions().size != 0)
				return;
			current = next;
			if (up) {
				tryConnection(current.u);
				return;
			} else if (down) {
				tryConnection(current.d);
				return;
			} else if (left) {
				tryConnection(current.l);
				return;
			} else if (right) {
				tryConnection(current.r);
				return;
			} else {
				setIdle();
			}
		}

		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			System.out.println("DOWN");
			switch (keycode) {
			case Input.Keys.UP:
				up = true;
				break;
			case Input.Keys.DOWN:
				down = true;
				break;
			case Input.Keys.LEFT:
				left = true;
				break;
			case Input.Keys.RIGHT:
				right = true;
				break;
			}
			handleInput();
			return true;
		}

		@Override
		public boolean keyUp(InputEvent event, int keycode) {
			System.out.println("UP");
			switch (keycode) {
			case Input.Keys.UP:
				up = false;
				break;
			case Input.Keys.DOWN:
				down = false;
				break;
			case Input.Keys.LEFT:
				left = false;
				break;
			case Input.Keys.RIGHT:
				right = false;
				break;
			}
			handleInput();
			return true;
		}
	}
}
