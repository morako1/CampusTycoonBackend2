package CampusTycoon.GameLogic;

import CampusTycoon.UI.ScreenUtils;
import CampusTycoon.UI.Components.MenuText;
import CampusTycoon.UI.Drawer;

import static CampusTycoon.GameUtils.createGameplayUI;


public class Timer{
	public static MenuText text;
    private static float timeRemaining;
    public static boolean isRunning;
    private boolean hasEnded;


    public Timer(float startTime) {
        this.timeRemaining = startTime;
        this.isRunning = false;
        this.hasEnded = false;
    }

    public void start() {
        isRunning = true;
        hasEnded = false; // Reset if the timer is restarted
    }

    public static void pause() {
        isRunning = !isRunning;

    }

    public static void resume() { isRunning = true; }

    public void reset(float startTime) {
        this.timeRemaining = startTime;
        this.isRunning = false;
        this.hasEnded = false;
    }

    public void update(float deltaTime) {
        if (isRunning && timeRemaining > 0) {
            timeRemaining -= deltaTime;
            if (timeRemaining <= 0) {
                timeRemaining = 0;
                onTimeUp(); // Call onTimeUp to handle end logic
            }

			if (text != null) {
				text.text = "Time: " + String.valueOf(timeRemaining).split("\\.")[0];
				text.update();
			}
        }
    }

    public static float getTimeRemaining() {
        return timeRemaining;
    }

    public boolean hasEnded() {
        return hasEnded;
    }

    private void onTimeUp() {
        if (!hasEnded) { // Check if already ended to avoid repeating
            hasEnded = true;
            isRunning = false; // Stop the timer completely

            Drawer.clear();
            ScreenUtils.OpenEndScreen();
			BuildingCounter.reset();
        }
    }
}
