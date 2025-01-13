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
     * Called any time a building is placed
     * It calculates the satisfaction score
     */
    public static void calculateSatisfaction() {
        resetSatisfactionScore();

        //(Assessment 2)Iterates through all the buildings on the map
        for (Building i : GameUtils.map.buildings) {

            if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.AccommodationBuilding).getClass())) {

                //(Assessment 2) For every accommodation building call this function
                accommodationSatisfaction(i, GameUtils.map.buildings);


            }


            //(Assessment 2) For every study building call this function
            if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.StudyBuilding).getClass())) {


                StudySatisfaction(i, GameUtils.map.buildings);


            }

        }


    }

    /**
     * Assessment 2
     * Calculates satisfaction score for accommodation building type based on the proximity of other buildings
     * (study buildings, restaurants, relaxation areas, and cafeterias).
     *
     * @param building The building for which satisfaction is calculated for.
     * @param list     A list of all buildings on the map.
     */
    private static void accommodationSatisfaction(Building building, List<Building> list) {
        int counter = 5;
        float studydistance = 1000;
        float restaurantdistance = 1000;
        float relaxationdistance = 1000;
        float cafedistance = 1000;

        for (Building i : list) {
            //Assessment 2
            //This function goes through all the buildings types and finds the closest one to the accommodation
            //Building we are calculating the satisfaction for
            if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.StudyBuilding).getClass())) {

                float checkDistance = (float) Math.sqrt((building.position.y - i.position.y) * (building.position.y - i.position.y) + (building.position.x - i.position.x) * (building.position.x - i.position.x));

                if (checkDistance < studydistance) {
                    studydistance = checkDistance;
                }
            } else if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.RestaurantBuilding).getClass())) {

                float checkDistance = (float) Math.sqrt((building.position.y - i.position.y) * (building.position.y - i.position.y) + (building.position.x - i.position.x) * (building.position.x - i.position.x));

                if (checkDistance < restaurantdistance) {
                    restaurantdistance = checkDistance;


                }

            } else if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.RelaxationBuilding).getClass())) {

                float checkDistance = (float) Math.sqrt((building.position.y - i.position.y) * (building.position.y - i.position.y) + (building.position.x - i.position.x) * (building.position.x - i.position.x));
                if (checkDistance < relaxationdistance) {
                    relaxationdistance = checkDistance;
                }
            } else if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.CafeteriaBuilding).getClass())) {
                float checkDistance = (float) Math.sqrt((building.position.y - i.position.y) * (building.position.y - i.position.y) + (building.position.x - i.position.x) * (building.position.x - i.position.x));
                if (checkDistance < cafedistance) {
                    cafedistance = checkDistance;
                }
            }


        }

        //Assessment 2
        //There is no study area within 30 then dont give any extra satisfaction score
        //If there is, calculate the satisfaction score depending on how close it is to the acccomodation
        if (studydistance >= 30d) {


        } else {
            float distanceScore = (30 - studydistance);
            if (distanceScore > 20) {
                distanceScore = 20;
            }

            counter += (int) distanceScore * 10;
        }

        //Calculate the score for the other buildings
        if (cafedistance < 15) {
            counter += 55;
        }

        if (restaurantdistance < 15) {
            counter += 105;
        }

        if (relaxationdistance < 15) {
            counter += 85;
        }

        //Assessment 2
        //Give bonus score if an accomodation is placed next to all other buildings
        if (relaxationdistance < 15 && restaurantdistance < 15 && cafedistance < 15 && studydistance < 20) {

            counter += 300;

        }


        increaseSatisfactionScore(counter);


    }

    /**
     * Assessment 2
     * Calculates the overcrowding penalty for having too many students  at 1 study area
     *
     * @param building The study building for which penalty is being calculated.
     * @param list     A list of all buildings on the map.
     */
    private static void StudySatisfaction(Building building, List<Building> list) {
        int accommodationCounter = 0;
        int counter = 1;
        float distance = 1000;

        for (Building i : list) {


            //Assessment 2
            //Iterate through buildings to find out how many accommodations are close
            if (i.getClass().equals(MapUtils.getBuilding(MapUtils.Placement.AccommodationBuilding).getClass())) {

                float checkDistance = (float) Math.sqrt((building.position.y - i.position.y) * (building.position.y - i.position.y) + (building.position.x - i.position.x) * (building.position.x - i.position.x));
                System.out.println(checkDistance + "Distance");
                if (checkDistance <= 7.5f) {
                    accommodationCounter++;

                }
            }
        }


        int checkvalue = 3200 - (accommodationCounter * 640);
        if (checkvalue < 0) {

            decreaseSatisfactionScore(Math.abs(checkvalue));
        } else {

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
