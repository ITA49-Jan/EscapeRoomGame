package com.mygdx.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Game;

/**
 * Created by ita49 on 30.01.2017.
 */
public class Book {
    public ImageButton key_btn;

    public Book()
    {
        TextureAtlas keyAtlas = new TextureAtlas(Gdx.files.internal("items.atlas"));
        Skin itemskin = new Skin();
        itemskin.addRegions(keyAtlas);

        ImageButton.ImageButtonStyle key_style = new ImageButton.ImageButtonStyle();
        key_style.up = itemskin.getDrawable("book_normal");

        key_btn = new ImageButton(key_style);
        key_btn.setPosition(520, 220);
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
