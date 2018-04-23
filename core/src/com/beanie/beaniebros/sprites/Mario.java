package com.beanie.beaniebros.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.beanie.beaniebros.BeanieBros;

/**
 * Created by janu2 on 23/04/2018.
 */

public class Mario extends Sprite {

    public World world;
    public Body body;

    public Mario(World world) {
        this.world = world;
        defineMario();
    }

    public void defineMario() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / BeanieBros.PIXEL_PER_METER,64 / BeanieBros.PIXEL_PER_METER);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(5.0f / BeanieBros.PIXEL_PER_METER);

        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);
    }

}
