package CampusTycoon.GameLogic.Tiles;

import CampusTycoon.GameLogic.Coordinate;

public interface Tile {

    // The Width and Length of any sprite
    static final int SpriteSize = 64;

    // Spritemap is a 12x12 grid of 64x64 sprites
    static final int SpriteMapSize = 12;


    public int getTileID();

    default Coordinate getSpriteCoords() {
        int tileID = getTileID();

        int x = SpriteSize * (tileID % SpriteMapSize);
        int y = SpriteSize * (tileID / SpriteMapSize);

        return new Coordinate(x, y);
    }

    // I don't actually use this for anything right now but I thought it might be useful
    public String debugMessage();
}
