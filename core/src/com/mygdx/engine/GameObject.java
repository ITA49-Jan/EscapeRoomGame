package com.mygdx.engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by ita49 on 23.01.2017.
 */
public class GameObject {

    public String filePath = null;
    public Texture texture;
    public Sprite sprite;
    public boolean active;


    public GameObject(String file)
    {
        active = true;

        filePath = file;

        texture = new Texture(filePath);
        sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch batch)
    {
        sprite.draw(batch);
    }

}
