package com.mygdx.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ita49 on 23.01.2017.
 */
public class TTF_Font {

    public BitmapFont bitmapFont;
    public FreeTypeFontParameter ttf;

    public String text;
    public Vector2 position;

    public boolean active;

    public TTF_Font(int size, String filename, boolean flip)
    {
        text = "null";
        position = Vector2.Zero;
        active = true;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(filename));
        ttf = new FreeTypeFontParameter();
        ttf.flip = flip;
        ttf.size = size;

        bitmapFont = generator.generateFont(ttf); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }


}
