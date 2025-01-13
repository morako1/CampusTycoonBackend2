package CampusTycoon.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import CampusTycoon.GameUtils;
import CampusTycoon.InputHandler;


public class LeaderboardScreen implements Screen {

    /**
     * Screen that appears when the leaderboard is selected
     */
    public LeaderboardScreen() {
    }

    @Override
    public void show() {

        GameUtils.leaderboardUI();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
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
}
