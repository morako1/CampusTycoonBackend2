package CampusTycoon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TextInput extends Game {
    Stage stage;
    private SpriteBatch batch;


    public TextInput() {
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();

    }


    /**
     *
     */
    @Override
    public void create() {

    }

    public void render() {
        System.out.println("draw");
        float delta = Gdx.graphics.getDeltaTime();

        stage.act(delta);
        stage.draw();

        batch.begin();
        batch.draw(new Texture(Gdx.files.internal("Dollar.png")),10,10);
        batch.end();

    }
}
