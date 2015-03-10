package com.hhsfbla.cgs;

public class File extends Item {

	public File() {
		setSprite(Images.get("file.png"));
		setSize(3/5f, 4/5f);
	}

	// n = number of asterisks
	public File(int n) {
		setSprite(Images.get("file-pw"+n+".png"));
		setSize(3/5f, 4/5f);
	}
}
