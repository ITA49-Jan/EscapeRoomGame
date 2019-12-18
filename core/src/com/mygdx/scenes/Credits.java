package com.mygdx.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.engine.GameObject;
import com.mygdx.engine.Scene;
import com.mygdx.engine.SceneManager;
import com.mygdx.engine.TTF_Font;
import com.mygdx.game.Game;
import com.mygdx.game.Timer;

/**
 * Created by ita49 on 30.01.2017.
 */
public class Credits extends Scene {

    Music music;
    GameObject credits_tex;
    TTF_Font font_12;


    @Override
    public void init()
    {
        credits_tex = new GameObject("credits.png");

        music = Gdx.audio.newMusic(Gdx.files.internal("DST-RoboticDreams.ogg"));
        //music.play();

        // UI
        font_12 = new TTF_Font(35, "BedandBreakfast-Regular.TTF", false);

        font_12.text = "Du brauchtest " + Timer.updateAsHours() + " um den Test zu klauen";
        font_12.position = new Vector2(100, 425);
        font_12.bitmapFont.setColor(0f, 0f, 1f, 1f);

        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons.atlas"));
        Skin skin = new Skin();
        skin.addRegions(buttonAtlas);

        TextButton.TextButtonStyle textStyle = new TextButton.TextButtonStyle();
        textStyle.font = font_12.bitmapFont;
        textStyle.up = skin.getDrawable("btn_normal");
        textStyle.down = skin.getDrawable("btn_down");
        textStyle.over = skin.getDrawable("btn_over");

        final TextButton btn2 = new TextButton("Beenden", textStyle);
        btn2.setPosition(300, 50);
        Game.canvas.addActor(btn2);


        btn2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });

        //push everything to scene stack
        this.add(credits_tex);
        this.add(font_12);
        this.add(Game.canvas);
    }

    @Override
    public void update()
    {

    }
}
