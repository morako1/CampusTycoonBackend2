package CampusTycoon.GameLogic;

import CampusTycoon.GameLogic.Events.CatEvent;
import CampusTycoon.GameLogic.Events.EventsEnum;
import CampusTycoon.GameLogic.Events.DonateEvent;
import CampusTycoon.GameLogic.Events.StrikeEvent;
import CampusTycoon.GameUtils;
import CampusTycoon.UI.ScreenUtils;
import CampusTycoon.UI.Components.MenuText;
import CampusTycoon.UI.Drawer;
import com.badlogic.gdx.Gdx;


import java.util.*;

import static CampusTycoon.GameLogic.SatisfactionMeter.getSatisfactionScore;
import static CampusTycoon.GameLogic.SatisfactionMeter.resetSatisfactionScore;

public class Timer {
    public static MenuText text;
    private static float timeRemaining;
    public static boolean isRunning;
    public static boolean popUp;

    private boolean hasEnded;
    public static ArrayList<EventsEnum> eventQueue;
    public static float nextEvent;
    public static float eventResult;
    public static int score;
    public static MenuText pauseText;

<<<<<<< HEAD
=======

>>>>>>> parent of 65aab95 (Headless Testing Implemented)
    /**
     * Assessment 2
     * Constructs a new Timer object.
     * Initializes the timer with the provided starting time, prepares the event queue
     * by randomizing the enumerated events from the EventsEnum, and sets the initial
     * states for the timer.
     *
     * @param startTime The initial time in seconds for the countdown timer.
     */
    public Timer(float startTime) {


        eventQueue = new ArrayList<EventsEnum>();

        eventQueue.addAll(Arrays.asList(EventsEnum.values()));

        Collections.shuffle(eventQueue);

        this.timeRemaining = startTime;
        this.isRunning = false;
        this.hasEnded = false;
    }

    public void start() {

        Random random = new Random();
        nextEvent = 300f - (random.nextInt((61)) + 20);
        BuildingCounter.reset();
        SatisfactionMeter.resetSatisfactionScore();
        Money.resetMoney();
        Money.increaseMoney(2000);
        SatisfactionMeter.resetSatisfactionScore();

        isRunning = false;
        hasEnded = false; // Reset if the timer is restarted


    }

    public static void pause() {
        if (popUp) {
            return;
        }


        isRunning = !isRunning;

    }

    public static void resume() {
        isRunning = true;
    }

    /**
     * Assessmet 2
     * Sets the timer to the time specified
     *
     * @param startTime the time to set the timer to
     */
    public void reset(float startTime) {
        this.timeRemaining = startTime;
        this.isRunning = false;
        this.hasEnded = false;
    }

    public void update(float deltaTime) {

        if (isRunning && timeRemaining > 0) {

            //Assessment 2
            //When the game is not paused remove the pause text
            pauseText.text= "";
            timeRemaining -= deltaTime;
            if (timeRemaining <= 0) {
                timeRemaining = 0;
                onTimeUp(); // Call onTimeUp to handle end logic
            }

            if (nextEvent > 0 & !eventQueue.isEmpty()) {


                if (timeRemaining <= nextEvent) {
                    CallEvent();
                }


            } else if (eventResult > 0 & !eventQueue.isEmpty()) {
                if (timeRemaining <= eventResult) {
                    CallEventResult();
                }
            }

            if (text != null) {
                text.text = "Time: " + String.valueOf(timeRemaining).split("\\.")[0];
                text.update();
            }
<<<<<<< HEAD
        }
        else{
            //Assessment 2
            //When the game is paused add the pause text
            pauseText.text= "Paused";
=======
>>>>>>> parent of 65aab95 (Headless Testing Implemented)
        }
    }


    /**
     * Assessment2
     * Processes the next event in the event queue and
<<<<<<< HEAD
=======
     * <p>
>>>>>>> parent of 65aab95 (Headless Testing Implemented)
     * If the event queue is empty, the method returns
     * - STRIKE: Creates and assigns a new instance of StrikeEvent to the current event.
     * - DONATE: Creates and assigns a new instance of DonateEvent to the current event.
     * - CAT: Creates and assigns a new instance of CatEvent to the current event.
     */
    public void CallEvent() {
        nextEvent = -1;

        if (eventQueue.isEmpty()) {

            return;
        }

        switch (eventQueue.get(0)) {
            case STRIKE -> GameUtils.currentEvent = new StrikeEvent();
            case DONATE -> GameUtils.currentEvent = new DonateEvent();
            case CAT -> GameUtils.currentEvent = new CatEvent();
        }


    }

    /**
     * Assessment2
     * Displays the results of the event after some seconds
     */
    public void CallEventResult() {
        eventResult = -1;

        if (eventQueue.isEmpty()) {

            return;
        }

        GameUtils.EventResultPopup();


    }

    public static float getTimeRemaining() {
        return timeRemaining;
    }

    public boolean hasEnded() {
        return hasEnded;
    }

    /**
     * Assessment 2
     * Called when time is up
     */
    private void onTimeUp() {
        if (!hasEnded) { // Check if already ended to avoid repeating
            hasEnded = true;
            isRunning = false; // Stop the timer completely

            Drawer.clear();
            score = getSatisfactionScore();

            ScreenUtils.OpenEndScreen();

        }
    }

    public static int getFinalScore() {
        return score;
    }
}
