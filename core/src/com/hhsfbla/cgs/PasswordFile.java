package com.hhsfbla.cgs;

public class PasswordFile extends File {
	private int type;	// number of asterisks

	public PasswordFile(int type) {
		setType(type);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		setSprite(Images.get("file-pw" + (type == 1 ? "" : type) + ".png"));
	}
}
