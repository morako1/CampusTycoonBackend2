package CampusTycoon.headless;

import CampusTycoon.GameLogic.Buildings.Cafeteria;
import CampusTycoon.GameLogic.Buildings.Relaxation;
import CampusTycoon.GameLogic.Buildings.Restaurant;
import CampusTycoon.GameLogic.Buildings.Study;
import CampusTycoon.GameLogic.Buildings.Accommodation;
import com.badlogic.gdx.Gdx;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssetTests extends AbstractHeadlessGdxTest {

    @BeforeEach
    public void setUp() {
        HeadlessLauncher.main(new String[0]);
    }

    @Test
    public void buildingAssets() {
        assertTrue(Gdx.files.internal(Restaurant.defaultImage).exists(), "The asset for the Restaurant exists");
        assertTrue(Gdx.files.internal(Cafeteria.defaultImage).exists(), "The asset for Cafeteria exists");
        assertTrue(Gdx.files.internal(Relaxation.defaultImage).exists(), "The asset for Relaxation exists");
        assertTrue(Gdx.files.internal(Study.defaultImage).exists(), "The asset for Study exists");
        assertTrue(Gdx.files.internal(Accommodation.defaultImage).exists(), "The Accommodation for Study exists");



    }

    @Test
    public void cafeteriaAsset() {
        assertTrue(Gdx.files.internal(Cafeteria.defaultImage).exists(), "The asset for Cafeteria exists");
    }




}
