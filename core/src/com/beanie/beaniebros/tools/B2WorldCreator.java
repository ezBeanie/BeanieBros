package com.beanie.beaniebros.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.beanie.beaniebros.BeanieBros;
import com.beanie.beaniebros.sprites.Brick;
import com.beanie.beaniebros.sprites.Coin;

/**
 * Created by janu2 on 23/04/2018.
 */

public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map) {

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth()/2) / BeanieBros.PIXEL_PER_METER,
                    (rect.getY() + rect.getHeight()/2) / BeanieBros.PIXEL_PER_METER);

            body = world.createBody(bodyDef);

            shape.setAsBox((rect.getWidth()/2) / BeanieBros.PIXEL_PER_METER,
                    (rect.getHeight()/2) / BeanieBros.PIXEL_PER_METER);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth()/2) / BeanieBros.PIXEL_PER_METER,
                    (rect.getY() + rect.getHeight()/2) / BeanieBros.PIXEL_PER_METER);

            body = world.createBody(bodyDef);

            shape.setAsBox((rect.getWidth()/2) / BeanieBros.PIXEL_PER_METER,
                    (rect.getHeight()/2) / BeanieBros.PIXEL_PER_METER);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Brick(world, map, rect);
        }

        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth()/2) / BeanieBros.PIXEL_PER_METER,
                    (rect.getY() + rect.getHeight()/2) / BeanieBros.PIXEL_PER_METER);

            body = world.createBody(bodyDef);

            shape.setAsBox((rect.getWidth()/2) / BeanieBros.PIXEL_PER_METER,
                    (rect.getHeight()/2) / BeanieBros.PIXEL_PER_METER);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Coin(world, map, rect);
        }

    }

}
