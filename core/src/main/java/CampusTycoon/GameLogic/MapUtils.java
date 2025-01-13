package CampusTycoon.GameLogic;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

import CampusTycoon.GameLogic.Buildings.*;
import CampusTycoon.GameLogic.Tiles.*;

import static CampusTycoon.GameLogic.Timer.isRunning;

public class MapUtils {
    private Map map;

    public MapUtils(Map Map) {
        map = Map;
    }

    public abstract class Placement {
        public static final String AccommodationBuilding = "ACCOMMODATION";
        public static final String StudyBuilding = "STUDY";
        public static final String CafeteriaBuilding = "CAFETERIA";
        public static final String RelaxationBuilding = "RELAXATION";
        public static final String RestaurantBuilding = "RESTAURANT";
        public static final String Road = "ROAD"; // Most definitely not implemented yet
    }

    public static Building getBuilding(String buildingType) {
        switch (buildingType) {
            case Placement.AccommodationBuilding:
                return new Accommodation();
            case Placement.StudyBuilding:
                return new Study();
            case Placement.CafeteriaBuilding:
                return new Cafeteria();
            case Placement.RelaxationBuilding:
                return new Relaxation();
            case Placement.RestaurantBuilding:
                return new Restaurant();
            default:
                return new Building();
        }
    }

    public boolean buildingPlaceable(Building newBuilding) {

        for (Building Building : map.buildings) {
            Coordinate b = Building.position;
            Coordinate newPos = newBuilding.position;

            // Checks if the position of the new building would cause any part of itself to
            // overlap with an existing building
            if (newPos.x + newBuilding.width - 1 >= b.x && newPos.x < b.x + Building.width &&
                newPos.y + newBuilding.height - 1 >= b.y && newPos.y < b.y + Building.height) {
                return false;
            }

            // If the game is paused do not allow placement
            if (!isRunning) {
                return false;
            }


        }
        //Assessment 2
        // Checks if the position of the new building would overlap with water or
        // mountains
        String aUnique = York.RawMap;
        String[] bUnique = aUnique.split("\n");
        String[] cUnique = bUnique[1].split(" ");
        String dUnique = cUnique[0];
        try {

            for (int iUnique = 0; iUnique < newBuilding.width; iUnique++) {
                for (int jUnique = 0; jUnique < newBuilding.height; jUnique++) {
                    cUnique = bUnique[48 - newBuilding.position.y - jUnique].split(" ");
                    if (!(Integer.parseInt(cUnique[newBuilding.position.x + iUnique]) == Integer.parseInt(dUnique))) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
        }
        return true;
    }


    /**
     * Assessment 2
     * Determines if the player can afford to build a building based on its cost.
     * If the player has enough money then the cost is subtracted from the available funds.
     *
     * @param cost The cost of the building to be constructed.
     * @return true if the player can afford the building, otherwise false.
     */
    public boolean canAffordBuilding(int cost) {


        if (Money.getMoney() - cost >= 0) {

            Money.decreaseMoney(cost);
            return true;
        } else {
            return false;
        }

    }


    public boolean tileHasBuilding(Coordinate tile) {
        for (Building building : map.buildings) {
            Coordinate pos = building.position;

            // Checks if a building occupies the current tile space
            if (tile.x >= pos.x && tile.x < pos.x + building.width &&
                tile.y >= pos.y && tile.y < pos.y + building.height) {
                return true;
            }
        }
        return false;
    }

    public boolean outsideMap(Coordinate tile) {
        if (tile.x >= map.width || tile.x < 0 ||
            tile.y >= map.height || tile.y < 0) {
            return true;
        }
        return false;
    }

    public void initialiseBuildings() {
        map.buildings = new ArrayList<Building>();

        // Forcefully enables placement mode
        map.placing = true;

        // Adds a few different buildings to the map
        // Used to add a few buildings, was disabled due to bug with desyncing camera
        // when collision with map was added
        // map.placementType = Placement.CafeteriaBuilding;
        // map.placeBuilding(new Coordinate(4, 5));
        // map.placementType = Placement.StudyBuilding;
        // map.placeBuilding(new Coordinate(10, 13));
        map.placementType = Placement.AccommodationBuilding;
        // map.placeBuilding(new Coordinate(23, 14));
    }

    public void initialiseGrid() {
        // Read map file from somewhere
        // Format:
        // width height
        // mapTileID
        // e.g. 3 3
        // 0 0 1
        // 0 1 1
        // 1 2 1

        String content = "";
        try {
            // byte[] byteContent =
            // getClass().getResourceAsStream(Map.defaultMap).readAllBytes();
            // content = new String(byteContent, StandardCharsets.UTF_8);
            content = York.RawMap;
        } catch (Exception e) {
        }

        String[] lines = content.split("\n");

        String[] size = lines[0].split(" ");
        map.width = Integer.parseInt(size[0].toString().strip());
        map.height = Integer.parseInt(size[1].toString().strip());

        map.grid = new ArrayList<ArrayList<Tile>>(map.height);

        for (int i = 0; i < map.height; i++) {
            ArrayList<Tile> row = new ArrayList<Tile>(map.width);
            String[] tiles = lines[1 + i].split(" ");
            for (int j = 0; j < map.width; j++) {
                Tile tile = getTile(Integer.parseInt(tiles[j].strip()));
                row.add(tile);
            }
            map.grid.add(row);
        }
    }

    public Tile getTile(int tileID) {
        Tile tile;
        switch (tileID) {
            case 0:
                tile = new Grass();
                break;
            case 1:
                tile = new Lake();
                break;
            case 2:
                tile = new Mountain();
                break;
            default:
                System.out.println("Unknown tile ID: \"" + tileID + "\"");
                tile = null;
        }
        return tile;
    }
}
