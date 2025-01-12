package CampusTycoon.UI;

import java.util.List;

import CampusTycoon.GameUtils;
import CampusTycoon.GameLogic.Coordinate;
import CampusTycoon.GameLogic.Map;
import CampusTycoon.GameLogic.MapUtils;
import CampusTycoon.GameLogic.Buildings.Building;
import CampusTycoon.GameLogic.Tiles.Tile;
import CampusTycoon.UI.Components.MapBuilding;
import CampusTycoon.UI.Components.MapTile;
import CampusTycoon.UI.Systems.BuildingDisplay;
import CampusTycoon.UI.Systems.MapDisplay;

public class Camera {
	public static Map map; // The game map
	public static int gridX, gridY; // Current grid coordinates of the mouse
	public static int x = 0, y = 0; // Current coordinates of the camera
	public static int width = Window.defaultWidth, height = Window.defaultHeight;
	public static float zoom = 1;
	private static final float MinZoom = 0.375f, MaxZoom = 2.75f;
	private static final int ZoomSpeed = 8; // Lower = faster
	private static Coordinate lastMousePos = new Coordinate();
	private static Coordinate lastClickPos = null;
	private static boolean placing;
	private static String placementType;
	private static Building hoverDisplay;

	public static void update() {
		printCameraInfo();
		updateDrawTiles();
		updateDrawBuildings();
		drawCursor();
	}

	// Calculates which Grid coordinate the mouse is over
	private static float getGridX(int X) {
		float GridX = zoom * (X - x) / (Tile.SpriteSize);
		return GridX;
	}
	private static float getGridY(int Y) {
		float GridY = zoom * (Window.height - Y - y) / (Tile.SpriteSize);
		return GridY;
	}

	public static void drawCursor() {
		// Game not started yet
		if (ScreenUtils.currentScreen != ScreenUtils.gameplayScreen) {
			return;
		}

		// Game not initialised yet
		if (map == null) {
			return;
		}

		// If placing is false and was false last check too
		if (placing == false && placing == map.placing) {
			return;
		}

		// If placing was just turned off
		if (placing == true && placing != map.placing) {
			placing = false;
			Drawer.remove(hoverDisplay.drawInfo);
			return;
		}

		// If the hover icon just changed
		if (map != null && hoverDisplay != null &&
		placing == true && map.placementType != placementType) {
			Drawer.remove(hoverDisplay.drawInfo);

			hoverDisplay = MapUtils.getBuilding(map.placementType);
			hoverDisplay.drawInfo.setImage(
				GameUtils.getHoverImagePath(
					hoverDisplay.drawInfo.sprite.getImagePath()));
			Drawer.add(BuildingDisplay.Layer + 1, hoverDisplay.drawInfo);

			placementType = map.placementType;
		}

		// If placing mode was just turned on
		else if (placing != map.placing) {
			placing = true;

			hoverDisplay = MapUtils.getBuilding(map.placementType);
			hoverDisplay.drawInfo.setImage(
				GameUtils.getHoverImagePath(
					hoverDisplay.drawInfo.sprite.getImagePath()));
			Drawer.add(BuildingDisplay.Layer + 1, hoverDisplay.drawInfo);
		}

		hoverDisplay.setPosition(new Coordinate(gridX, gridY));
		updateCursor();
	}

	public static void checkMouseOverTile(int X, int Y) {
		lastMousePos = new Coordinate(X, Y);

		gridX = (int)Math.floor((double)getGridX(X));
		gridY = (int)Math.floor((double)getGridY(Y));

	}

	public static void scroll(float scrollAmount) {
		float oldZoom = zoom;

		zoom += scrollAmount / ZoomSpeed;
		zoom = Math.min(MaxZoom, Math.max(MinZoom, zoom));

		float sign = Math.signum(zoom - oldZoom);
		x = Math.round(x * (oldZoom / zoom) + (sign * 64f) / zoom); // 64f makes sense as it is the tile sprite width
		y = Math.round(y * (oldZoom / zoom) + (sign * 36f) / zoom); // 36f does not make any sense at all (but it works)

		checkMouseOverTile(lastMousePos.x, lastMousePos.y);
		drawCursor();
		update();
	}

	private static void placeBuilding() {
		if (ScreenUtils.currentScreen == ScreenUtils.gameplayScreen) {
			map.placeBuilding(new Coordinate(gridX, gridY));
			update();
		}
	}

	public static void lift(int X, int Y, int button) {
		// TODO: Add a time based check to this too
		// Checks that the mouse has barely moved since clicking
		if (lastClickPos != null &&
		lastClickPos.distance(new Coordinate(X, Y)) < 5) { // 5 is an extremely arbitrary number
			placeBuilding();
		}
	}

	public static void click(int X, int Y, int button) {
		lastMousePos = new Coordinate(X, Y);
		lastClickPos = lastMousePos;
	}

	public static void drag(int mouseX, int mouseY) {
		x += mouseX - lastMousePos.x;
		y -= mouseY - lastMousePos.y;
		lastMousePos = new Coordinate(mouseX, mouseY);
		update();
	}

	private static List<MapTile> popDrawTiles() {
		List<MapTile> components = Drawer.popLayer(MapDisplay.Layer, new MapTile());
		return components;
	}

	private static void updateDrawTiles() {
		List<MapTile> tiles = popDrawTiles();
		for (MapTile tile : tiles) {
			tile.setOffset(x, y);
			tile.setScale(1f / zoom);
			tile.applyZoomOffset();
			Drawer.add(MapDisplay.Layer, tile);
		}
	}

	private static void updateDrawBuildings() {
		List<MapBuilding> buildings = Drawer.popLayer(BuildingDisplay.Layer, new MapBuilding());
		for (MapBuilding building : buildings) {
			building.setOffset(x, y);
			building.setScale(1f / zoom);
			building.applyZoomOffset();
			Drawer.add(BuildingDisplay.Layer, building);
		}
	}

	private static void updateCursor() {
		if (hoverDisplay == null) {
			return;
		}

		hoverDisplay.drawInfo.setOffset(x, y);
		hoverDisplay.drawInfo.setScale(1f / zoom);
		hoverDisplay.drawInfo.applyZoomOffset();

	}

	// For debug purposes
	private static void printCameraInfo() {
		System.out.println("X: " + x + ", Y: " + y +
			"\nWidth: " + width + ", Height: " + height +
			"\nZoom: " + zoom);
	}
}
