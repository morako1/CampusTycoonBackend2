package CampusTycoon.UI.Systems;

import CampusTycoon.GameLogic.Map;
import CampusTycoon.GameLogic.Tiles.Tile;
import CampusTycoon.UI.Drawer;
import CampusTycoon.UI.SpriteSheet;
import CampusTycoon.UI.Camera;
import CampusTycoon.UI.Component.Anchor;
import CampusTycoon.UI.Components.MapTile;

import java.util.List;

public class MapDisplay {
	public static final int Layer = -2;
	private Map map;
	private SpriteSheet spriteSheet;
	
	public MapDisplay(Map Map) {
		map = Map;
		initialise();
	}
	
	public void drawMap() {
		for (int row = 0; row < map.height; row++) {
			List<Tile> mapRow = map.grid.get(row);
			for (int col = 0; col < map.width; col++) {
				Tile tile = mapRow.get(col);
				MapTile drawTile = new MapTile(
					spriteSheet, 
					tile.getTileID(), 
					getX(col), getY(row),
					col, row,
					map.height);
				drawTile.setAnchor(Anchor.BottomLeft);
				Drawer.add(Layer, drawTile);
			}
		}
	}
	
	private int getX(int x) {
		return (int)(spriteSheet.spriteWidth * x * Camera.zoom);
	}
	
	private int getY(int y) {
		return (int)(spriteSheet.spriteHeight * (map.height - 1 - y) * Camera.zoom);
	}
	
	private void initialise() {
		spriteSheet = new SpriteSheet(
			"Tiles\\SpriteMap.png", 
			12, 12, 
			Tile.SpriteSize, Tile.SpriteSize);
	}
}