package com.mygdx.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.engine.GameObject;
import com.mygdx.engine.Scene;
import com.mygdx.engine.SceneManager;
import com.mygdx.engine.TTF_Font;
import com.mygdx.game.*;

/**
 * Created by ita49 on 25.01.2017.
 */
public class Computer extends Scene {

    TextField txtUsername;
    TextField txtPassword;

    //TTF_Font ui_font;
    GameObject mac;

    String Username;
    String Password;

    ImageButton login_btn;

    boolean usernameWrong = false;
    boolean passwordWrong = false;
    boolean loggedin = false;
    boolean firstUse = false;

    Label error_label;

    void CheckUsername() {
        if (Username.equals("jajoka16")) {
            System.out.println("Whoop!");
            usernameWrong = false;
            firstUse = true;
        }
        else
        {
            usernameWrong = true;
            firstUse = true;
        }
    }

    void CheckPassword() {
        if (Password.equals(CodeGenerator.computerPassword)) {
            System.out.println("Whoop!");
            passwordWrong = false;
            firstUse = true;
        }
        else
        {
            passwordWrong = true;
            firstUse = true;
        }
    }

    @Override
    public void init() {

        mac = new GameObject("MacScreen.png");

        TTF_Font ui_font = new TTF_Font(18, "Ubuntu-R.ttf", false);
        TTF_Font login_font = new TTF_Font(12, "Ubuntu-R.ttf", false);

        TextField.TextFieldStyle txtStyle = new TextField.TextFieldStyle();
        txtStyle.fontColor = Color.BLACK;
        txtStyle.font = ui_font.bitmapFont;

        txtUsername = new TextField("", txtStyle);
        txtUsername.setPosition(280, 295);
        txtUsername.setText("");
        txtUsername.setWidth(235);

        txtUsername.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char key) {
                //next textfield
                if (Gdx.input.isKeyPressed(Keys.ENTER))
                    Game.canvas.setKeyboardFocus(txtPassword);
            }
        });

        txtPassword = new TextField("", txtStyle);
        txtPassword.setPosition(280, 240);
        txtPassword.setText("");
        txtPassword.setWidth(235);
        txtPassword.setPasswordCharacter('*');
        txtPassword.setPasswordMode(true);

        txtPassword.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char key) {
                if (Gdx.input.isKeyPressed(Keys.ENTER)) {
                    CheckUsername();
                    CheckPassword();
                }
            }
        });

        Game.canvas.addActor(txtPassword);
        Game.canvas.addActor(txtUsername);

        TextureAtlas keyAtlas = new TextureAtlas(Gdx.files.internal("buttons.atlas"));
        Skin itemskin = new Skin();
        itemskin.addRegions(keyAtlas);
        ImageButton.ImageButtonStyle imgStyle = new ImageButton.ImageButtonStyle();
        imgStyle.up = itemskin.getDrawable("login_normal");
        imgStyle.down = itemskin.getDrawable("login_down");

        login_btn = new ImageButton(imgStyle);
        login_btn.setPosition(450, 180);

        Game.canvas.addActor(login_btn);

        login_btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                CheckUsername();
                CheckPassword();
            }
        });

        Label.LabelStyle timer_style = new Label.LabelStyle();
        timer_style.font = login_font.bitmapFont;
        error_label = new Label("Benutzername/Passwort ungültig!", timer_style);
        error_label.setPosition(275, 220);
        error_label.setColor(1f, 0f, 0f, 1f);

        RoomNavigation.initBack();

        RoomNavigation.nav_down.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("down");
                SceneManager.load(new ClassRoom());
                RoomNavigation.removeBack();

                error_label.remove();
                login_btn.remove();
                txtUsername.remove();
                txtPassword.remove();
            }
        });

        this.add(mac);
        this.add(ui_font);
        this.add(Game.canvas);
    }

    @Override
    public void update() {

        Username = txtUsername.getText();
        Password = txtPassword.getText();
        // System.out.println("PW:" + txtUsernamePW);

        if(usernameWrong || passwordWrong)
        {
            Game.canvas.addActor(error_label);
        }

        if(!usernameWrong && !passwordWrong && firstUse)
        {
            loggedin = true;

            error_label.remove();
            login_btn.remove();
            txtUsername.remove();
            txtPassword.remove();

            RoomNavigation.removeBack();

            SceneManager.load(new ComputerDesktop());
           // System.out.println("Cracked !");
        }

        if(loggedin)
        {
            error_label.remove();
        }
    }

    @Override
    public void resize(int width, int height) {

    }
}
