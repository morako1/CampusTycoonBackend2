package CampusTycoon;

import CampusTycoon.UI.saveScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;

import CampusTycoon.UI.ScreenUtils;
import CampusTycoon.UI.StartScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    public static InputMultiplexer multiplexer;

    @Override
    public void create() {
		//Gdx.graphics.setForegroundFPS(60); // Useful function for settings menu later
        //Gdx.graphics.setContinuousRendering(false); // Interesting function to explore later
        //(^if rendering performance becomes an issue)
        System.out.println("Back to menu");
        Gdx.graphics.setTitle("Campus Tycoon");

        multiplexer = new InputMultiplexer();
        saveScreen.stage = new Stage(new ScreenViewport());

        Main.multiplexer = new InputMultiplexer();
        Main.multiplexer.addProcessor(saveScreen.stage);
        Main.multiplexer.addProcessor(new InputHandler());





        Gdx.input.setInputProcessor(Main.multiplexer);

		// Sets the screen to the Main Menu

		Screen screen = new StartScreen();
		ScreenUtils.currentScreen = screen;
		setScreen(screen);
	}

	@Override
	public void render () {
		if (screen != null) screen.render(Gdx.graphics.getDeltaTime());
		if (ScreenUtils.currentScreen != screen) { setScreen(ScreenUtils.currentScreen); }
	}
}
