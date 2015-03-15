package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

/**
 * Represents a selectable level on the OverworldScreen
 */
public class OverworldLevel extends Group {
	Array<OverworldActor> overworldActors = new Array<>();
	StageScreen screen;
	OverworldPlayer player;

	public OverworldLevel() {

	}

	public Array<OverworldActor> getOverworldActors() {
		return overworldActors;
	}

	public void addActor(OverworldActor actor) {
		overworldActors.add(actor);
		super.addActor(actor);
	}

	public boolean removeActor(OverworldActor actor) {
		overworldActors.removeValue(actor, true);
		return super.removeActor(actor);
	}

	public void setScreen(OverworldScreen screen){
		this.screen = screen;
		player = new OverworldPlayer();
		//define locations and graphics
		addActor(player);
		addActor(new OverworldActor(2, 4, "computer.png")); 			//0, first computer
		addActor(new OverworldCircle(4, 4, 1)); 						//1, first computer
		addActor(new OverworldCircle(6, 4, 2)); 						//2, router
		addActor(new OverworldActor(7, 4, "router.png", 0.8f, 0.5f)); 	//3, router
		addActor(new OverworldCircle(6, 6, 1)); 						//4, computer on top
		addActor(new OverworldActor(6, 8, "computer.png")); 			//5, computer on top
		addActor(new OverworldCircle(6, 2, 0)); 						//6, computer on bottom
		addActor(new OverworldActor(6, 1, "computer.png")); 			//7, computer on bottom
		addActor(new OverworldActor(10, 4, "switch.png", 0.8f, 0.5f)); 	//8, switch
		addActor(new OverworldCircle(9, 4, 0)); 						//9, switch
		addActor(new OverworldActor(14, 4, "server.png", 0.8f, 0.5f)); 	//10, server
		addActor(new OverworldCircle(13, 4, 0)); 						//11, server

		//double the size
		for(AnimatedActor a : overworldActors){
			a.setSize(a.getWidth()*2, a.getHeight()*2);
		}
		overworldActors.get(1).setLevel(new Level1());
		overworldActors.get(4).setLevel(new Level2());
		overworldActors.get(6).setLevel(new Level3());
		overworldActors.get(2).setLevel(new Level4()); //router
		//set connections
		OverworldActor.Connector.connectH(overworldActors.get(1), overworldActors.get(2));
		OverworldActor.Connector.connectV(overworldActors.get(6), overworldActors.get(2));
		OverworldActor.Connector.connectV(overworldActors.get(2), overworldActors.get(4));
		OverworldActor.Connector.connectH(overworldActors.get(2), overworldActors.get(9));
		OverworldActor.Connector.connectH(overworldActors.get(9), overworldActors.get(11));
		player.setOverworldActor(overworldActors.get(1));
		player.addListener(player.new OverworldLevelInputListener(screen));
	}

	public OverworldPlayer getPlayer() {
		return player;
	}

}
