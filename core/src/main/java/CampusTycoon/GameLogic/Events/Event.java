package CampusTycoon.GameLogic.Events;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import CampusTycoon.GameLogic.SatisfactionMeter;
import CampusTycoon.GameLogic.Timer;
import CampusTycoon.GameUtils;
import CampusTycoon.UI.Systems.EventPopup;

public abstract class Event {
	public EventPopup eventUI;
	public static int choices; // Number of choices for the event
    public static String eventText;

    public static String acceptText; // Description of the event
    public static String rejectText; // Description of the event
    // Description of the event
	public static List<String> choiceText; // Text to be displayed in the choice buttons (NOT IMPLEMENTED)
    public  String resultText;

    public int choice= 0;

    //(Assessment 2) added polymorphism, this will make it easier to create new types of events


    public Event(){

        Timer.popUp = true;
        Timer.isRunning = false;
    }

    public void UI(){

        eventUI = new EventPopup(this);
        eventUI.initialise();
    }

	public void chooseOption(int option) {
		if (option > choices) {
			System.out.print("Invalid event choice");
			return;
		}

		switch (option) {
			case 1:
                choice =1;
				Option1();
				break;
			case 2:
                choice =2;
				Option2();
				break;

			default:
				System.out.print("Invalid event choice");
				break;
		}
	}

	// Temporary choice implementations, will change to abstract functions later (as each individual event should decide what the outcome of choices are)
	public void Option1(){

        Timer.popUp = false;
        Timer.isRunning = true;

        resultText = acceptText;

        eventUI.close();
        Timer.eventResult =Timer.getTimeRemaining()-7.5f;

    };
	public void Option2() {
        Timer.popUp = false;
        Timer.isRunning = true;

        resultText = rejectText;

        eventUI.close();
        Timer.eventResult =Timer.getTimeRemaining()-7.5f;
	}

	public void End() {

		GameUtils.currentEvent = null;
	}

    public abstract  void applyEffects();
}

