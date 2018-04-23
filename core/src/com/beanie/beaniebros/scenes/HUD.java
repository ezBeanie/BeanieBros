package com.beanie.beaniebros.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.beanie.beaniebros.BeanieBros;
import java.lang.annotation.Target;

/**
 * Created by janu2 on 23/04/2018.
 */

public class HUD {

    public Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private BitmapFont font;

    private int worldTime;
    private float timeCount;
    private int score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label marioLabel;

    public HUD(SpriteBatch spriteBatch) {
        worldTime = 300;
        timeCount = 0;
        score = 0;

        camera = new OrthographicCamera();
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        viewport = new FitViewport(BeanieBros.VIEW_WIDTH, BeanieBros.VIEW_HEIGHT, camera);
        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        //table.setDebug(true);

        countdownLabel = new Label(String.format("%03d", worldTime), new Label.LabelStyle(font,
                Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(font,
                Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(font, Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(font, Color.WHITE));
        worldLabel = new Label("WORLD", new Label.LabelStyle(font, Color.WHITE));
        marioLabel = new Label("MARIO", new Label.LabelStyle(font, Color.WHITE));

        table.add().width(24);
        table.add(marioLabel).expandX().padTop(8).left();
        table.add(worldLabel).expandX().padTop(8).center();
        table.add(timeLabel).expandX().padTop(8).right();
        table.add().width(25);
        table.row();
        table.add().width(24);
        table.add(scoreLabel).expandX().left();
        table.add(levelLabel).expandX().center();
        table.add(countdownLabel).expandX().right();
        table.add().width(25);

        stage.addActor(table);
    }

}
