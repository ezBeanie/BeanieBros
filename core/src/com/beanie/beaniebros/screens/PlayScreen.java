package com.beanie.beaniebros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.beanie.beaniebros.BeanieBros;
import com.beanie.beaniebros.scenes.HUD;
import com.beanie.beaniebros.sprites.Mario;
import com.beanie.beaniebros.tools.B2WorldCreator;

/**
 * Created by janu2 on 17/04/2018.
 */

public class PlayScreen implements Screen, InputProcessor {

    private BeanieBros game;
    private TextureAtlas atlas;

    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private HUD hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Mario player;
    private Box2DDebugRenderer debugRenderer;

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;

    public PlayScreen(BeanieBros game, SpriteBatch spriteBatch) {
        this.game = game;
        this.spriteBatch = spriteBatch;
        atlas = new TextureAtlas("sprites.pack");

        Gdx.input.setInputProcessor(this);

        camera = new OrthographicCamera();
        viewport = new FitViewport(BeanieBros.VIEW_WIDTH / BeanieBros.PIXEL_PER_METER,
                BeanieBros.VIEW_HEIGHT / BeanieBros.PIXEL_PER_METER, camera);
        hud = new HUD(spriteBatch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / BeanieBros.PIXEL_PER_METER);

        world = new World(new Vector2(0.0f,-30.01f), true);
        player = new Mario(world, this);

        debugRenderer = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);

        camera.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(int key) {

    }

    public void handleInput() {
        if(rightPressed && player.body.getLinearVelocity().x <= 5) {
            player.body.applyLinearImpulse(new Vector2(1.0f, 0.0f),
                    player.body.getWorldCenter(), true);
        }
        if(leftPressed && player.body.getLinearVelocity().x >= -5) {
            player.body.applyLinearImpulse(new Vector2(-1.0f, 0.0f),
                    player.body.getWorldCenter(), true);
        }
    }

    public void update(float delta) {
        handleInput();
        world.step(1/60.0f, 6, 2);

        player.update(delta);

        camera.position.x = player.body.getPosition().x;

        camera.update();
        renderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        spriteBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        renderer.render();

        //debugRenderer.render(world, camera.combined);

        hud.stage.draw();

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        player.draw(spriteBatch);
        //spriteBatch.draw(img,0 - img.getWidth()/2,0 - img.getHeight()/2);
        spriteBatch.end();

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
        map.dispose();
        renderer.dispose();
        world.dispose();
        debugRenderer.dispose();
        hud.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.UP) {
            upPressed = true;
            if(player.getState() != Mario.State.JUMPING) {
                player.body.applyLinearImpulse(new Vector2(0f, 16.5f),
                        player.body.getWorldCenter(), true);
            }
        }
        if(keycode == Input.Keys.DOWN) {
            downPressed = true;
        }
        if(keycode == Input.Keys.LEFT) {
            leftPressed = true;
        }
        if(keycode == Input.Keys.RIGHT) {
            rightPressed = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.UP) {
            upPressed = false;
        }
        if(keycode == Input.Keys.DOWN) {
            downPressed = false;
        }
        if(keycode == Input.Keys.LEFT) {
            leftPressed = false;
        }
        if(keycode == Input.Keys.RIGHT) {
            rightPressed = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
