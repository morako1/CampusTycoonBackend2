package CampusTycoon.UI.Components;

import CampusTycoon.UI.Component;
import CampusTycoon.UI.SpriteSheet;

public class MapTile extends Component {

    private int mapHeight;
    public int gridX, gridY;

    public MapTile(SpriteSheet SpriteSheet, int TileID, float X, float Y, float Width, float Height) {
        super(SpriteSheet, TileID, X, Y, Width, Height);
    }

    public MapTile(SpriteSheet SpriteSheet, int TileID, float X, float Y) {
        super(SpriteSheet, TileID, X, Y, SpriteSheet.spriteWidth, SpriteSheet.spriteHeight);
    }

    public MapTile(SpriteSheet SpriteSheet, int TileID, float X, float Y, int GridX, int GridY, int MapHeight) {
        super(SpriteSheet, TileID, X, Y, SpriteSheet.spriteWidth, SpriteSheet.spriteHeight);
        mapHeight = MapHeight;
        gridX = GridX;
        gridY = GridY;
    }

    public MapTile() {
        super(0, 0, 0, 0);
    }


    public void applyZoomOffset() {
        this.baseX = this.offsetX + getBaseWidth() * this.gridX;
        this.baseY = this.offsetY + getBaseHeight() * (mapHeight - 1 - this.gridY);
        update();
    }

    @Override
    public void setClickAction(String action) {
        throw new UnsupportedOperationException("Unimplemented method 'setClickAction'");
    }
}
