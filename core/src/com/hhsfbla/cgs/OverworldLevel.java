package com.hhsfbla.cgs;

import com.badlogic.gdx.utils.Array;

/**
 * Represents a selectable level on the OverworldScreen
 */
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
		player = new OverworldPlayer();
		//define locations and graphics
		actors.add(new OverworldActor(2, 3, "server.png"));
		actors.add(new OverworldActor(8, 3, "router.png"));
		actors.add(new OverworldActor(8, 5, "computer.png"));
		actors.add(new OverworldActor(11, 3, "computer.png"));
		actors.add(new OverworldActor(8, 1, "computer.png"));
		actors.add(new OverworldActor(5, 3, "switch.png"));
		//double the size
		for(AnimatedActor a : actors)
			a.setSize(a.getWidth()*2, a.getHeight()*2);
		actors.get(0).setLevel(new Level1());
		actors.get(1).setLevel(new Level1());
		actors.get(2).setLevel(new Level2());
		actors.get(3).setLevel(new Level3());
		actors.get(4).setLevel(new Level4());
		//set connections
		OverworldActor.Connector.connectH(actors.get(0), actors.get(5));
		OverworldActor.Connector.connectV(actors.get(1), actors.get(2));
		OverworldActor.Connector.connectV(actors.get(4), actors.get(1));
		OverworldActor.Connector.connectH(actors.get(1), actors.get(3));
		OverworldActor.Connector.connectH(actors.get(5), actors.get(1));
		player.setOverworldActor(actors.get(0));
		player.addListener(player.new OverworldLevelInputListener(screen));
	}

	public OverworldPlayer getPlayer() {
		return player;
	}

}
