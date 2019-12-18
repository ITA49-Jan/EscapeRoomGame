package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.engine.TTF_Font;

import java.util.concurrent.TimeUnit;

/**
 * Created by ita49 on 23.01.2017.
 */
public class Timer {

    public static double offset;

    private static double time;
    private static String timeStr;
    private static double resetOffset;

    private static Label timer_label;

    public Timer()
    {

    }

    public static double update()
    {
        time = ((System.currentTimeMillis() - offset) / 1000) - resetOffset;
        return time;
    }

    public static String updateAsHours()
    {
        double s = time % 60;
        double m = (time / 60) % 60;
        double h = (time / (60 * 60)) % 24;
        timeStr = String.format("%02d:%02d:%02d", (int)h, (int)m, (int)s);
        return timeStr;
    }

    public static void start()
    {
        offset = System.currentTimeMillis();
    }

    public static void createDisplay()
    {
        TTF_Font ui_font = new TTF_Font(35, "DS-DIGI.TTF", false);
        Label.LabelStyle timer_style = new Label.LabelStyle();
        timer_style.font = ui_font.bitmapFont;
        timer_label = new Label("Time:", timer_style);
        timer_label.setPosition(10, 600 - 50);
        timer_label.setColor(1f, 0f, 0f, 1f);
    }

    public static void showDisplay()
    {
        Game.canvas.addActor(timer_label);
    }

    public static void hideDisplay()
    {
        timer_label.remove();
    }

    public static void updateDisplay()
    {
        timer_label.setText("Time: " + Timer.updateAsHours());
    }

    public static void reset()
    {
        resetOffset = time;
    }

}
