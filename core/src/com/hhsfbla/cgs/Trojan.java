package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Trojan extends Enemy {

	@SuppressWarnings("serial")
	public Trojan() {

		setIdleSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("trojan-up.png")))));
			put(DIR_DOWN, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("trojan-down.png")))));
			put(DIR_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("trojan-left.png")))));
			put(DIR_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("trojan-right.png")))));
			put(DIR_UP_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("trojan-up-left.png")))));
			put(DIR_UP_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("trojan-up-right.png")))));
			put(DIR_DOWN_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("trojan-down-left.png")))));
			put(DIR_DOWN_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("trojan-down-right.png")))));
		}});
		setMoveSprite(getIdleSprite());
		
		final Animation leftDie = new Animation(0.03f, 
			new TextureRegion(new Texture(Gdx.files.internal("trojan-left-die-9.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-left-die-8.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-left-die-7.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-left-die-6.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-left-die-5.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-left-die-4.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-left-die-3.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-left-die-2.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-left-die-1.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-left-die-0.png"))));
		final Animation rightDie = new Animation(0.03f, 
			new TextureRegion(new Texture(Gdx.files.internal("trojan-right-9.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-right-8.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-right-7.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-right-6.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-right-5.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-right-4.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-right-3.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-right-2.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-right-1.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("trojan-right-0.png"))));
        final Animation downDie = new Animation(0.03f, 
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-die-9.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-die-8.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-die-7.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-die-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-die-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-die-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-die-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-die-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-die-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-die-0.png"))));
        final Animation upDie = new Animation(0.03f, 
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-die-9.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-die-8.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-die-7.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-die-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-die-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-die-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-die-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-die-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-die-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-die-0.png"))));
        final Animation upRightDie = new Animation(0.03f, 
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-right-die-9.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-right-die-8.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-right-die-7.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-right-die-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-right-die-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-right-die-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-right-die-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-right-die-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-right-die-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-right-die-0.png"))));
        final Animation upLeftDie = new Animation(0.03f, 
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-left-die-9.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-left-die-8.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-left-die-7.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-left-die-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-left-die-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-left-die-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-left-die-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-left-die-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-left-die-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-up-left-die-0.png"))));
        final Animation downLeftDie = new Animation(0.03f, 
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-left-die-9.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-left-die-8.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-left-die-7.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-left-die-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-left-die-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-left-die-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-left-die-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-left-die-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-left-die-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-left-die-0.png"))));
        final Animation downRightDie = new Animation(0.03f, 
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-right-die-9.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-right-die-8.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-right-die-7.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-right-die-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-right-die-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-right-die-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-right-die-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-right-die-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-right-die-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("trojan-down-right-die-0.png"))));
        
		setDyingSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, upDie);
			put(DIR_DOWN, downDie);
			put(DIR_LEFT, leftDie);
			put(DIR_RIGHT, rightDie);
			put(DIR_UP_LEFT, upLeftDie);
			put(DIR_UP_RIGHT, upRightDie);
			put(DIR_DOWN_LEFT, downLeftDie);
			put(DIR_DOWN_RIGHT, downRightDie);
		}});
		
		setHurtSprite(new TreeMap<Integer, Animation>() {{
            put(DIR_UP, new Animation(0.05f, 
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-up copy.png"))),
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-up.png")))));
            put(DIR_DOWN, new Animation(0.05f, 
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-down copy.png"))),
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-down.png")))));
            put(DIR_LEFT, new Animation(0.05f, 
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-left copy.png"))),
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-left.png")))));
            put(DIR_RIGHT, new Animation(0.05f, 
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-right copy.png"))),
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-right.png")))));
            put(DIR_UP_LEFT, new Animation(0.05f, 
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-up-left copy.png"))),
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-up-left.png")))));
            put(DIR_UP_RIGHT, new Animation(0.05f, 
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-up-right copy.png"))),
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-up-right.png")))));
            put(DIR_DOWN_LEFT, new Animation(0.05f, 
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-down-left copy.png"))),
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-down-left.png")))));
            put(DIR_DOWN_RIGHT, new Animation(0.05f, 
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-down-right copy.png"))),
                    new TextureRegion(new Texture(Gdx.files.internal("trojan-down-right.png")))));
    }});
	}
}
