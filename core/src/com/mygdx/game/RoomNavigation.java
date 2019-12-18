package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.engine.GameObject;
import com.mygdx.engine.Scene;
import com.mygdx.engine.SceneManager;

/**
 * Created by ita49 on 26.01.2017.
 */
public class RoomNavigation {

    public static int nextRoomside = 0;

    public static enum roomside {
        front,
        right,
        back,
        left
    }

    public static roomside currentRoomside;

    private static ImageButton nav_left;
    private static ImageButton nav_right;
    public static ImageButton nav_down;

    private static Skin navskin;

    public RoomNavigation() {

    }

    public static void init()
    {
        currentRoomside = roomside.front;

        //room navigation
        TextureAtlas navAtlas = new TextureAtlas(Gdx.files.internal("buttons.atlas"));
        navskin = new Skin();
        navskin.addRegions(navAtlas);

        ImageButton.ImageButtonStyle nav_left_style = new ImageButton.ImageButtonStyle();
        nav_left_style.up = navskin.getDrawable("forward");

        nav_left = new ImageButton(nav_left_style);
        nav_left.setPosition(800 - 50, 600 / 2 - 50 / 2);

        nav_left.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("right");
                nextRoomside++;
            }
        });

        ImageButton.ImageButtonStyle nav_right_style = new ImageButton.ImageButtonStyle();
        nav_right_style.up = navskin.getDrawable("backward");

        nav_right = new ImageButton(nav_right_style);
        nav_right.setPosition(0, 600 / 2 - 50 / 2);

        nav_right.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("left");
                nextRoomside--;
            }
        });
    }

    public static void initBack()
    {
        ImageButton.ImageButtonStyle nav_right_style = new ImageButton.ImageButtonStyle();
        nav_right_style.up = navskin.getDrawable("down");

        nav_down = new ImageButton(nav_right_style);
        nav_down.setPosition(800 / 2 - 50 / 2, 0);

        Game.canvas.addActor(nav_down);
    }

    public static void show()
    {
        Game.canvas.addActor(nav_left);
        Game.canvas.addActor(nav_right);
    }

    public static void reset()
    {
        nextRoomside = 0;

        currentRoomside = roomside.front;
    }

    public static void update()
    {
        if(nextRoomside > 3)
        {
            nextRoomside = 0;
        }
        else if (nextRoomside < 0)
        {
            nextRoomside = 3;
        }

        currentRoomside = RoomNavigation.roomside.values()[nextRoomside];

        //System.out.println(currentRoomside);
    }

    public static void remove()
    {
        nav_left.remove();
        nav_right.remove();
    }

    public static void removeBack()
    {
        nav_down.remove();
    }

}
