package CampusTycoon.GameLogic;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import CampusTycoon.GameUtils;
import CampusTycoon.UI.Systems.EventPopup;

public class Event {



	//


	public EventPopup eventUI;
	public static int choices= 3; // Number of choices for the event
	public static String eventText= "Test event"; // Description of the event
	public static List<String> choiceText= new ArrayList<String>(Arrays.asList(
			"Accept", "Neutral", "Reject"));; // Text to be displayed in the choice buttons (NOT IMPLEMENTED)

	public Event() {
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
				Option1();
				break;
			case 2:
				Option2();
				break;
			case 3:
				Option3();
				break;
			default:
				System.out.print("Invalid event choice");
				break;
		}
	}

	// Temporary choice implementations, will change to abstract functions later (as each individual event should decide what the outcome of choices are)
	public void Option1() {
		SatisfactionMeter.increaseSatisfactionScore(4);
		this.End();
	}
	public void Option2() {
		this.End();
	}
	public void Option3() {
		SatisfactionMeter.decreaseSatisfactionScore(4);
		this.End();
	}

	public void End() {
		eventUI.close();
		GameUtils.currentEvent = null;
	}
}
