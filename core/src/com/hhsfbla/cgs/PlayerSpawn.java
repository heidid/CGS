package com.hhsfbla.cgs;

public class PlayerSpawn extends SpawnPort {
	public PlayerSpawn() {
		this(DIR_RIGHT);
	}

	public PlayerSpawn(int direction) {
		super(direction);
	}
}
