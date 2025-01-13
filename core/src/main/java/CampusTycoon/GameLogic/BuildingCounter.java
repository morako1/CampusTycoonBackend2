package CampusTycoon.GameLogic;

import CampusTycoon.GameLogic.Buildings.*;
import CampusTycoon.UI.Components.MenuText;

import java.util.ArrayList;
import java.util.List;

public class BuildingCounter {
    private static int totalBuildingCount = 0;

    // [0] = "Accommodation", [1] = "Study", [2] = "Cafeteria", [3] = "Relaxation",
    // [4] = "null", [5] = "Restaurant"
    private static int[] buildingCounts = new int[6];
    public static List<MenuText> UI = new ArrayList<MenuText>();
    public static MenuText totalCountUI;

    public static void reset() {
        totalBuildingCount = 0;
        buildingCounts = new int[6];
        UI = new ArrayList<MenuText>();
    }

    public static int getTotalBuildingCount() {
        return totalBuildingCount;
    }

    public static int getBuildingCount(String building) {
        return buildingCounts[getBuildingCountIndex(building)];
    }

    private static int getBuildingCountIndex(String building) {
        switch (building) {
            case Accommodation.buildingName:
                return 0;
            case Study.buildingName:
                return 1;
            case Cafeteria.buildingName:
                return 2;
            case Relaxation.buildingName:
                return 3;
            case Restaurant.buildingName:
                return 5;
            default:
                return 4; // Default building
        }
    }


    public static void updateDisplay() {
        if (UI.isEmpty()) {
            return;
        }


        totalCountUI.text = String.valueOf(totalBuildingCount);
        totalCountUI.update();
    }

    public static void increaseBuildingCounter(String building, int value) {
        buildingCounts[getBuildingCountIndex(building)] += value;
        increaseBuildingCounter(value);
    }

    public static void decreaseBuildingCounter(String building, int value) {
        buildingCounts[getBuildingCountIndex(building)] -= value;
        decreaseBuildingCounter(value);
    }

    public static void increaseBuildingCounter(int value) {
        totalBuildingCount += value;
        updateDisplay();
    }

    public static void decreaseBuildingCounter(int value) {
        totalBuildingCount -= value;
        updateDisplay();
    }

}
