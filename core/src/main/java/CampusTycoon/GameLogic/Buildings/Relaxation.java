package CampusTycoon.GameLogic.Buildings;

import CampusTycoon.GameLogic.BuildingCounter;
import CampusTycoon.GameLogic.Coordinate;

public class Relaxation extends Building {
	public static final String defaultImage = "Buildings\\Relaxation.png";
	public static final String buildingName = "Relaxation";
	public static int width = 3, height = 3;
    public static int cost = 50;
	public Relaxation(Coordinate Position) {
		super(Position, defaultImage,cost, width , height);
	}
	public Relaxation() {
		super(new Coordinate(0, 0), defaultImage,cost, width , height);
	}


	@Override
	public void incrementBuildingCounter() {
		BuildingCounter.increaseBuildingCounter(buildingName, 1);
	}
}
