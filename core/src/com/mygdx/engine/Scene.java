package com.mygdx.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Game;

import java.util.Vector;

/**
 * Created by ita49 on 23.01.2017.
 */
public abstract class Scene {

    Vector<GameObject> goVec = new Vector<GameObject>();
    Vector<TTF_Font> ttfVec = new Vector<TTF_Font>();
    Vector<Canvas> stageVec = new Vector<Canvas>();

    public void add(GameObject go) {

        goVec.add(go);
    }

    public void add(TTF_Font ttfText) {
        ttfVec.add(ttfText);
    }

    public void add(Canvas s) {
        stageVec.add(s);

        Gdx.input.setInputProcessor(s);
    }

    public abstract void init();

    public abstract void update();

    public void updateCanvas()
    {
        for (Canvas s : stageVec) {
            s.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        }
    }

    public void resize(int width, int height)
    {
        for (Canvas s : stageVec) {
            s.getViewport().update(width, height, false);
        }
    }

    public void draw(SpriteBatch batch) {
        for (GameObject go : goVec) {
            if (go.active)
                go.draw(batch);
        }

        for (TTF_Font t : ttfVec) {
            if (t.active)
                t.bitmapFont.draw(batch, t.text, t.position.x, t.position.y);
        }
    }


    public void stage_draw(SpriteBatch batch) {
        for (Canvas s : stageVec) {
            s.draw();
        }
    }

    public void dispose()
    {
        for (GameObject go : goVec) {
                go.texture.dispose();
        }

        for (TTF_Font t : ttfVec) {
                t.bitmapFont.dispose();
        }

        for (Canvas s : stageVec) {
            s.dispose();
        }
    }

    public void clear() {

        goVec.clear();

        ttfVec.clear();

        //stageVec.clear();
        for (Canvas s : stageVec) {
            if(s.DontDestroy == false)
            {
                stageVec.remove(s);
            }
        }
    }

}
