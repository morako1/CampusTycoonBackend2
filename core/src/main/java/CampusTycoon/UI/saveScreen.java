package CampusTycoon.UI;

import CampusTycoon.GameLogic.Timer;
import CampusTycoon.UI.Components.Button;
import CampusTycoon.UI.Components.Leaderboard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;

import CampusTycoon.InputHandler;

import java.io.IOException;


public class saveScreen implements Screen, TextField.TextFieldListener {




    public static Stage stage;
    private SpriteBatch batch;
    public static int timerd;
    public static TextField nameField;
    private Label label;
    /** Screen that appears when the leaderboard is selected */
    public saveScreen() {

        //Assessment2
        //Create new stage for UI and overwrite the multiplexer to allow
        //Input to the text box
        //stage = new Stage(new ScreenViewport());



        //Main.multiplexer.addProcessor(stage);
        // Gdx.input.setInputProcessor(Main.multiplexer);
        batch = new SpriteBatch();
        Skin skin = new Skin(Gdx.files.internal("Skins/uiskin.json"));
        nameField = new TextField("Name",skin);

        nameField.setSize(250,65);

        nameField.setPosition(Gdx.graphics.getWidth()/2f -nameField.getWidth()/2f,Gdx.graphics.getHeight()/2f);
        nameField.setMaxLength(20);








        label = new Label("Enter Name",skin);
        label.setColor(Color.BLACK);
        label.setPosition(Gdx.graphics.getWidth()/2f -label.getWidth()/2f -80f,Gdx.graphics.getHeight()/2f+70f);

        stage.addActor(nameField);
        stage.addActor(label);
        label.setVisible(false);
        nameField.setVisible(false);
        System.out.println("savescreen create");
    }

    public static String getText(){
        return nameField.getText();
    }
    @Override
    public void show() {
        label.setVisible(true);
        nameField.setVisible(true);

        System.out.println(Gdx.input.getInputProcessor()+"PROCESSOR");
        //Load leaderboard so we can edit it
        try {
            Leaderboard.loadLeaderboard();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //if stage == null create ui
    if(stage == null){


    }




        System.out.println("Show save input");


        Button buttonMainMenu = new Button("Save Score.png", 0, -300, 262, 66);
        buttonMainMenu.setClickAction(Component.Actions.OpenLeaderboardFromSave);
        buttonMainMenu.setAnchor(Component.Anchor.Centre);
        Drawer.add(1, buttonMainMenu);
        InputHandler.add(buttonMainMenu);


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        Drawer.drawAll();




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

       // Leaderboard.updateScore(nameField.get());

        label.setVisible(false);
        nameField.setVisible(false);
        Leaderboard.updateScore(nameField.getText(), Timer.score);
        nameField.setMessageText("Name");
        try {
            Leaderboard.saveLeaderboard();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
