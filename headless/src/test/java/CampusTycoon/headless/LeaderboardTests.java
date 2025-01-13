package CampusTycoon.headless;

import CampusTycoon.UI.Components.Leaderboard;
import com.badlogic.gdx.Gdx;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static CampusTycoon.UI.Components.Leaderboard.leaderboardmap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaderboardTests {

    @BeforeEach
    public void setUp() {
        HeadlessLauncher.main(new String[0]);
    }

    @Test
    void addScoreToLeaderboard() throws IOException {
        Leaderboard.loadLeaderboard();
        int length = leaderboardmap.size();
        Leaderboard.updateScore("Tester", 10);
        assertEquals(length + 1, leaderboardmap.size());
    }

}
