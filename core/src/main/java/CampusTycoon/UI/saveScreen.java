package CampusTycoon.UI;

import CampusTycoon.UI.Components.Button;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;

import CampusTycoon.GameUtils;
import CampusTycoon.InputHandler;


public class saveScreen implements Screen, TextField.TextFieldListener {

    /** Screen that appears when the leaderboard is selected */
    public saveScreen() {
    }

    @Override
    public void show() {
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
