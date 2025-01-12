package CampusTycoon.GameLogic.Buildings;

import CampusTycoon.GameLogic.BuildingCounter;
import CampusTycoon.GameLogic.Coordinate;

public class Cafeteria extends Building {
	public static final String defaultImage = "Buildings\\Cafeteria.png";
	public static final String buildingName = "Cafeteria";
	public static int width = 3, height = 3;
    public static int cost = 30;

	public Cafeteria(Coordinate Position) {
		super(Position, defaultImage, width , height);
	}
	public Cafeteria() {
		super(new Coordinate(0, 0), defaultImage, width , height);
	}


	@Override
	public void incrementBuildingCounter() {
		BuildingCounter.increaseBuildingCounter(buildingName, 1);
	}
}
