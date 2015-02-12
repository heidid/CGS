package com.hhsfbla.cgs;

import com.badlogic.gdx.utils.Array;

public class OverworldLevel {
	Array<OverworldActor> actors = new Array<OverworldActor>();
	StageScreen screen;
	OverworldPlayer player;
	
	public OverworldLevel() {
		
	}

	public Array<OverworldActor> getActors() {
		return actors;
	}

	public void setScreen(OverworldScreen screen){
		this.screen = screen;
		Level level = new Level();
		level.setPlayerPosition(7, 7);
		Enemy enemy = new Enemy();
		level.addEnemy(enemy, 7, 0);
		for (int i = 0; i < 5; i++) {
			level.addObstacle(new Wall(), 6 + i, 6);
			level.addObstacle(new Wall(), 6 + i, 8);
			level.addObstacle(new Wall(), 6 + i, 4);
			level.addObstacle(new Wall(), 6 + i, 2);
		}
		for (int i = 0; i < 6; i++) {
			level.addObstacle(new Wall(Wall.DIR_LEFT), 12, 2 + i);
		}
		player = new OverworldPlayer();
		actors.add(new OverworldActor(4, 4));
		actors.add(new OverworldActor(5, 5));
		actors.add(new OverworldActor(6, 4));
		actors.add(new OverworldActor(5, 3));
		actors.get(0).setLevel(level);
		OverworldActor actor;
		actor = actors.get(0);
		actor.setConnections(null, null, actor.conTo(actors.get(1)), actor.conTo(actors.get(3)));
		actor = actors.get(1);
		actor.setConnections(actor.conTo(actors.get(0)), actor.conTo(actors.get(2)), null, null);
		actor = actors.get(2);
		actor.setConnections(null, null, actor.conTo(actors.get(1)), actor.conTo(actors.get(3)));
		actor = actors.get(3);
		actor.setConnections(actor.conTo(actors.get(0)), actor.conTo(actors.get(2)), null, null);
		player.setOverworldActor(actors.get(0));
		player.addListener(player.new OverworldLevelInputListener(screen));
		for(AnimatedActor a : actors)
			a.setScreen(this.screen);
	}
	public Player getPlayer() {
		return player;
	}
	
}