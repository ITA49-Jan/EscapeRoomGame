package com.mygdx.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Game;

/**
 * Created by ita49 on 30.01.2017.
 */
public class Door {

    public ImageButton key_btn;

    public Door()
    {
        TextureAtlas keyAtlas = new TextureAtlas(Gdx.files.internal("items.atlas"));
        Skin itemskin = new Skin();
        itemskin.addRegions(keyAtlas);

        ImageButton.ImageButtonStyle key_style = new ImageButton.ImageButtonStyle();
        key_style.up = itemskin.getDrawable("door_normal");

        key_btn = new ImageButton(key_style);
        key_btn.setPosition(130, 150);
        //Game.stage.addActor(key_btn);

    }

    public void add()
    {
        Game.canvas.addActor(key_btn);
    }

    public void remove()
    {
        key_btn.remove();
    }
}
