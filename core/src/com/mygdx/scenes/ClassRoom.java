package com.mygdx.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.engine.GameObject;
import com.mygdx.engine.Scene;
import com.mygdx.engine.SceneManager;
import com.mygdx.engine.TTF_Font;
import com.mygdx.game.*;
import com.mygdx.items.*;


/**
 * Created by memor on 23/01/2017.
 */
public class ClassRoom extends Scene {

    Music music;

    // GameObject inventary_tex;
    GameObject credits_tex;
    private GameObject room_front;
    private GameObject room_left;
    private GameObject room_right;
    private GameObject room_back;

    TTF_Font ui_font;

    boolean click_switch = false;

    int x = -400;

    PostIt postIt_item;
    Mac mac_item;
    Book book_item;
    Door door_item;

    boolean book_used = false;
    int c = 0;

    @Override
    public void init() {

        postIt_item = new PostIt();
        mac_item = new Mac();
        book_item = new Book();
        door_item = new Door();

        room_front = new GameObject("room1.png");
        room_left = new GameObject("room4.png");
        room_right = new GameObject("room2.png");
        room_back = new GameObject("room3.png");

        Inventory.show();

        //  inventary_tex = new GameObject("hud.png");
        credits_tex = new GameObject("credits.png");

        music = Gdx.audio.newMusic(Gdx.files.internal("DST-RoboticDreams.ogg"));
        //music.play();

        // UI
        ui_font = new TTF_Font(35, "DS-DIGI.TTF", false);

        //this.add(inventary_tex);
        this.add(Game.canvas);

        Timer.showDisplay();

        RoomNavigation.show();

        //items
        postIt_item.key_btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("key");

                Inventory.add_postit();
                postIt_item.key_btn.remove();

                // SceneManager.load(new Computer());
                // RoomNavigation.remove();
            }
        });

        mac_item.key_btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("key");

                mac_item.key_btn.remove();

                postIt_item.key_btn.remove();

                SceneManager.load(new Computer());
                RoomNavigation.remove();
            }
        });

        book_item.key_btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("key");

                book_item.key_btn.remove();
                book_used = true;
                Inventory.add_paper();
            }
        });

        door_item.key_btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("key");

                if (Inventory.usbstick_added) {
                    Game.canvas.clear();
                    SceneManager.load(new Credits());
                }
                else
                {
                    Inventory.MsgText("Du wolltest doch den Test klauen ?!");
                }
            }
        });


        //push everything to scene stack
        this.add(room_front);
        this.add(room_left);
        this.add(room_right);
        this.add(room_back);
    }

    @Override
    public void update() {
        //Game.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        RoomNavigation.update();

        if (RoomNavigation.currentRoomside == RoomNavigation.roomside.front) {
            //System.out.println("front");
            room_front.active = true;
            room_left.active = false;
            room_right.active = false;
            room_back.active = false;

            if (c != 0)
                c = 0;

            postIt_item.remove();
            mac_item.remove();
            book_item.remove();
            door_item.remove();
        }

        if (RoomNavigation.currentRoomside == RoomNavigation.roomside.left) {
            //System.out.println("front");
            room_front.active = false;
            room_left.active = true;
            room_right.active = false;
            room_back.active = false;

            if (c != 0)
                c = 0;

            postIt_item.remove();
            mac_item.remove();
            door_item.remove();

            if (c++ == 0) {

                if (!book_used)
                    book_item.add();
            }

        }

        if (RoomNavigation.currentRoomside == RoomNavigation.roomside.right) {
            //System.out.println("baba" + c);
            room_front.active = false;
            room_left.active = false;
            room_right.active = true;
            room_back.active = false;

            if (c != 0)
                c = 0;

            if (c++ == 0) {

                mac_item.add();

                if (!Inventory.postit_added) {
                    postIt_item.add();
                }

            }

            book_item.remove();
            door_item.remove();
        }

        if (RoomNavigation.currentRoomside == RoomNavigation.roomside.back) {
            //System.out.println("front");
            room_front.active = false;
            room_left.active = false;
            room_right.active = false;
            room_back.active = true;

            if (c != 0)
                c = 0;

            postIt_item.remove();
            mac_item.remove();
            book_item.remove();

            if (c++ == 0) {

                door_item.add();

            }
        }

    }

    @Override
    public void resize(int width, int height) {
        //  Game.stage.getViewport().update(width, height, false);
    }

}
