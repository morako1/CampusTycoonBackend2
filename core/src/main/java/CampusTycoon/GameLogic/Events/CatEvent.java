package CampusTycoon.GameLogic.Events;

import java.util.ArrayList;
import java.util.Arrays;

public class CatEvent extends Event{

    public CatEvent(){

        choices = 2;


        eventText= "A friendly stray cat  starts roaming the\ncampus, quickly becoming a favourite\namong students. However the grounds\nmaintenance team express concern\nabout the cat's presence and ask to\nremove it.\n\nWill you accept their proposal to\nrelocate the cat?";
        acceptText = "With your permission, the grounds\nmaintenance team relocates the cat";
        rejectText="You let the cat stay on campus";
        choiceText= new ArrayList<String>(Arrays.asList(
            "Accept","Reject"));
        UI();
    }

    @Override
    public void applyEffects() {

        //accept
        if(choice  ==1 ){

            //Do nothing

        }
        //reject
        else if (choice ==2) {
            //Do nothing
        }


    }
}
