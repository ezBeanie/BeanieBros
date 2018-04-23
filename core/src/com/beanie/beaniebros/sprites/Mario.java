package com.beanie.beaniebros.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.beanie.beaniebros.BeanieBros;
import com.beanie.beaniebros.screens.PlayScreen;

import java.awt.geom.RectangularShape;

/**
 * Created by janu2 on 23/04/2018.
 */

public class Mario extends Sprite {

    public enum State {
        FALLING,
        JUMPING,
        STANDING,
        RUNNUNG
    }

    public State currentState;
    public State previousState;
    private float stateTimer;

    private Animation marioRun;
    private Animation marioJump;

    private boolean runningRight;

    public World world;
    public Body body;
    private TextureRegion marioStand;

    public Mario(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0.0f;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), i * 16, 11, 16, 16));
        }
        marioRun = new Animation(0.1f, frames);
        frames.clear();

        for(int i = 4; i < 6; i++) {
            frames.add(new TextureRegion(getTexture(), i * 16, 11, 16, 16));
        }
        marioJump = new Animation(0.1f, frames);
        frames.clear();

        defineMario();

        marioStand = new TextureRegion(getTexture(),1,11,16,16);
        setBounds(0,0, 16 / BeanieBros.PIXEL_PER_METER, 16 / BeanieBros.PIXEL_PER_METER);
        setRegion(marioStand);
    }

    public void update(float delta) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
    }

    public TextureRegion getFrame(float delta) {
        currentState = getState();
        return null;
    }

    public State getState() {
        if(body.getLinearVelocity().y > 0 || body.getLinearVelocity().y < 0 && previousState == State.JUMPING) {
            return State.FALLING;
        }
        else if(body.getLinearVelocity().y < 0) {
            return State.FALLING;
        }
        else if(body.getLinearVelocity().x != 0) {
            return State.RUNNUNG;
        }
        else {
            return State.STANDING;
        }
    }

    public void defineMario() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / BeanieBros.PIXEL_PER_METER,64 / BeanieBros.PIXEL_PER_METER);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(7.5f / BeanieBros.PIXEL_PER_METER);

        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);
    }

}
