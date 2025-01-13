package CampusTycoon.GameLogic;

import CampusTycoon.GameLogic.Buildings.*;

public class Achievements {

    /**
     * Assessment 2
     * Determines and returns a string containing the achievements the player obtained
     *
     * @return A string that lists all achievements earned
     */
    public static String GetAchievements() {
        int cafe;
        int restaurant;
        int relaxation;
        int study;
        int accommodation;

        String output = "";


        cafe = BuildingCounter.getBuildingCount(Cafeteria.buildingName);
        accommodation = BuildingCounter.getBuildingCount(Accommodation.buildingName);
        study = BuildingCounter.getBuildingCount(Study.buildingName);
        relaxation = BuildingCounter.getBuildingCount(Relaxation.buildingName);
        restaurant = BuildingCounter.getBuildingCount(Restaurant.buildingName);


        if (SatisfactionMeter.getSatisfactionScore() >= 1000) {
            output += "Achieve 1000 satisfaction score\n";

        }
        if (SatisfactionMeter.getSatisfactionScore() >= 5000) {
            output += "Achieve 5000 satisfaction score\n";

        }

        if (SatisfactionMeter.getSatisfactionScore() >= 10000) {
            output += "Achieve 10000 satisfaction scores\n";

        }


        if (cafe >= 5) {
            output += "Place 5 Cafeterias\n";
        }
        if (accommodation >= 5) {
            output += "Place 5 Accommodations\n";
        }
        if (study >= 5) {
            output += "Place 5 Study Buildings\n";
        }
        if (relaxation >= 5) {
            output += "Place 5 Parks\n";
        }
        if (restaurant >= 5) {
            output += "Place 5 Restaurants\n";
        }


        if (BuildingCounter.getTotalBuildingCount() >= 10) {

            output += "Place 10 Buildings\n";
        }

        if (BuildingCounter.getTotalBuildingCount() >= 15) {

            output += "Place 15 Buildings\n";
        }

        if (BuildingCounter.getTotalBuildingCount() >= 20) {

            output += "Place 20 Buildings\n";
        }

        return output;


    }


}
