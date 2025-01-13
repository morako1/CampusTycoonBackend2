package CampusTycoon.GameLogic.Tiles;

public class Grass implements Tile {
    @Override
    public int getTileID() {
        return 0;
    }

    @Override
    public String debugMessage() {
        return "I am grass!";
    }
}
