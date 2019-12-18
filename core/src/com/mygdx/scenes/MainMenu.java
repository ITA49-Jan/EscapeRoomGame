package com.mygdx.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.engine.GameObject;
import com.mygdx.engine.Scene;
import com.mygdx.engine.SceneManager;
import com.mygdx.engine.TTF_Font;
import com.mygdx.game.*;

/**
 * Created by ita49 on 25.01.2017.
 */
public class MainMenu extends Scene {
    Music music;

    GameObject room_tex;
    GameObject inventary_tex;
    GameObject credits_tex;

    TTF_Font font_12;


    boolean click_switch = false;

    int x = -600;

    float rot = 1f;
    float speed = 2f;
    float temp;

    @Override
    public void init()
    {
        inventary_tex = new GameObject("hud.png");
        room_tex = new GameObject("menu.png");
        credits_tex = new GameObject("credits.png");

        temp = rot;

        music = Gdx.audio.newMusic(Gdx.files.internal("DST-RoboticDreams.ogg"));
        //music.play();

        font_12 = new TTF_Font(50, "BedandBreakfast-Regular.TTF", false);

        font_12.text = "School Escape Game";
        font_12.position = new Vector2(200, 450);
        font_12.bitmapFont.setColor(0f, 0f, 1f, 1f);

        // UI
        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons.atlas"));
        Skin skin = new Skin();
        skin.addRegions(buttonAtlas);

        TextButton.TextButtonStyle textStyle = new TextButton.TextButtonStyle();
        textStyle.font = font_12.bitmapFont;
        textStyle.up = skin.getDrawable("btn_normal");
        textStyle.down = skin.getDrawable("btn_down");
        textStyle.over = skin.getDrawable("btn_over");

        final TextButton btn = new TextButton("Start", textStyle);
        btn.setPosition(300, 275);
        Game.canvas.addActor(btn);

        final TextButton btn2 = new TextButton("Beenden", textStyle);
        btn2.setPosition(300, 200);
        Game.canvas.addActor(btn2);

        btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                System.out.println("load new scene !");
                btn.remove();
                btn2.remove();
                SceneManager.load(new Intro());
            }
        });

        btn2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });

        //push everything to scene stack
        this.add(room_tex);
        //this.add(credits_tex);
        this.add(font_12);
        this.add(Game.canvas);
    }

    @Override
    public void update()
    {
        /*
        x++;

        if(x > 800)
            x = -400;

        font_12.position = new Vector2(x, font_12.position.y);
        */

        rot += speed * Gdx.graphics.getDeltaTime();
        float cos_x = temp * MathUtils.sin(rot);

        font_12.position = new Vector2(font_12.position.x, font_12.position.y + cos_x);
    }
}
