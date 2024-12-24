package CampusTycoon.UI;

import CampusTycoon.GameLogic.Coordinate;

public class SpriteSheet {
	public String imagePath;
	public int spriteWidth; // Width of each sprite in the spriteSheet (assumes all sprites in the sheet are the same dimensions)
	public int spriteHeight; // Height of each sprite in the spriteSheet
	private int sheetWidth; // Width of the entire sheet (in units of spriteWidth)
	private int sheetHeight; // Height of the entire sheet (in units of spriteHeight)
	
	public SpriteSheet(String ImagePath, int SheetWidth, int SheetHeight, int SpriteWidth, int SpriteHeight) {
		
		imagePath = ImagePath;
		spriteWidth = SpriteWidth;
		spriteHeight = SpriteHeight;
		
		// width (how many sprites fit in a row) = width (in pixels) / spriteWidth (in pixels)
		sheetWidth = SheetWidth;  
		sheetHeight = SheetHeight;
	}
	
	public Coordinate getRegionCoords(int spriteID) {
		Coordinate coord = new Coordinate(
			(spriteID % sheetWidth) * spriteWidth, // x
			(spriteID / sheetHeight) * spriteHeight // y
		);
		return coord;
	}
}
