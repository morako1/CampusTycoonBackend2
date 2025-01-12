package CampusTycoon.GameLogic.Buildings;

import CampusTycoon.GameLogic.BuildingCounter;
import CampusTycoon.GameLogic.Coordinate;

public class Restaurant extends Building {
    public static final String defaultImage = "Buildings\\Restaurant.png";
    public static final String buildingName = "Restaurant";
    public static int width = 3, height = 3;
    public static int cost = 40;
    public Restaurant(Coordinate Position) {
        super(Position, defaultImage,cost, width, height);
    }

    public Restaurant() {
        super(new Coordinate(0, 0), defaultImage,cost, width, height);
    }

    @Override
    public void incrementBuildingCounter() {
        BuildingCounter.increaseBuildingCounter(buildingName, 1);
    }
}
