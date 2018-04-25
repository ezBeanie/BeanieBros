package com.beanie.beaniebros.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.beanie.beaniebros.BeanieBros;
import com.beanie.beaniebros.scenes.HUD;

/**
 * Created by janu2 on 25/04/2018.
 */

public class Block extends InteractiveTileObject {

    private boolean isActive = true;

    private static TiledMapTileSet tileSet;
    private final int BLANK_BLOCK = 28;

    public Block(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        tileSet = map.getTileSets().getTileSet("tileset");
        fixture.setUserData(this);
        setCategoryFitler(BeanieBros.BLOCK_BIT);
    }

    @Override
    public void onHeadHit() {
        if(isActive) {
            getCell().setTile(tileSet.getTile(BLANK_BLOCK));
            HUD.addScore(200);
            isActive = false;
        }
    }
}
