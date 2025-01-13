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

public class Timer{
	public static MenuText text;
    private static float timeRemaining;
    public static boolean isRunning;
    public static boolean popUp;

    private boolean hasEnded;
    public static ArrayList<EventsEnum> eventQueue;
    public static float nextEvent;
    public static float eventResult;
    public static int score;


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
        nextEvent = 300f -(random.nextInt((61))+20);
        BuildingCounter.reset();
        SatisfactionMeter.resetSatisfactionScore();
        Money.resetMoney();
        Money.increaseMoney(2000);
        SatisfactionMeter.resetSatisfactionScore();

        isRunning = true;
        hasEnded = false; // Reset if the timer is restarted
    }

    public static void pause() {
        if (popUp) {
            return;
        }


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

            if(nextEvent>0& !eventQueue.isEmpty()){


                if(timeRemaining<=nextEvent){
                    CallEvent();
                }


            }
            else if (eventResult>0&!eventQueue.isEmpty()) {
                if(timeRemaining<=eventResult){
                    CallEventResult();
                }
            }

            if (text != null) {
				text.text = "Time: " + String.valueOf(timeRemaining).split("\\.")[0];
				text.update();
			}
        }
    }


    public void CallEvent(){
        nextEvent = -1;

        if(eventQueue.isEmpty()){

            return;
        }

        switch(eventQueue.get(0)){
            case STRIKE -> GameUtils.currentEvent= new StrikeEvent();
            case DONATE -> GameUtils.currentEvent= new DonateEvent();
            case CAT -> GameUtils.currentEvent= new CatEvent();
        }



    }

    public void CallEventResult(){
        eventResult = -1;

        if(eventQueue.isEmpty()){

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

    private void onTimeUp() {
        if (!hasEnded) { // Check if already ended to avoid repeating
            hasEnded = true;
            isRunning = false; // Stop the timer completely

            Drawer.clear();
            score = getSatisfactionScore();

            ScreenUtils.OpenEndScreen();

        }
    }

    public static int getFinalScore(){
        return score;
    }
}
