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

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class CampusTycoon extends Game {

    public static InputMultiplexer multiplexer;

    @Override
    public void create() {

        Gdx.graphics.setTitle("Campus Tycoon");

        //Assessment 2
        //Using a multiplexer to allow text input to work
        multiplexer = new InputMultiplexer();
        saveScreen.stage = new Stage(new ScreenViewport());

        CampusTycoon.multiplexer = new InputMultiplexer();
        CampusTycoon.multiplexer.addProcessor(saveScreen.stage);
        CampusTycoon.multiplexer.addProcessor(new InputHandler());


        Gdx.input.setInputProcessor(CampusTycoon.multiplexer);

        // Sets the screen to the Main Menu

        Screen screen = new StartScreen();
        ScreenUtils.currentScreen = screen;
        setScreen(screen);
    }

    @Override
    public void render() {
        if (screen != null) screen.render(Gdx.graphics.getDeltaTime());
        if (ScreenUtils.currentScreen != screen) {
            setScreen(ScreenUtils.currentScreen);
        }
    }
}
