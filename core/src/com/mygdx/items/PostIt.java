package com.mygdx.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.engine.SceneManager;
import com.mygdx.game.Game;
import com.mygdx.game.Inventory;
import com.mygdx.game.RoomNavigation;
import com.mygdx.scenes.Computer;

/**
 * Created by ita49 on 26.01.2017.
 */
public class PostIt {

    public ImageButton key_btn;

    public PostIt()
    {
        TextureAtlas keyAtlas = new TextureAtlas(Gdx.files.internal("items.atlas"));
        Skin itemskin = new Skin();
        itemskin.addRegions(keyAtlas);

        ImageButton.ImageButtonStyle key_style = new ImageButton.ImageButtonStyle();
        key_style.up = itemskin.getDrawable("postit_normal");

        key_btn = new ImageButton(key_style);
        key_btn.setPosition(100, 325);
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
