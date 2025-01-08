package CampusTycoon.GameLogic.Events;

import java.util.ArrayList;
import java.util.Arrays;

public class DonateEvent extends Event {

    public DonateEvent(){

        choices = 2;


        eventText= "A group of successful alumni decide\nthey want to donate to the school\n\nDo you accept their donation?";
        acceptText = "You accept their donation graciously\n\n+$100";
        rejectText="For some reason, you decided to reject\nthe donation";
        choiceText= new ArrayList<String>(Arrays.asList(
            "Accept","Reject"));
        UI();
    }

    @Override
    public void applyEffects() {

        //accept
        if(choice  ==1 ){

            System.out.println("accept effect");

        }
        //reject
        else if (choice ==2) {
            System.out.println("reject effect");
        }


    }
}
