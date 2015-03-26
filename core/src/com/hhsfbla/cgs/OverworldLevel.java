package com.hhsfbla.cgs;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

/**
 * Represents a selectable level on the OverworldScreen
 */
public class OverworldLevel extends Group {
	Array<OverworldActor> overworldActors = new Array<>();
	Array<OverworldCircle> levels = new Array<>();
	OverworldPlayer player = new OverworldPlayer();
	OverworldScreen screen;

	// TODO: Add OverworldLevel state saving
	private int state;
	private int current;

	public OverworldLevel(int state, int current) {
		this.state = state;
		this.current = current;
		addActor(player);
	}

	public Array<OverworldActor> getOverworldActors() {
		return overworldActors;
	}

	public void addActor(OverworldCircle actor) {
		levels.add(actor);
		addActor((OverworldActor) actor);
	}

	public boolean removeActor(OverworldCircle actor) {
		levels.removeValue(actor, true);
		return removeActor((OverworldActor) actor);
	}

	public void addActor(OverworldActor actor) {
		overworldActors.add(actor);
		super.addActor(actor);
	}

	public boolean removeActor(OverworldActor actor) {
		overworldActors.removeValue(actor, true);
		return super.removeActor(actor);
	}

	public void setScreen(OverworldScreen screen) {
		this.screen = screen;

		createMap();

		// double the size
		for(AnimatedActor a : overworldActors) {
			a.setSize(a.getWidth() * 2, a.getHeight() * 2);
		}

//		router.setLevel(new MainMenuLevel()); // router circle

		player.setOverworldActor(levels.get(current));
		player.addListener(player.new OverworldLevelInputListener(screen));
	}

	private void createMap() {
		// state 0
		addActor(new OverworldCircle(2, 2, 2, new Level1())); 			// first computer circle
		addActor(new OverworldActor(2, 1, "computer.png")); 			// first computer
		if (state == 0) {
			screen.getUi().addActor(new OverworldDialogBox(100, 300,
					"Press enter to start the level."));
			return;
		}

		// state 1
		final OverworldCircle swtch = new OverworldCircle(5, 4, 0, new Level4()); // swtch not switch

		levels.get(0).setType(1);
		OverworldActor.Connector.connectV(levels.get(0), swtch);
		OverworldActor.Connector.connectH(levels.get(0), swtch);

		addActor(new OverworldCircle(5, 2, 2, new Level2())); 		    // second computer circle
		addActor(new OverworldActor(5, 1, "computer.png")); 			// second computer
		OverworldActor.Connector.connectV(levels.get(1), swtch);

		addActor(new OverworldCircle(8, 2, 0, new Level3())); 			// third computer circle
		addActor(new OverworldActor(8, 1, "computer.png")); 			// third computer
		OverworldActor.Connector.connectH(swtch, levels.get(2));
//		OverworldActor.Connector.connectV(levels.get(2), swtch);

		addActor(swtch); 												// switch circle
		addActor(new OverworldActor(5, 5, "switch.png", 0.8f, 0.5f)); 	// switch

		if (state == 1) {
			screen.getUi().addActor(new OverworldDialogBox(200, 500,
					"Use WASD to navigate the network to the infected computer"));
			return;
		}

		// state 2
		levels.get(1).setType(1);
		levels.get(2).setType(2);
		if (state == 2) {
			return;
		}

		// state 3
		levels.get(2).setType(1);
		levels.get(3).setType(2);
		if (state == 3) {
			return;
		}

		// state 4
		levels.get(3).setType(1);
		OverworldCircle router = new OverworldCircle(8, 6, 2, null);
		addActor(router); 												// router circle
		addActor(new OverworldActor(8, 7, "router.png", 0.8f, 0.5f));	// router
		OverworldActor.Connector.connectV(swtch, router);
		if (state == 4) {
			return;
		}

		// state 5
		levels.get(4).setType(1);
		OverworldCircle s = new OverworldCircle(12, 6, 2, null);
		addActor(s); 													// server circle
		addActor(new OverworldActor(13.7f, 6, "server.png", 0.8f, 0.5f));//server
		OverworldActor.Connector.connectH(router, s);
		if (state == 5) {
			screen.getUi().addActor(new OverworldDialogBox(100, 500,
					"Use WASD to navigate the network to the infected computer."));
			return;
		}
	}

	public OverworldPlayer getPlayer() {
		return player;
	}

}
