package com.mygdx.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.engine.GameObject;
import com.mygdx.engine.Scene;
import com.mygdx.engine.SceneManager;
import com.mygdx.engine.TTF_Font;
import com.mygdx.game.CodeGenerator;
import com.mygdx.game.Game;
import com.mygdx.game.Inventory;
import com.mygdx.game.RoomNavigation;

/**
 * Created by ita49 on 30.01.2017.
 */
public class ComputerDesktop extends Scene {

    GameObject mac;


    @Override
    public void init() {

        mac = new GameObject("MacDesktop.png");

        TextureAtlas keyAtlas = new TextureAtlas(Gdx.files.internal("buttons.atlas"));
        Skin itemskin = new Skin();
        itemskin.addRegions(keyAtlas);
        ImageButton.ImageButtonStyle imgStyle = new ImageButton.ImageButtonStyle();
        imgStyle.up = itemskin.getDrawable("text_normal");
        //imgStyle.down = itemskin.getDrawable("login_down");

        final ImageButton file_btn = new ImageButton(imgStyle);
        file_btn.setPosition(450, 180);

        Game.canvas.addActor(file_btn);

        file_btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                file_btn.remove();
                Inventory.add_usbstick();
            }
        });

        RoomNavigation.initBack();

        RoomNavigation.nav_down.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("down");
                SceneManager.load(new ClassRoom());
                RoomNavigation.removeBack();

                file_btn.remove();
            }
        });

        this.add(mac);
        this.add(Game.canvas);
    }

    @Override
    public void update() {


    }

    @Override
    public void resize(int width, int height) {

    }
}