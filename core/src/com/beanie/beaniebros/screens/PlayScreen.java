package com.beanie.beaniebros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.beanie.beaniebros.BeanieBros;

/**
 * Created by janu2 on 17/04/2018.
 */

public class PlayScreen implements Screen {

    private BeanieBros game;
    private OrthographicCamera camera;
    private Viewport viewport;

    public PlayScreen(BeanieBros game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1280,720,camera);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
