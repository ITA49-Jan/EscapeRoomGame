package com.mygdx.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Vector;

/**
 * Created by memor on 23/01/2017.
 */
public class SceneManager {

    static Vector<Scene> sceneVec = new Vector<Scene>();

    public SceneManager()
    {

    }

    public static void load(Scene s)
    {
        sceneVec.clear();
        sceneVec.add(s);

        for(Scene u : sceneVec)
        {
            if(!sceneVec.isEmpty())
            u.init();
        }
    }

    public static void update()
    {
        for(Scene s : sceneVec)
        {
            if(!sceneVec.isEmpty())
            s.update();
        }
    }

    public static void draw(SpriteBatch batch)
    {
        for(Scene s : sceneVec)
        {
            if(!sceneVec.isEmpty())
            s.draw(batch);
        }
    }

    public static void resize(int width, int height)
    {
        for(Scene s : sceneVec)
        {
            if(!sceneVec.isEmpty())
                s.resize(width, height);
        }
    }

    public static void stage_draw(SpriteBatch batch)
    {
        for(Scene s : sceneVec)
        {
            if(!sceneVec.isEmpty())
            s.stage_draw(batch);
        }
    }

    public static void dispose()
    {
        for(Scene s : sceneVec)
        {
            if(!sceneVec.isEmpty())
                s.dispose();
        }
    }

}
