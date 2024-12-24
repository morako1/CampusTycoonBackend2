package CampusTycoon.UI.Components;

import java.util.List;

import CampusTycoon.GameLogic.Tiles.Tile;
import CampusTycoon.UI.Component;

public class MapBuilding extends Component {
	float gridBaseWidth, gridBaseHeight;
	float gridX, gridY;
	
	public MapBuilding(String imagePath, float X, float Y, float Width, float Height) {
		super(imagePath, getX(X), getY(Y), Width * Tile.SpriteSize, Height * Tile.SpriteSize);
		initialiseBuilding(X, Y, Width, Height);
	}
	public MapBuilding(List<String> imagePaths, float X, float Y, float Width, float Height) {
		super(imagePaths, getX(X), getY(Y), Width * Tile.SpriteSize, Height * Tile.SpriteSize);
		initialiseBuilding(X, Y, Width, Height);
	}
	public MapBuilding(String imagePath, float X, float Y) {
		super(imagePath, getX(X), getY(Y), Tile.SpriteSize, Tile.SpriteSize);
		initialiseBuilding(X, Y, 1, 1);
	}
	public MapBuilding() {
		super(0, 0, 0, 0);
	}
	
	private void initialiseBuilding(float X, float Y, float Width, float Height) {
		gridX = X;
		gridY = Y;
		gridBaseWidth = Width;
		gridBaseHeight = Height;
	}
	
	private static float getX(float X) {
		return X * Tile.SpriteSize;
	}
	private static float getY(float Y) {
		return Y * Tile.SpriteSize;
	}
	
	public void setGridCoordinates(int X, int Y) {
		gridX = X;
		gridY = Y;
		initialise(getX(X), getY(Y), baseWidth, baseHeight, scale);
	}
	
	public void applyZoomOffset() {
		this.baseX = this.offsetX + (getBaseWidth() * this.gridX) / this.gridBaseWidth;
		this.baseY = this.offsetY + (getBaseHeight() * this.gridY) / this.gridBaseHeight;
		update();
	}
	
	@Override
	public void setClickAction(String action) {
		throw new UnsupportedOperationException("Unimplemented method 'setClickAction'");
	}
	
}
