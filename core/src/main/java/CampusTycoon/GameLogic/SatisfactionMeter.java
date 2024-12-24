package CampusTycoon.GameLogic;

import CampusTycoon.UI.Component;

public class SatisfactionMeter {
	public static Component satisfactionText;
    private static int satisfactionScore = 0;
    private static int newscore = 0;

    public static int getSatisfactionScore() {
        return satisfactionScore;
    }

    public static void increaseSatisfactionScore(int value) {
        newscore = satisfactionScore + value;
        satisfactionScore = newscore;
		
		SatisfactionMeter.updateDisplay();
    }

    public static void decreaseSatisfactionScore(int value) {
        newscore = satisfactionScore - value;
        satisfactionScore = newscore;
		
		SatisfactionMeter.updateDisplay();
    }
	
	public static void updateDisplay() {
		if (satisfactionText == null) {
			return;
		}
		satisfactionText.text = String.valueOf(satisfactionScore);
	}
}
