package CampusTycoon.GameLogic.Buildings;

import CampusTycoon.GameLogic.BuildingCounter;
import CampusTycoon.GameLogic.Coordinate;

public class Accommodation extends Building {
	public static final String defaultImage = "Buildings\\Accommodation.png";
	public static final String buildingName = "Accommodation";
	public static int width = 3, height = 3;
    public static int cost = 50;
	public Accommodation(Coordinate Position) {
		super(Position, defaultImage, width , height);
	}
	public Accommodation() {
		super(new Coordinate(0, 0), defaultImage, width , height);
	}


	@Override
	public void incrementBuildingCounter() {
		BuildingCounter.increaseBuildingCounter(buildingName, 1);
	}
}
