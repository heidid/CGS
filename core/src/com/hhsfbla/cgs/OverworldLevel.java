package com.hhsfbla.cgs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
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
		OverworldCircle c1 = new OverworldCircle(2, 2, 1);
		OverworldCircle c2 = new OverworldCircle(5, 2, 1);
		OverworldCircle c3 = new OverworldCircle(8, 2, 2);
		OverworldCircle router = new OverworldCircle(5, 4, 1);
		OverworldCircle swtch = new OverworldCircle(8, 6, 0); // swtch not switch
		OverworldCircle s = new OverworldCircle(12, 6, 0);
		addActor(player);
		addActor(new OverworldActor(2, 1, "computer.png")); 			// 0, first computer
		addActor(c1); 													// 1, first computer circle
		addActor(router); 												// 2, router circle
		addActor(new OverworldActor(5, 5, "router.png", 0.8f, 0.5f)); 	// 3, router
		addActor(c3); 													// 4, third computer circle
		addActor(new OverworldActor(8, 1, "computer.png")); 			// 5, third computer
		addActor(c2); 													// 6, second computer circle
		addActor(new OverworldActor(5, 1, "computer.png")); 			// 7, second computer
		addActor(new OverworldActor(8, 7, "switch.png", 0.8f, 0.5f));	// 8, switch
		addActor(swtch); 												// 9, switch circle
		addActor(new OverworldActor(13.7f, 6, "server.png", 0.8f, 0.5f));//10, server
		addActor(s); 													// 11, server circle

		//double the size
		for(AnimatedActor a : overworldActors){
			a.setSize(a.getWidth()*2, a.getHeight()*2);
		}
		c1.setLevel(new Level1()); // first computer circle
		c3.setLevel(new Level2()); // third computer circle
		c2.setLevel(new Level3()); // second computer circle
//		router.setLevel(new MainMenuLevel()); //router circle
		//set connections
		OverworldActor.Connector.connectV(c1, router);
		OverworldActor.Connector.connectV(c3, router);
		OverworldActor.Connector.connectV(c2, router);
		OverworldActor.Connector.connectH(swtch, s);
		OverworldActor.Connector.connectH(c1, router);
		OverworldActor.Connector.connectH(router, c2);
		OverworldActor.Connector.connectH(router, c3);
		OverworldActor.Connector.connectV(router, swtch);
		player.setOverworldActor(c1);
		player.addListener(player.new OverworldLevelInputListener(screen));
	}

	public OverworldPlayer getPlayer() {
		return player;
	}

}
