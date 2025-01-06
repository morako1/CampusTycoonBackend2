package CampusTycoon.UI;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import CampusTycoon.GameUtils;
import CampusTycoon.GameLogic.Timer;

public class GameplayScreen implements Screen{
    private Timer timer;
    private boolean stateChanged;

    @Override
    public void show() {
        timer = new Timer(300f); // Sets a countdown for 300 seconds (5 minutes)
        timer.start();
        GameUtils.startGame();
        GameUtils.createGameplayUI();
        stateChanged = false;  // Reset stateChanged when the screen is shown
    }

    @Override
    public void render(float delta) {
        timer.update(delta); // Update the timer every frame


        // Check if the timer has ended and stateChanged is false
        if (timer.hasEnded() && !stateChanged) {
            stateChanged = true; // Set the flag to true to prevent re-execution
        }

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
        }

        @Override
        public void dispose() {
            // Destroy screen's assets here.
        }
    }
