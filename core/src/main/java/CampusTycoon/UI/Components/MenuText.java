package CampusTycoon.UI.Components;

import CampusTycoon.UI.Component;
import com.badlogic.gdx.graphics.Color;


public class MenuText extends Component {

	public MenuText(String text, float X, float Y, float WidthScale, float HeightScale) {
		super(X, Y, WidthScale, HeightScale);
		this.text = text;
		this.isText = true;
	}



	// Not needed as text elements don't need to do anything on click
	@Override
	public void setClickAction(String action) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setClickAction'");
	}
}

