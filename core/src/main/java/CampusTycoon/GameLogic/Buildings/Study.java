package CampusTycoon.GameLogic.Buildings;

import CampusTycoon.GameLogic.BuildingCounter;
import CampusTycoon.GameLogic.Coordinate;

public class Study extends Building{
	public static final String defaultImage = "Buildings\\Study.png";
	public static final String buildingName = "Study";
	public static int width = 3, height = 3;
    public static int cost = 50;
    public int  AccommodationsAssigned = 0;
	public Study(Coordinate Position) {
		super(Position, defaultImage,cost, width , height);
	}
	public Study() {
		super(new Coordinate(0, 0), defaultImage,cost, width , height);
	}


	@Override
	public void incrementBuildingCounter() {
		BuildingCounter.increaseBuildingCounter(buildingName, 1);
	}
}
