package CampusTycoon.UI;

import CampusTycoon.TextInput;
import CampusTycoon.UI.Components.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;

import CampusTycoon.GameUtils;
import CampusTycoon.InputHandler;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class saveScreen implements Screen, TextField.TextFieldListener {

    //will turn true when on save screen
    public static boolean isOnSaveScreen = false;


    Stage stage;
    private SpriteBatch batch;

    /** Screen that appears when the leaderboard is selected */
    public saveScreen() {
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        Skin skin = new Skin(Gdx.files.internal("Skins/uiskin.json"));
        TextField nameField = new TextField("text",skin);
        nameField.setPosition(0,0);
        nameField.setSize(250,65);
        stage.addActor(nameField);


        isOnSaveScreen = true;
        System.out.println("Show save input");
       new TextInput();

        Button buttonMainMenu = new Button("Main Menu.png", 0, -300, 262, 66);
        buttonMainMenu.setClickAction(Component.Actions.OpenStartScreen);
        buttonMainMenu.setAnchor(Component.Anchor.Centre);
        Drawer.add(1, buttonMainMenu);
        InputHandler.add(buttonMainMenu);


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        Drawer.drawAll();

        System.out.println("draw");


        stage.act(delta);
        stage.draw();

       
    }

    @Override
    public void resize(int width, int height) {
        Window.updateResolution(width, height);
        Drawer.updateAll();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
        Drawer.clear();
        InputHandler.clear();
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }

    @Override
    public void keyTyped(TextField textField, char c) {

    }
}
