package com.beanie.beaniebros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beanie.beaniebros.screens.PlayScreen;

public class BeanieBros extends Game {
	private SpriteBatch spriteBatch;

	public static final int  VIEW_WIDTH = 256;
	public static final int  VIEW_HEIGTH = 224;

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
