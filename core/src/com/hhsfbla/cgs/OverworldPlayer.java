package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class OverworldPlayer extends MovableActor {
	OverworldActor current;

	@SuppressWarnings("serial")
	public OverworldPlayer() {
		setIdleSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, Images.get("player-up.png")));
			put(DIR_DOWN, new Animation(0, Images.get("player-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("player-left.png")));
			put(DIR_RIGHT, new Animation(0, Images.get("player-right.png")));
		}});
		setMoveSprite(getIdleSprite());
		setOrigin(Align.bottom);
		setSpeed(2);
	}

	public void setOverworldActor(OverworldActor current) {
		this.current = current;
		setX(current.getX());
		setY(current.getY());
	}

	public class OverworldLevelInputListener extends InputListener {

		OverworldScreen os;

		public OverworldLevelInputListener(OverworldScreen os) {
			this.os = os;
		}

		public boolean tryConnection(OverworldConnection con) {
			if (con != null) {
				con.sa.restart();
				addAction(con.sa);
				current = con.oa;
				return true;
			}
			return false;
		}

		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			if (getActions().size != 0)
				return true;
			if (keycode == Input.Keys.W) {
				if(tryConnection(current.u))
					setDirection(DIR_UP);
				return true;
			} else if (keycode == Input.Keys.S) {
				if(tryConnection(current.d))
					setDirection(DIR_DOWN);
				return true;
			} else if (keycode == Input.Keys.A) {
				if(tryConnection(current.l))
					setDirection(DIR_LEFT);
				return true;
			} else if (keycode == Input.Keys.D) {
				if(tryConnection(current.r))
					setDirection(DIR_RIGHT);
				return true;
			} else if (keycode == Input.Keys.ENTER) {
				if (((OverworldCircle)current).getType()>0
						&& current.getLevel() != null) {
					os.game.setScreen(new LevelScreen(os.game, os.stage,
							current.getLevel()));
					//initialize the level
					current.getLevel().init();
				}
			} else
				setIdle();
			return true;
		}
	}
}
