package CampusTycoon.GameLogic;

import CampusTycoon.GameLogic.Buildings.Building;
import CampusTycoon.GameUtils;
import CampusTycoon.UI.Component;

import java.util.List;

public class SatisfactionMeter {
    public static Component satisfactionText;
    private static int satisfactionScore = 0;
    private static int newscore = 0;


    public static int getSatisfactionScore() {
        return satisfactionScore;
    }

    /**
     * Assessment 2
     * Calculate the satisfaction scor
     */
    public static void calculateSatisfaction() {
        resetSatisfactionScore();

        for (Building i : GameUtils.map.buildings) {

            if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.AccommodationBuilding).getClass())) {


                accommodationSatisfaction(i, GameUtils.map.buildings);


            }


            if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.StudyBuilding).getClass())) {


                StudySatisfaction(i, GameUtils.map.buildings);


            }

        }


    }

    /**
     * Assessment 2
     * Calculates how much satisfaction score the accomodation building adds
     *
     * @param building the building the calculate the score for
     */
    private static void accommodationSatisfaction(Building building, List<Building> list) {
        int counter = 5;
        float studydistance = 1000;
        float restaurantdistance = 1000;
        float relaxationdistance = 1000;
        float cafedistance = 1000;

        for (Building i : list) {




            //Iterate through buildings to find closest study building
            if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.StudyBuilding).getClass())) {

                float checkDistance = (float) Math.sqrt((building.position.y - i.position.y) * (building.position.y - i.position.y) + (building.position.x - i.position.x) * (building.position.x - i.position.x));

                if (checkDistance < studydistance) {
                    studydistance = checkDistance;



                }



            }
            else if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.RestaurantBuilding).getClass())) {

                float checkDistance = (float) Math.sqrt((building.position.y - i.position.y) * (building.position.y - i.position.y) + (building.position.x - i.position.x) * (building.position.x - i.position.x));

                if (checkDistance < restaurantdistance) {
                    restaurantdistance = checkDistance;



                }

            }
            else if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.RelaxationBuilding).getClass())) {

                float checkDistance = (float) Math.sqrt((building.position.y - i.position.y) * (building.position.y - i.position.y) + (building.position.x - i.position.x) * (building.position.x - i.position.x));

                if (checkDistance < relaxationdistance) {
                    relaxationdistance = checkDistance;



                }

            }

            else if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.CafeteriaBuilding).getClass())) {

                float checkDistance = (float) Math.sqrt((building.position.y - i.position.y) * (building.position.y - i.position.y) + (building.position.x - i.position.x) * (building.position.x - i.position.x));

                if (checkDistance < cafedistance) {
                    cafedistance = checkDistance;



                }

            }





        }


        //There is no study area within 30
        if (studydistance >= 30d) {


        } else {
            float distanceScore = (30 - studydistance);
            if (distanceScore > 20) {
                distanceScore = 20;
            }

            counter += (int) distanceScore*10;
        }

        if(cafedistance<15){
            counter+=55;
        }

        if(restaurantdistance<15){
            counter+=105;
        }

        if(relaxationdistance<15){
            counter+=85;
        }

        if(relaxationdistance<15 && restaurantdistance<15 && cafedistance<15 && studydistance<20){

            counter+=300;

        }

        System.out.println(counter + "Building:");
        increaseSatisfactionScore(counter);


    }
    private static void StudySatisfaction(Building building, List<Building> list) {
        int accommodationCounter =0;
        int counter = 1;
        float distance = 1000;

        for (Building i : list) {




            //Iterate through buildings to find out how many accommodations are close
            if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.AccommodationBuilding).getClass())) {

                float checkDistance = (float) Math.sqrt((building.position.y - i.position.y) * (building.position.y - i.position.y) + (building.position.x - i.position.x) * (building.position.x - i.position.x));
                System.out.println(checkDistance + "Distance");
                if (checkDistance <= 7.5f) {
                    accommodationCounter++;

                }
            }
        }


        //There is no study area within 35

        System.out.println(100 - (accommodationCounter*25) + "accomcount:");

        int checkvalue = 200 - (accommodationCounter*40);
        if (checkvalue<0){

            decreaseSatisfactionScore(Math.abs(checkvalue));
        }
        else{

            increaseSatisfactionScore(1);

        }




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

    public static void resetSatisfactionScore() {
        satisfactionScore = 0;
    }
}
