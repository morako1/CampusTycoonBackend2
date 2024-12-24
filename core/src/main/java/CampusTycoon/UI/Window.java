package CampusTycoon.UI;

public class Window {
	public static int defaultWidth = 1280; // Treat as a constant
	public static int defaultHeight = 720; // Do not ask me why they need to be specifically these values but just know that they do otherwise everything breaks
	public static int width = defaultWidth;
	public static int height = defaultHeight;
	
	public static void updateResolution(int ScreenWidth, int ScreenHeight) {
		width = ScreenWidth;
		height = ScreenHeight;
		Component.updateResolution();
	}
}