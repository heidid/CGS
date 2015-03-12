package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

/**
 * Represents a selectable level on the OverworldScreen
 */
public class OverworldLevel extends Group {
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		addActor(new OverworldActor(2, 4, "computer.png")); //0
		addActor(new OverworldCircle(4, 4, 1)); //1
		addActor(new OverworldCircle(6, 4, 2)); //2
		addActor(new OverworldActor(7, 4, "router.png", 0.8f, 0.5f)); //3
		addActor(new OverworldCircle(6, 6, 1)); //4
		addActor(new OverworldActor(6, 8, "computer.png")); //5
		addActor(new OverworldCircle(6, 2, 0)); //6
		addActor(new OverworldActor(6, 1, "computer.png")); //7
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
		OverworldActor.Connector.connectV(overworldActors.get(2), overworldActors.get(6));
		OverworldActor.Connector.connectV(overworldActors.get(2), overworldActors.get(4));
//		OverworldActor.Connector.connectV(overworldActors.get(2), overworldActors.get(6));
		player.setOverworldActor(overworldActors.get(1));
		player.addListener(player.new OverworldLevelInputListener(screen));
	}

	public OverworldPlayer getPlayer() {
		return player;
	}

}
