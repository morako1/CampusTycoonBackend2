package CampusTycoon.GameLogic.Events;

import CampusTycoon.GameLogic.Money;
import CampusTycoon.GameLogic.SatisfactionMeter;
import CampusTycoon.GameLogic.Timer;
import CampusTycoon.GameUtils;
import CampusTycoon.UI.Systems.EventPopup;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrikeEvent extends Event{



    public StrikeEvent(){

        choices = 2;


        eventText= "The teaching staff at your university\nhave declared a strike as they feel they\nare underpaid. Lectures and tutorials\nare canceled, leaving students\nannoyed. The disruption could have\nnegative effects on student satisfaction.\n \nWill you accept or deny the teachers\nrequest for more pay?";
        acceptText = "Teachers resume their lectures and\ntutorials, grateful for the administrations\nsupport.\n\n-$300";
        rejectText="Your students are upset as lectures\nand tutorials have been interrupted\n\n-1000 satisfaction";
        choiceText= new ArrayList<String>(Arrays.asList(
            "Accept","Reject"));
       UI();
    }

    @Override
    public void applyEffects() {

        //accept
        if(choice  ==1 ){

            System.out.println("accept effect");
            Money.decreaseMoney(300);
        }

        //reject
        else if (choice ==2) {
            SatisfactionMeter.decreaseSatisfactionScore(1000);
            System.out.println("reject effect");
        }


    }
}
