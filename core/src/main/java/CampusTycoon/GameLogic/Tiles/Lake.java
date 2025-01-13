package CampusTycoon.GameLogic.Tiles;

public class Lake implements Tile {
	@Override
	public int getTileID() { return 1; }
	
	@Override
	public String debugMessage() {
		return "I am wet!";
	}
}