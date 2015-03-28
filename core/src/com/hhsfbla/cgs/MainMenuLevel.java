package com.hhsfbla.cgs;



public class MainMenuLevel extends Level {

	MainMenu screen;

	public MainMenuLevel() {
		remove(player);
		remove(spawn);
		remove(exit);
		add(new FileStack(), 1, 2);
		add(new FileStack(), 2, 2);
		add(new FileStack(), 2, 3);
		add(new FileStack(), 3, 2);
		add(new FileStack(), 3, 3);
		for (int i=13; i<16; i++) {
			for (int j=2; j<6; j++) {
				add(new FileStack(), i, j);
			}
		}
		for (int i=0; i<4; i++) {
			add(new Wall(), i, 1);
		}
		add(new Enemy(), 0, 0);
		add(new Wall(Wall.DIR_DOWN, true), 4, 0);
		add(new Wall(Wall.DIR_LEFT_DOWN_RIGHT), 4, 1);
		for (int i=5; i<10; i++) {
			add(new Wall(), i, 1);
		}
		add(new Virus(), 7, 0);
		add(new Wall(Wall.DIR_DOWN, true), 10, 0);
		add(new Wall(Wall.DIR_LEFT_DOWN_RIGHT), 10, 1);
		for (int i=11; i<16; i++) {
			add(new Wall(), i, 1);
		}
		add(new Trojan(), 11, 0);
	}

	public void setScreen(MainMenu mainMenu) {
		this.screen = mainMenu;
	}

}
