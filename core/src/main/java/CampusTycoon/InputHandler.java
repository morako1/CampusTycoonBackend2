package CampusTycoon;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputProcessor;

import CampusTycoon.UI.Camera;
import CampusTycoon.UI.Component;
import CampusTycoon.UI.Window;

public class InputHandler implements InputProcessor {
	private static final int LeftClick = 0;
	private static final int RightClick = 1;
	private static final int MiddleClick = 2;

	private static List<Component> clickables = new ArrayList<Component>();
	private static boolean leftClickDown = false;


	// Removes a component from clickables, so that it stops being processed
	// Does a linear search through the list to find the component
	public static void remove(Component component) {
		for (int index = 0; index < clickables.size(); index++) {
			if (component.equals(clickables.get(index))) {
				clickables.remove(index);
				return;
			}
		}
	}


	public static void clear() {
		clickables = new ArrayList<Component>();
	}

	public static void add(Component button) {
		clickables.add(button);
	}

	public static void add(List<Component> buttons) {
		clickables.addAll(buttons);
	}

	public boolean keyDown(int keycode) {
		return false;
	}

	public boolean keyUp(int keycode) {
		return false;
	}

	public boolean keyTyped(char character) {
		return false;
	}

	// Called on click
	public boolean touchDown(int x, int y, int pointer, int button) {

		if (button == LeftClick) {
			leftClickDown = true;
		}

		for (Component btn : clickables){
			if (isTouchWithinButton(transformX(x), transformY(y), btn)) {
				btn.onClick();
				Camera.update();

				return true;
			}

		}
        Camera.click(x, y, button);
        return false;


        //return false;
    }

	private int transformX(int x) {
		return (int)(x * Component.widthRatio);
	}
	private int transformY(int y) {
		return Window.defaultHeight - (int)(y * Component.heightRatio);
	}

	private boolean isTouchWithinButton(int x, int y, Component button) {
		if (button == null) {
			return false; // Button is null, so return false to avoid a NullPointerException
		}
        // Assuming button's (x, y) represents the bottom-left corner and has width and height
        return x >= button.getX() && x <= button.getX() + button.getWidth()
                && y >= button.getY() && y <= button.getY() + button.getHeight();
    }

	public boolean touchUp(int x, int y, int pointer, int button) {

		if (button == LeftClick) {
			leftClickDown = false;
			Camera.lift(x, y, button);
		}
		return true;
	}

	public boolean touchDragged(int x, int y, int pointer) {

		Camera.drag(x, y);
		return true;
	}

	public boolean mouseMoved(int x, int y) {

		Camera.checkMouseOverTile(x ,y);
		Camera.drawCursor();
		return true;
	}

	public boolean scrolled(float amountX, float amountY) {

		Camera.scroll(amountY);
		return true;
	}

	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}
}
