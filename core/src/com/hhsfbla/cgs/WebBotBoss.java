package com.hhsfbla.cgs;

import com.badlogic.gdx.utils.Array;
import com.hhsfbla.cgs.Enemy.DyingAction;

public class WebBotBoss extends Rootkit {
	private static int PORTS_TO_OPEN = 1;
	private static int DELAY = 20;
	private static float time;

	@Override
	public void act(float delta) {
		super.act(delta);

		time += delta;
		if (time >= DELAY) {
			int portsOpened = 0;

			final Array<Obstacle> obstacles = new Array<>(getLevel().getObstacles());
			obstacles.shuffle();

			for (Obstacle o : obstacles) {
				if (o instanceof TemporaryEnemySpawn && !o.isBlocked()) {
					final TemporaryEnemySpawn port = (TemporaryEnemySpawn) o;
					port.setHealth(port.MAX_HEALTH);
					port.addAction(port.new AppearAction());

					portsOpened++;
					if (portsOpened >= PORTS_TO_OPEN) break;
				}
			}

			time = 0;
		}
	}

	@Override
	public void setHealth(int health) {
		super.setHealth(health);
		if (health <= 0) {
			clearActions();
			for (Enemy e : getLevel().getEnemies()) {
				if (e != this) e.setHealth(0);
			}
			addActionOnce(new BossDyingAction());
		}
	}

	public class BossDyingAction extends DyingAction {
		@Override
		protected void end() {
			super.end();
			getLevel().win();
		}
	}
}
