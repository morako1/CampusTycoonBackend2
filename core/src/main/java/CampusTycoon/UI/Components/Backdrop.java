package CampusTycoon.UI.Components;

import java.util.List;

import CampusTycoon.UI.Component;

public class Backdrop extends Component {

	public Backdrop(float X, float Y, float Width, float Height) {
		super(X, Y, Width, Height);
	}
	public Backdrop(String imagePath, float X, float Y, float Width, float Height) {
		super(imagePath, X, Y, Width, Height);
	}
	public Backdrop(List<String> imagePaths, float X, float Y, float Width, float Height) {
		super(imagePaths, X, Y, Width, Height);
	}

	public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

	public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
	
	
	
	// Not needed as backdrop elements don't need to do anything on click
	@Override
	public void setClickAction(String action) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setClickAction'");
	}
}