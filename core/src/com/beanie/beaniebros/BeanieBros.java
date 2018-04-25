package com.beanie.beaniebros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beanie.beaniebros.screens.PlayScreen;

public class BeanieBros extends Game {

	public static final int  VIEW_WIDTH = 256;
	public static final int VIEW_HEIGHT = 224;
	public static final float PIXEL_PER_METER = 16.0f;

	public static final short DEFAULT_BIT = 1;
	public static final short MARIO_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short BLOCK_BIT = 16;
	public static final short DESTROYED_BIT = 32;

	private SpriteBatch spriteBatch;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		setScreen(new PlayScreen(this, spriteBatch));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
	}
}
