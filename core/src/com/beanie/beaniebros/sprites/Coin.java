package com.beanie.beaniebros.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.beanie.beaniebros.BeanieBros;
import com.beanie.beaniebros.scenes.HUD;

/**
 * Created by janu2 on 23/04/2018.
 */

public class Coin extends InteractiveTileObject {

    public Coin(World world, TiledMap map, Rectangle bounds) {

        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFitler(BeanieBros.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Coin","Collision");
        setCategoryFitler(BeanieBros.DESTROYED_BIT);
        HUD.addScore(200);
        getCell().setTile(null);
    }

}
