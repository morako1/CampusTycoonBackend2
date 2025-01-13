package CampusTycoon.UI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import CampusTycoon.GameLogic.Coordinate;

public class Drawer {
    private static List<DrawInfo> drawQueue = new LinkedList<>();
    private static SpriteBatch spriteBatch = new SpriteBatch();
    private static BitmapFont font = new BitmapFont();
    private static Map<String, Texture>
        textures = new HashMap<String, Texture>(); // Note: this exists because I learned that generating hundreds of new textures every second is NOT a good idea
    private static Map<Texture, Map<Integer, TextureRegion>>
        textureRegions = new HashMap<Texture, Map<Integer, TextureRegion>>();

    private static class DrawInfo { // Cursed static class with non-static members
        public int layer; // Used to determine draw order
        public Component component;

        public DrawInfo(int Layer, Component Component) {
            layer = Layer;
            component = Component;
        }
    }

    public static <T> List<T> popLayer(int layer, T type) {
        List<T> layerComponents = new ArrayList<T>();

        int left = binarySearch(layer - 1);
        int right = binarySearch(layer);
        int count = right - left;

        for (int i = 0; i < count; i++) {
            if (drawQueue.get(left).component.getClass() == type.getClass()) {
                T component = (T) drawQueue.get(left).component; // Should be a safe cast
                layerComponents.add(component);
                drawQueue.remove(left);
            }
        }
        return layerComponents;
    }

    public static void clear() {
        drawQueue.clear();
    }

    public static void drawAll() {
        spriteBatch.begin();

        //Drawing the components in the drawQueue
        for (int i = 0; i < drawQueue.size(); i++) {
            // drawQueue is pre-sorted, so this draws primarily in order of layer (ascending)
            // Followed by the time the component was added to the queue (within each layer)

            Component component = drawQueue.get(i).component;
            if (component.sprite.usesSpriteSheet) {
                drawRegion(component);
            } else if (component.isText) {
                drawText(component);
            } else {
                draw(component);
            }
        }
        spriteBatch.end();
    }

    private static void drawText(Component component) {
        //font.setFixedWidthGlyphs(component.text);
        font.getRegion().getTexture().setFilter(
            TextureFilter.Linear, TextureFilter.Linear);
        font.getData().setScale(component.width, component.height);

        font.draw(spriteBatch, component.text,
            component.x, component.y);
    }

    private static void draw(Component component) {
        Sprite sprite = component.sprite;
        sprite.stepAnimation();
        // Important to note that the animation is stepped and then immediately retrieved for drawing
        // This is to prevent the 1 frame of visual delay that would be caused by stepping after returning the image

        String imagePath = sprite.getImagePath();
        Texture image = getTexture(imagePath);
        spriteBatch.draw(
            image, // Image texture to draw
            component.x, component.y, // Coordinates to draw at
            component.width, component.height); // Size of the image
    }

    private static void drawRegion(Component component) {
        Sprite sprite = component.sprite;
        sprite.stepAnimation();

        String sheetPath = sprite.getImagePath();
        Texture sheet = getTexture(sheetPath);
        TextureRegion image = getTextureRegion(sheet, sprite);
        spriteBatch.draw(
            image, // Image texture (region) to draw
            component.x, component.y, // Coordinates to draw at
            component.width, component.height); // Size of the image
    }

    private static TextureRegion getTextureRegion(Texture sheet, Sprite sprite) {
        int spriteID = sprite.getID();

        // If a mapping of the spriteSheet does not exist -> creates new mapping
        if (!textureRegions.containsKey(sheet)) {
            Map<Integer, TextureRegion> regions = new HashMap<Integer, TextureRegion>();
            textureRegions.put(sheet, regions);
        }

        Map<Integer, TextureRegion> sheetRegions = textureRegions.get(sheet);

        // If a mapping of the spriteID to a TextureRegion does not exist -> adds that mapping
        if (!sheetRegions.containsKey(spriteID)) {
            Coordinate spriteCoords = sprite.spriteSheet.getRegionCoords(spriteID);

            sheetRegions.put(spriteID, new TextureRegion(
                sheet, // Spritesheet to get sprite from
                spriteCoords.x, spriteCoords.y, // Coordinates of the sprite within the spritesheet
                sprite.spriteSheet.spriteWidth, sprite.spriteSheet.spriteHeight)); // Sprite dimensions
        }

        return sheetRegions.get(spriteID);
    }

    private static Texture getTexture(String imagePath) {
        if (!textures.containsKey(imagePath)) {
            textures.put(imagePath, new Texture(imagePath));
        }
        return textures.get(imagePath);
    }

    // Updates every component in the drawQueue (usually needed due to screen resolution changes)
    public static void updateAll() {
        for (int i = 0; i < drawQueue.size(); i++) {
            drawQueue.get(i).component.update();
        }
    }

    // Adds the new component to the drawQueue at the end of the given layer
    public static void add(int layer, Component component) {
        drawQueue.add(binarySearch(layer), new DrawInfo(layer, component));
    }

    public static void remove(int layer, Component component) {
        remove(component, binarySearch(layer - 1));
    }

    public static void remove(Component component) {
        remove(component, 0);
    }

    // Removes the given component from the drawQueue
    // Does a linear search of the drawQueue starting from the index given by startIndex
    private static void remove(Component component, int startIndex) {
        for (int index = startIndex; index < drawQueue.size(); index++) {
            if (drawQueue.get(index).component.equals(component)) {
                drawQueue.remove(index);
                return;
            }
        }
    }

    // Searches the drawQueue to find the index of the end of a given layer
    // e.g. in {-1, 0, 0, 1, 1, 1, 2, 2}, searching for '1' would give the index of the first 2
    private static int binarySearch(int target) {
        int left = 0;
        int right = drawQueue.size() - 1;
        int midpoint = 0;

        if (drawQueue.size() <= 1) {
            return drawQueue.size();
        }
        if (drawQueue.get(left).layer > target) {
            return 0;
        }
        if (drawQueue.get(right).layer < target) {
            return right + 1;
        }

        while (right - left > 1) {
            midpoint = (left + right) / 2;
            int value = drawQueue.get(midpoint).layer;

            if (value <= target) {
                left = midpoint;
                continue;
            }
            if (value > target) {
                right = midpoint;
                continue;
            }
        }
        midpoint = (left + right) / 2;
        return midpoint + 1;
    }
}
