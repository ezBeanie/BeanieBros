package com.beanie.beaniebros.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.beanie.beaniebros.BeanieBros;
import com.beanie.beaniebros.screens.PlayScreen;

/**
 * Created by janu2 on 23/04/2018.
 */

public class Mario extends Sprite {

    public enum State {
        FALLING,
        JUMPING,
        STANDING,
        RUNNING
    }

    public enum BodyParts {
        HEAD
    }

    public State currentState;
    public State previousState;
    private float stateTimer;

    private Animation<TextureRegion> marioRun;

    private boolean runningRight;

    public World world;
    public Body body;
    private TextureRegion marioStand;
    private TextureRegion marioJump;
    private TextureRegion marioFall;

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

        marioStand = new TextureRegion(getTexture(),1,11,16,16);
        marioJump = new TextureRegion(getTexture(),80,11,16,16);
        marioFall = new TextureRegion(getTexture(), 48,11,16,16);

        defineMario();

        setBounds(0,0, 16 / BeanieBros.PIXEL_PER_METER, 16 / BeanieBros.PIXEL_PER_METER);
        setRegion(marioStand);
    }

    public void update(float delta) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
    }

    public TextureRegion getFrame(float delta) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case JUMPING:
                region = marioJump;
                break;
            case RUNNING:
                region = marioRun.getKeyFrame(stateTimer,true);
                break;
            case FALLING:
                region = marioFall;
                break;
            case STANDING:
            default:
                region = marioStand;
            break;
        }

        if((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        }
        else if ((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        if(currentState == previousState) {
            stateTimer = stateTimer + delta;
        }
        else {
            stateTimer = 0;
        }

        previousState = currentState;

        return region;
    }

    public State getState() {
        if(body.getLinearVelocity().y > 0.0f ||
                body.getLinearVelocity().y < 0.0f && previousState == State.JUMPING) {
            return State.JUMPING;
        }
        else if(body.getLinearVelocity().y < 0) {
            return State.FALLING;
        }
        else if(body.getLinearVelocity().x != 0) {
            return State.RUNNING;
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
        circleShape.setRadius(7f / BeanieBros.PIXEL_PER_METER);
        fixtureDef.filter.categoryBits = BeanieBros.MARIO_BIT;
        fixtureDef.filter.maskBits = BeanieBros.DEFAULT_BIT | BeanieBros.COIN_BIT | BeanieBros.BRICK_BIT | BeanieBros.BLOCK_BIT;

        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / BeanieBros.PIXEL_PER_METER, 7.0f / BeanieBros.PIXEL_PER_METER),
                new Vector2(2 / BeanieBros.PIXEL_PER_METER, 7.0f / BeanieBros.PIXEL_PER_METER));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef).setUserData(BodyParts.HEAD);
    }

}
