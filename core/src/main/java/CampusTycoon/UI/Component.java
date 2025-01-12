package CampusTycoon.UI;

import java.util.List;
import java.util.function.Consumer;
import CampusTycoon.GameLogic.Timer;



public abstract class Component {

	// Add to this class when implementing a new anchor point
	public abstract class Anchor {
		public static final String TopLeft = "TOPLEFT";
		public static final String TopCentre = "TOPCENTRE";
		public static final String TopRight = "TOPRIGHT";
		public static final String Centre = "CENTRE";
		public static final String BottomLeft = "BOTTOMLEFT";
		public static final String BottomCentre = "BOTTOMCENTRE";
	}

	// Add to this class when implementing new component functionalities
	public final class Actions {
		public static final String OpenGameplayScreen = "OPENGAMEPLAYSCREEN";
		public static final String OpenStartScreen = "OPENSTARTSCREEN";
        public static final String OpenLeaderboardFromSave = "OPENLEADERBOARDFROMSAVE";
        public static final String OpenLeaderboardScreen = "OPENLEADERBOARDSCREEN";
        public static final String QuitGame = "QUITGAME";
        public static final String OpenSaveScreen = "OPENSAVESCREEN";

		public static final String OpenEventPopup = "OPENEVENTPOPUP";
		public static final String CloseEventPopup = "CLOSEEVENTPOPUP";
		public static final String ChooseEventOption = "CHOOSEEVENTOPTION";
		public static final String EndEvent = "ENDEVENT";

		public static final String IncreaseSatisfactionScore = "INCREASESATISFACTIONSCORE";
		public static final String DecreaseSatisfactionSccore = "DECREASESATISFACTIONSCORE";

        public static final String Resume = "RESUME";
        public static final String Pause = "PAUSE";
		public static final String ToggleAccommodationBuilding = "TOGGLEACCOMMODATIONBUILDING";
		public static final String ToggleStudyBuilding = "TOGGLESTUDYBUILDING";
		public static final String ToggleCafeteriaBuilding = "TOGGLECAFETERIABUILDING";
		public static final String ToggleRelaxationBuilding = "TOGGLERELAXATIONBUILDING";
		public static final String ToggleRestaurantBuilding = "TOGGLERESTAURANTBUILDING";
	}

	protected Consumer<Boolean> clickAction = a -> none(a);
	protected Consumer<Boolean> hoverAction = a -> none(a);
	protected Consumer<Boolean> callAction = a -> none(a);

	protected static void none(Boolean a) {
	}

	public void onClick() {
		clickAction.accept(true);
	}

	public void onHover() {
		hoverAction.accept(true);
	}

	public void onCall() {
		callAction.accept(true);
	}

	public static float widthRatio, heightRatio;

	protected float scale;
	protected float offsetX, offsetY;
	protected float baseX, baseY, baseWidth, baseHeight;
	public float x, y, width, height;
	private String anchor = Anchor.TopLeft; // Default anchor position
	public Sprite sprite;
	public Boolean isText = false;
	public String text;

	public Component(float X, float Y, float Width, float Height) {
		sprite = new Sprite("null");
		initialise(X, Y, Width, Height);
	}

	public Component(String ImagePath, float X, float Y, float Width, float Height) {
		sprite = new Sprite(ImagePath);
		initialise(X, Y, Width, Height);
	}

	public Component(List<String> ImagePaths, float X, float Y, float Width, float Height) {
		sprite = new Sprite(ImagePaths);
		initialise(X, Y, Width, Height);
	}

	public Component(SpriteSheet SpriteSheet, int SpriteID, float X, float Y, float Width, float Height) {
		sprite = new Sprite(SpriteSheet, SpriteID);
		initialise(X, Y, Width, Height);
	}

	protected void initialise(float X, float Y, float Width, float Height) {
		baseX = X;
		baseY = Y;
		baseWidth = Width;
		baseHeight = Height;

		scale = 1;
		offsetX = 0;
		offsetY = 0;

		updateResolution();
		updateSize();

		// Default anchor is TopLeft
		// (0, 0) is the top left of the screen for components bound to this anchor
		applyAnchor();
	}

	protected void initialise(float X, float Y, float Width, float Height, float Scale) {
		initialise(X, Y, Width, Height);
		scale = Scale;
	}

	// Called after resolution changes or after setters are used
	public void update() {
		updateResolution();
		updateSize();
		applyAnchor();
	}

	public static void updateResolution() {
		widthRatio = (float) Window.defaultWidth / Window.width;
		heightRatio = (float) Window.defaultHeight / Window.height;
	}

	// Calculate the size of the image so that it scales correctly throughout
	// resolution changes
	private void updateSize() {
		width = baseWidth * widthRatio;
		height = baseHeight * heightRatio;
	}

	// Changes the anchor point to the specified part of the window
	public void setAnchor(String anchorPoint) {
		anchor = anchorPoint;
	}

	private void applyAnchor() {
		// I don't know how to do this without using a switch statement
		// If only I could store functions within a HashMap, then the Anchor constants
		// could be keys and call the correct function simply by passing the Anchor
		// point to the HashMap
		// (Note: I haven't actually looked into whether or not doing the above is
		// possible)

		switch (anchor) {
			case Anchor.TopLeft:
				anchorTopLeft();
				break;
			case Anchor.Centre:
				anchorCentre();
				break;
			case Anchor.BottomCentre:
				anchorBottomCentre();
				break;
			case Anchor.TopRight:
				anchorTopRight();
				break;
			case Anchor.TopCentre:
				anchorTopCentre();
				break;
			case Anchor.BottomLeft:
				anchorBottomLeft();
				break;
			default:
				System.out.println("WARNING: Invalid anchor type, defaulting to TopLeft");
				anchorTopLeft();
				break;
		}
	}

	private void anchorTopLeft() {
		// Calculate where to tell LibGDX to draw things in order for them to
		// be drawn accurately throughout resolution changes
		float Y = Window.height - baseY - baseHeight;
		x = baseX * widthRatio;
		y = Y * heightRatio;
	}

	private void anchorCentre() {
		float X = baseX + (Window.width - baseWidth) / 2;
		float Y = baseY + (Window.height - baseHeight) / 2;
		x = X * widthRatio;
		y = Y * heightRatio;
	}

	private void anchorBottomCentre() {
		float X = baseX + (Window.width - baseWidth) / 2;
		float Y = baseY;
		x = X * widthRatio;
		y = Y * heightRatio;
	}

	private void anchorTopRight() {
		float X = Window.width - baseX - baseWidth;
		float Y = Window.height - baseY - baseHeight;
		x = X * widthRatio;
		y = Y * heightRatio;
	}

	private void anchorTopCentre() {
		float X = baseX + (Window.width - baseWidth) / 2;
		float Y = Window.height - baseY - baseHeight;
		x = X * widthRatio;
		y = Y * heightRatio;
	}

	private void anchorBottomLeft() {
		x = baseX * widthRatio;
		y = baseY * heightRatio;
	}

	public abstract void setClickAction(String action);

	public void setOffset(float X, float Y) {
		this.baseX = this.baseX - offsetX + X;
		this.baseY = this.baseY - offsetY + Y;
		offsetX = X;
		offsetY = Y;
		update();
	}

	public void setScale(float Scale) {
		float scaleRatio = Scale / scale;
		this.baseWidth = this.baseWidth * scaleRatio;
		this.baseHeight = this.baseHeight * scaleRatio;
		scale = Scale;
		update();
	}

	public void setImage(String ImagePath) {
		this.sprite.setImagePath(ImagePath);
	}

	public int left() {
		return (int) this.x;
	}

	public int right() {
		// TODO: Add some kind of workaround to get the correct positions for text
		return (int) (this.x + this.width);
	}

	public int top() {
		// TODO: ^^
		return (int) (this.y + this.height);
	}

	public int bottom() {
		return (int) this.y;
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

	public float getBaseX() {
		return this.baseX;
	}

	public float getBaseY() {
		return this.baseY;
	}

	public float getBaseWidth() {
		return this.baseWidth;
	}

	public float getBaseHeight() {
		return this.baseHeight;
	}

	public void setX(float x) {
		this.baseX = x;
		update();
	}

	public void setY(float y) {
		this.baseY = y;
		update();
	}

	public void setWidth(float width) {
		this.baseWidth = width;
		update();
	}

	public void setHeight(float height) {
		this.baseHeight = height;
		update();
	}
}
