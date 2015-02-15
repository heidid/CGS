package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Virus extends Enemy {

	@SuppressWarnings("serial")
	public Virus() {
		setIdleSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-up.png")))));
			put(DIR_DOWN, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-down.png")))));
			put(DIR_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-left.png")))));
			put(DIR_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-right.png")))));
			put(DIR_UP_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-up-left.png")))));
			put(DIR_UP_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-up-right.png")))));
			put(DIR_DOWN_LEFT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-down-left.png")))));
			put(DIR_DOWN_RIGHT, new Animation(0, new TextureRegion(
					new Texture(Gdx.files.internal("virus-down-right.png")))));
		}});
		setMoveSprite(getIdleSprite());
		
		final Animation leftDie = new Animation(0.03f, 
			new TextureRegion(new Texture(Gdx.files.internal("virus-left-9.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-left-8.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-left-7.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-left-6.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-left-5.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-left-4.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-left-3.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-left-2.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-left-1.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-left-0.png"))));
		final Animation rightDie = new Animation(0.03f, 
			new TextureRegion(new Texture(Gdx.files.internal("virus-right-9.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-right-8.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-right-7.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-right-6.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-right-5.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-right-4.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-right-3.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-right-2.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-right-1.png"))),
			new TextureRegion(new Texture(Gdx.files.internal("virus-right-0.png"))));
        final Animation downDie = new Animation(0.03f, 
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-9.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-8.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-7.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-0.png"))));
        final Animation upDie = new Animation(0.03f, 
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-9.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-8.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-7.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-0.png"))));
        final Animation upRightDie = new Animation(0.03f, 
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-right-9.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-right-8.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-right-7.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-right-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-right-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-right-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-right-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-right-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-right-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-right-0.png"))));
        final Animation upLeftDie = new Animation(0.03f, 
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-left-9.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-left-8.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-left-7.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-left-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-left-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-left-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-left-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-left-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-left-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-up-left-0.png"))));
        final Animation downLeftDie = new Animation(0.03f, 
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-left-9.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-left-8.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-left-7.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-left-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-left-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-left-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-left-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-left-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-left-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-left-0.png"))));
        final Animation downRightDie = new Animation(0.03f, 
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-right-9.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-right-8.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-right-7.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-right-6.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-right-5.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-right-4.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-right-3.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-right-2.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-right-1.png"))),
            new TextureRegion(new Texture(Gdx.files.internal("virus-down-right-0.png"))));
        
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
			put(DIR_UP, new Animation(0.1f, new TextureRegion(
					new Texture(Gdx.files.internal("virus-up copy.png")))));
			put(DIR_DOWN, new Animation(0.03f, new TextureRegion(
					new Texture(Gdx.files.internal("virus-down copy.png")))));
			put(DIR_LEFT, new Animation(0.03f, new TextureRegion(
					new Texture(Gdx.files.internal("virus-left copy.png")))));
			put(DIR_RIGHT, new Animation(0.03f, new TextureRegion(
					new Texture(Gdx.files.internal("virus-right copy.png")))));
			put(DIR_UP_LEFT, new Animation(0.03f, new TextureRegion(
					new Texture(Gdx.files.internal("virus-up-left copy.png")))));
			put(DIR_UP_RIGHT, new Animation(0.03f, new TextureRegion(
					new Texture(Gdx.files.internal("virus-up-right copy.png")))));
			put(DIR_DOWN_LEFT, new Animation(0.03f, new TextureRegion(
					new Texture(Gdx.files.internal("virus-down-left copy.png")))));
			put(DIR_DOWN_RIGHT, new Animation(0.03f, new TextureRegion(
					new Texture(Gdx.files.internal("virus-down-right copy.png")))));
		}});
	}
}
