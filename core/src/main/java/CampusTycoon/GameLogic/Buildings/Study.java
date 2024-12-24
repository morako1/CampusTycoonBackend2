package CampusTycoon.GameLogic.Buildings;

import CampusTycoon.GameLogic.BuildingCounter;
import CampusTycoon.GameLogic.Coordinate;

public class Study extends Building{
	public static final String defaultImage = "Buildings\\Study.png";
	public static final String buildingName = "Study";
	public static int width = 3, height = 3;
	
	public Study(Coordinate Position) {
		super(Position, defaultImage, width , height);
	}
	public Study() {
		super(new Coordinate(0, 0), defaultImage, width , height);
	}
	
	
	@Override
	public void incrementBuildingCounter() {
		BuildingCounter.increaseBuildingCounter(buildingName, 1);
	}
}
