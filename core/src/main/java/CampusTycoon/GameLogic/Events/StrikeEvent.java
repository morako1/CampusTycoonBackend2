package CampusTycoon.GameLogic.Events;

import CampusTycoon.GameLogic.SatisfactionMeter;
import CampusTycoon.GameLogic.Timer;
import CampusTycoon.GameUtils;
import CampusTycoon.UI.Systems.EventPopup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrikeEvent extends Event{



    public StrikeEvent(){

        choices = 2;


        eventText= "The teaching staff at your university\nhave declared a strike as they feel they\nare underpaid. Lectures and tutorials\nare canceled, leaving students\nannoyed. The disruption could have\nnegative effects on student satisfaction.\n \nWill you accept or deny the teachers\nrequest for more pay?";
        acceptText = "Teachers resume their lectures and\ntutorials, grateful for the administrations\nsupport. Their enthusiasm rubs off on\nthe students. who feel reassured that\nthey can continue their education.\n\n+10% Student Satisfaction\n-$100";
        rejectText="";
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
