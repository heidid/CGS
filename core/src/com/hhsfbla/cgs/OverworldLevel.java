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
		addActor(new OverworldActor(2, 3, "server.png"));
		addActor(new OverworldActor(8, 3, "router.png"));
		addActor(new OverworldActor(8, 5, "computer.png"));
		addActor(new OverworldActor(11, 3, "computer.png"));
		addActor(new OverworldActor(8, 1, "computer.png"));
		addActor(new OverworldActor(5, 3, "switch.png"));
		//double the size
		for(AnimatedActor a : overworldActors)
			a.setSize(a.getWidth()*2, a.getHeight()*2);
		overworldActors.get(0).setLevel(new Level1());
		overworldActors.get(1).setLevel(new Level1());
		overworldActors.get(2).setLevel(new Level2());
		overworldActors.get(3).setLevel(new Level3());
		overworldActors.get(4).setLevel(new Level4());
		//set connections
		OverworldActor.Connector.connectH(overworldActors.get(0), overworldActors.get(5));
		OverworldActor.Connector.connectV(overworldActors.get(1), overworldActors.get(2));
		OverworldActor.Connector.connectV(overworldActors.get(4), overworldActors.get(1));
		OverworldActor.Connector.connectH(overworldActors.get(1), overworldActors.get(3));
		OverworldActor.Connector.connectH(overworldActors.get(5), overworldActors.get(1));
		player.setOverworldActor(overworldActors.get(0));
		player.addListener(player.new OverworldLevelInputListener(screen));
	}

	public OverworldPlayer getPlayer() {
		return player;
	}

}
