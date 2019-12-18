package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.engine.TTF_Font;

/**
 * Created by ita49 on 24.01.2017.
 */
public class Inventory {

    public enum items {
        key, paper
    }

    public static items _items;

    static ImageButton postit_btn;
    static ImageButton paper_btn;
    static ImageButton usbstick_btn;
    static ImageButton inventory_btn;

    //private static Texture hud_tex;
    //static Image hud;

    private static boolean init;
    public static boolean postit_added;
    public static boolean paper_added;
    public static boolean usbstick_added;

    static Texture dim_tex;
    static Image dim;
    static ImageButton nav_down;

    static Vector2 hud_start_pos = new Vector2(590, -120);
    static Vector2 hud_end_pos = new Vector2(590, 5);
    static Vector2 hud_cur_pos;

    static boolean hud_tween_play;
    static boolean hud_tween_revert;
    static float hud_tween = 0f;

    static Texture hud_tex;
    static Image hud_img;

    //zoomed items
    static Texture postit_zoom_tex;
    static Image postit_zoom;

    static Texture paper_zoom_tex;
    static Image paper_zoom;

    // text
    static Label text_label;
    static float counter;
    static boolean startCounter = false;

    static float c = 0;

    static GlyphGenerator glyphGenerator;

    //tweens
    static Vector2 postit_start_pos = new Vector2(100, 325);
    static Vector2 postit_end_pos = new Vector2(600, 70);
    static Vector2 postit_get_pos;
    static float postit_tween = 0f;
    static boolean postit_tween_play;

    static Vector2 paper_start_pos = new Vector2(520, 220);
    static Vector2 paper_end_pos = new Vector2(670, 70);
    static Vector2 paper_get_pos;
    static float paper_tween = 0f;
    static boolean paper_tween_play;

    static Vector2 usbstick_start_pos = new Vector2(450, 180);
    static Vector2 usbstick_end_pos = new Vector2(625, 10);
    static Vector2 usbstick_get_pos;
    static float usbstick_tween = 0f;
    static boolean usbstick_tween_play;

    public Inventory() {

    }

    public static void init() {
        if (!init) {
            init = true;

            System.out.println("Inventroy");

            /*
            hud_tex = new Texture("hud.png");
            hud = new Image(hud_tex);
            hud.setPosition(hud_pos.x, hud_pos.y);
            */

            postit_get_pos = postit_end_pos = new Vector2(600, 70 - Math.abs(hud_start_pos.y));
            paper_get_pos = paper_end_pos = new Vector2(670, 70 - Math.abs(hud_start_pos.y));
            usbstick_get_pos = usbstick_end_pos = new Vector2(625, 10 - Math.abs(hud_start_pos.y));

            hud_tex = new Texture("hud_clip.png");
            hud_img = new Image(hud_tex);
            hud_cur_pos = new Vector2(hud_start_pos.x, hud_start_pos.y);
            hud_img.setPosition(hud_cur_pos.x, hud_cur_pos.y);

            hud_img.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("inventory clicked");
                    hud_tween_play = true;
                }
            });

            //zoomed items
            postit_zoom_tex = new Texture("postit_zoom.png");
            postit_zoom = new Image(postit_zoom_tex);

            paper_zoom_tex = new Texture("blatt_zoom.png");
            paper_zoom = new Image(paper_zoom_tex);

            glyphGenerator = new GlyphGenerator();

            // achieved text stuff
            TTF_Font ui_font = new TTF_Font(25, "BedandBreakfast-Regular.TTF", false);
            Label.LabelStyle timer_style = new Label.LabelStyle();
            timer_style.font = ui_font.bitmapFont;
            text_label = new Label("", timer_style);

            //exit button
            dim_tex = new Texture("dim.png");
            dim = new Image(dim_tex);

            TextureAtlas navAtlas = new TextureAtlas(Gdx.files.internal("buttons.atlas"));
            Skin navskin = new Skin();
            navskin.addRegions(navAtlas);

            ImageButton.ImageButtonStyle nav_right_style = new ImageButton.ImageButtonStyle();
            nav_right_style.up = navskin.getDrawable("down");

            nav_down = new ImageButton(nav_right_style);
            nav_down.setPosition(800 / 2 - 50 / 2, 0);

            nav_down.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("moo");
                    nav_down.remove();
                    dim.remove();
                    removeZoom();
                }
            });
        }
    }

    public static void add_postit() {

        TextureAtlas keyAtlas = new TextureAtlas(Gdx.files.internal("items.atlas"));
        Skin itemskin = new Skin();
        itemskin.addRegions(keyAtlas);
        ImageButton.ImageButtonStyle imgStyle = new ImageButton.ImageButtonStyle();
        imgStyle.up = itemskin.getDrawable("postit_icon");

        postit_btn = new ImageButton(imgStyle);

        Game.canvas.addActor(postit_btn);

        MsgText("Notizblatt erhalten !");

        postit_btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("moo");
                _items = items.key;
                zoom();
            }
        });

/*
        postit_btn.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                postit_btn.moveBy(x - postit_btn.getWidth() / 2, y - postit_btn.getHeight() / 2);
            }

            public void dragStop(InputEvent event, float x, float y, int pointer) {
                postit_btn.setPosition(postit_get_pos.x, postit_get_pos.y);
            }
        });
*/
        postit_added = true;
        postit_tween_play = true;
    }

    public static void add_paper() {

        TextureAtlas keyAtlas = new TextureAtlas(Gdx.files.internal("items.atlas"));
        Skin itemskin = new Skin();
        itemskin.addRegions(keyAtlas);
        ImageButton.ImageButtonStyle imgStyle = new ImageButton.ImageButtonStyle();
        imgStyle.up = itemskin.getDrawable("paper_icon");

        paper_btn = new ImageButton(imgStyle);

        Game.canvas.addActor(paper_btn);

        MsgText("Papier mit Symbolen erhalten !");

        paper_btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("moo");
                _items = items.paper;
                zoom();
            }
        });

/*
        paper_btn.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                paper_btn.moveBy(x - paper_btn.getWidth() / 2, y - paper_btn.getHeight() / 2);
            }

            public void dragStop(InputEvent event, float x, float y, int pointer) {
                paper_btn.setPosition(paper_get_pos.x, paper_get_pos.y);
            }
        });
*/
        paper_added = true;
        paper_tween_play = true;
    }

    public static void add_usbstick() {

        TextureAtlas keyAtlas = new TextureAtlas(Gdx.files.internal("items.atlas"));
        Skin itemskin = new Skin();
        itemskin.addRegions(keyAtlas);
        ImageButton.ImageButtonStyle imgStyle = new ImageButton.ImageButtonStyle();
        imgStyle.up = itemskin.getDrawable("usb_icon");

        usbstick_btn = new ImageButton(imgStyle);

        Game.canvas.addActor(usbstick_btn);

        MsgText("USB Stick erhalten !");

        usbstick_btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("moo");
            }
        });

/*
        usbstick_btn.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                usbstick_btn.moveBy(x - usbstick_btn.getWidth() / 2, y - usbstick_btn.getHeight() / 2);
            }

            public void dragStop(InputEvent event, float x, float y, int pointer) {
                usbstick_btn.setPosition(usbstick_get_pos.x, usbstick_get_pos.y);
            }
        });
*/

        usbstick_added = true;
        usbstick_tween_play = true;
    }

    public static void show() {
        Game.canvas.addActor(hud_img);
        //Game.canvas.addActor(hud);
    }

    static void zoom() {
        Game.canvas.addActor(dim);

        if (_items == items.key) {
            Game.canvas.addActor(postit_zoom);
            //postit_zoom.toBack();
        }


        if (_items == items.paper) {
            Game.canvas.addActor(paper_zoom);
            //postit_zoom.toBack();

            for (Glyph e : glyphGenerator.glyphVec) {
                Game.canvas.addActor(e.glyph);
            }
        }
/*
        if(_items == items.usb_stick)
        {
            Game.canvas.addActor(usb_stick);
            //postit_zoom.toBack();
        }
*/

        Game.canvas.addActor(nav_down);
        nav_down.toFront();
    }

    static void removeZoom() {
        if (_items == items.key) {
            postit_zoom.remove();
        }

        if (_items == items.paper) {
            paper_zoom.remove();

            for (Glyph e : glyphGenerator.glyphVec) {
                e.glyph.remove();
            }
        }
/*
        if(_items == items.usb_stick)
        {
            usb_stick.remove();
        }
*/
    }

    /*
    void draw(SpriteBatch batch) {
        //stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        //stage.draw();
    }

    void resize(int width, int height) {
        //stage.getViewport().update(width, height, false);
    }
   */

    public static void MsgText(String text) {
        text_label.setText(text);
        text_label.setPosition(275, 600 - 25);
        text_label.setColor(0f, 0f, 1f, 1f);

        //reset just in case
        startCounter = false;
        counter = 0f;
        c = 0;

        startCounter = true;

        Game.canvas.addActor(text_label);
        text_label.toFront();
    }

    static void register_postit_drag(float _x, float _y)
    {

        final float x_ = _x;
        final float y_ = _y;

        postit_btn.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                postit_btn.moveBy(x - postit_btn.getWidth() / 2, y - postit_btn.getHeight() / 2);
            }

            public void dragStop(InputEvent event, float x, float y, int pointer) {
                postit_btn.setPosition(x_, y_);
            }
        });
    }

    public static void update() {
        if (startCounter) {
            counter += 1f * Gdx.graphics.getDeltaTime();

            //System.out.println(c);

            if (counter < 1) {
                c += 1f * Gdx.graphics.getDeltaTime();
                text_label.setColor(0f, 0f, 1f, Math.min(1f, c));
            }

            if (counter > 3f) {
                c -= 1f * Gdx.graphics.getDeltaTime();
                text_label.setColor(0f, 0f, 1f, Math.min(1f, c));
            }

            if (counter > 5f) {
                text_label.remove();
                startCounter = false;
                counter = 0f;
                c = 0;
            }
        }

        //tweens

        if (hud_tween_play) {
            float anim = hud_tween += 1 * Gdx.graphics.getDeltaTime();

            if (!hud_tween_revert) {

                hud_cur_pos = new Vector2(MathUtils.lerp(hud_start_pos.x, hud_end_pos.x, MathUtils.clamp(anim, 0f, 1f)),
                        MathUtils.lerp(hud_start_pos.y, hud_end_pos.y, MathUtils.clamp(anim, 0f, 1f)));

                hud_img.setPosition(hud_cur_pos.x, hud_cur_pos.y);

                postit_get_pos = new Vector2(600, 70 - Math.abs(hud_start_pos.y));
                paper_get_pos = new Vector2(670, 70 - Math.abs(hud_start_pos.y));
                usbstick_get_pos = new Vector2(625, 10 - Math.abs(hud_start_pos.y));

                if (!postit_added)
                    postit_end_pos = new Vector2(600, 70);


                if (!paper_added)
                    paper_end_pos = new Vector2(670, 70);

                if (!usbstick_added)
                    usbstick_end_pos = new Vector2(625, 10);

            } else {
                hud_cur_pos = new Vector2(MathUtils.lerp(hud_end_pos.x, hud_start_pos.x, MathUtils.clamp(anim, 0f, 1f)),
                        MathUtils.lerp(hud_end_pos.y, hud_start_pos.y, MathUtils.clamp(anim, 0f, 1f)));

                hud_img.setPosition(hud_cur_pos.x, hud_cur_pos.y);

                /*
                postit_get_pos = new Vector2(600, 70 - Math.abs(hud_start_pos.y));
                paper_get_pos = new Vector2(670, 70 - Math.abs(hud_start_pos.y));
                usbstick_get_pos = new Vector2(625, 10 - Math.abs(hud_start_pos.y));
                */

                postit_get_pos = new Vector2(600, 70);
                paper_get_pos = new Vector2(670, 70);
                usbstick_get_pos = new Vector2(625, 10);

                if (!postit_added)
                    postit_end_pos = new Vector2(600, 70 - Math.abs(hud_start_pos.y));

                if (!paper_added)
                    paper_end_pos = new Vector2(670, 70 - Math.abs(hud_start_pos.y));

                if (!usbstick_added)
                    usbstick_end_pos = new Vector2(625, 10 - Math.abs(hud_start_pos.y));
            }

            if (postit_added && hud_tween_revert) {

                //register_postit_drag(postit_get_pos.x, postit_get_pos.y - Math.abs(hud_start_pos.y));

                float cur_pos = postit_get_pos.y;
                float to_pos = postit_get_pos.y - Math.abs(hud_start_pos.y);
                postit_btn.setPosition(postit_get_pos.x, MathUtils.lerp(cur_pos,
                        to_pos, MathUtils.clamp(anim, 0f, 1f)));
            } else if (postit_added && !hud_tween_revert) {

                //register_postit_drag(postit_get_pos.x, postit_get_pos.y + Math.abs(hud_start_pos.y));

                float cur_pos = postit_get_pos.y;
                float to_pos = postit_get_pos.y + Math.abs(hud_start_pos.y);
                postit_btn.setPosition(postit_get_pos.x, MathUtils.lerp(cur_pos,
                        to_pos, MathUtils.clamp(anim, 0f, 1f)));
            }

            if (paper_added && hud_tween_revert) {
                float cur_pos = paper_get_pos.y;
                float to_pos = paper_get_pos.y - Math.abs(hud_start_pos.y);
                paper_btn.setPosition(paper_get_pos.x, MathUtils.lerp(cur_pos,
                        to_pos, MathUtils.clamp(anim, 0f, 1f)));
            } else if (paper_added && !hud_tween_revert) {
                float cur_pos = paper_get_pos.y;
                float to_pos = paper_get_pos.y + Math.abs(hud_start_pos.y);
                paper_btn.setPosition(paper_get_pos.x, MathUtils.lerp(cur_pos,
                        to_pos, MathUtils.clamp(anim, 0f, 1f)));
            }

            if (usbstick_added && hud_tween_revert) {
                float cur_pos = usbstick_get_pos.y;
                float to_pos = usbstick_get_pos.y - Math.abs(hud_start_pos.y);
                usbstick_btn.setPosition(usbstick_get_pos.x, MathUtils.lerp(cur_pos,
                        to_pos, MathUtils.clamp(anim, 0f, 1f)));
            } else if (usbstick_added && !hud_tween_revert) {
                float cur_pos = usbstick_get_pos.y;
                float to_pos = usbstick_get_pos.y + Math.abs(hud_start_pos.y);
                usbstick_btn.setPosition(usbstick_get_pos.x, MathUtils.lerp(cur_pos,
                        to_pos, MathUtils.clamp(anim, 0f, 1f)));
            }

            if (MathUtils.lerp(hud_end_pos.x, hud_start_pos.x, MathUtils.clamp(anim, 0f, 1f)) <= hud_start_pos.x &&
                    MathUtils.lerp(hud_end_pos.y, hud_start_pos.y, MathUtils.clamp(anim, 0f, 1f)) <= hud_start_pos.y) {
                hud_tween_play = false;
                hud_tween_revert = !hud_tween_revert;
                hud_tween = 0f;
            }
        }

        if (postit_added) {

            if (postit_tween_play) {
                float anim = postit_tween += 1 * Gdx.graphics.getDeltaTime();
                postit_btn.setPosition(MathUtils.lerp(postit_start_pos.x, postit_end_pos.x, MathUtils.clamp(anim, 0f, 1f)),
                        MathUtils.lerp(postit_start_pos.y, postit_end_pos.y, MathUtils.clamp(anim, 0f, 1f)));

                if (MathUtils.lerp(postit_start_pos.x, postit_end_pos.x, MathUtils.clamp(anim, 0f, 1f)) <= postit_end_pos.x &&
                        MathUtils.lerp(postit_start_pos.y, postit_end_pos.y, MathUtils.clamp(anim, 0f, 1f)) <= postit_end_pos.y) {
                    postit_tween_play = false;
                }
            }
        }

        if (paper_added) {

            if (paper_tween_play) {
                float anim = paper_tween += 1 * Gdx.graphics.getDeltaTime();
                paper_btn.setPosition(MathUtils.lerp(paper_start_pos.x, paper_end_pos.x, MathUtils.clamp(anim, 0f, 1f)),
                        MathUtils.lerp(paper_start_pos.y, paper_end_pos.y, MathUtils.clamp(anim, 0f, 1f)));

                if (MathUtils.lerp(paper_start_pos.x, paper_end_pos.x, MathUtils.clamp(anim, 0f, 1f)) <= paper_end_pos.x &&
                        MathUtils.lerp(paper_start_pos.y, paper_end_pos.y, MathUtils.clamp(anim, 0f, 1f)) <= paper_end_pos.y) {
                    paper_tween_play = false;
                }
            }
        }

        if (usbstick_added) {

            if (usbstick_tween_play) {
                float anim = usbstick_tween += 1 * Gdx.graphics.getDeltaTime();
                usbstick_btn.setPosition(MathUtils.lerp(usbstick_start_pos.x, usbstick_end_pos.x, MathUtils.clamp(anim, 0f, 1f)),
                        MathUtils.lerp(usbstick_start_pos.y, usbstick_end_pos.y, MathUtils.clamp(anim, 0f, 1f)));

                if (MathUtils.lerp(usbstick_start_pos.x, usbstick_end_pos.x, MathUtils.clamp(anim, 0f, 1f)) <= usbstick_end_pos.x &&
                        MathUtils.lerp(usbstick_start_pos.y, usbstick_end_pos.y, MathUtils.clamp(anim, 0f, 1f)) <= usbstick_end_pos.y) {
                    usbstick_tween_play = false;
                }
            }
        }
    }
}
