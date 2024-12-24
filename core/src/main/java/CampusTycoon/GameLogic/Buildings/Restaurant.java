package CampusTycoon.GameLogic.Buildings;

import CampusTycoon.GameLogic.BuildingCounter;
import CampusTycoon.GameLogic.Coordinate;

public class Restaurant extends Building {
    public static final String defaultImage = "Buildings\\Restaurant.png";
    public static final String buildingName = "Restaurant";
    public static int width = 3, height = 3;

    public Restaurant(Coordinate Position) {
        super(Position, defaultImage, width, height);
    }

    public Restaurant() {
        super(new Coordinate(0, 0), defaultImage, width, height);
    }

    @Override
    public void incrementBuildingCounter() {
        BuildingCounter.increaseBuildingCounter(buildingName, 1);
    }
}
