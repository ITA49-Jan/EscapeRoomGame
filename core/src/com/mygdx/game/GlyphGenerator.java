package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.Vector;

/**
 * Created by ita49 on 30.01.2017.
 */
class Glyph
{
    Texture glyph_tex;
    Image glyph;

    /*
    public Glyph(int ran)
    {
        glyph_tex = new Texture("Symbol" + ran +".png");
        glyph = new Image(glyph_tex);
    }
    */
}


public class GlyphGenerator {

    Glyph glyphObj;
    public Vector<Glyph> glyphVec = new Vector<Glyph>();

    public GlyphGenerator()
    {
        int row = 0;
        int line = 0;

        for(int i = 0; i < 10; i++)
        {
            if(line == 5) {
                line = 0;
                row = 1;
            }

            glyphObj = new Glyph();

            if(CodeGenerator.firstDigit == i + 1)
            {
                glyphObj.glyph_tex = new Texture("symbols/Symbol" + (CodeGenerator.firstDigit) +"_dot.png");
                glyphObj.glyph = new Image(glyphObj.glyph_tex);
            }
            else if(CodeGenerator.secondDigit == i + 1)
            {
                glyphObj.glyph_tex = new Texture("symbols/Symbol" + (CodeGenerator.secondDigit) +"_dot.png");
                glyphObj.glyph = new Image(glyphObj.glyph_tex);
            }
            else if(CodeGenerator.thirdDigit == i + 1)
            {
                glyphObj.glyph_tex = new Texture("symbols/Symbol" + (CodeGenerator.thirdDigit) +"_dot.png");
                glyphObj.glyph = new Image(glyphObj.glyph_tex);
            }
            else if(CodeGenerator.fourthDigit == i + 1)
            {
                glyphObj.glyph_tex = new Texture("symbols/Symbol" + (CodeGenerator.fourthDigit) +"_dot.png");
                glyphObj.glyph = new Image(glyphObj.glyph_tex);
            }
            else
            {
                glyphObj.glyph_tex = new Texture("symbols/Symbol" + (i + 1) +".png");
                glyphObj.glyph = new Image(glyphObj.glyph_tex);
            }

            glyphObj.glyph.setSize(32, 32);
            glyphObj.glyph.setPosition(350 + (128 * row), 490 - (line * 50));

            glyphVec.add(glyphObj);

            line++;
        }

    }

}
