package CampusTycoon.UI.Components;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Gdx;

import static CampusTycoon.UI.Components.Leaderboard.leaderboardmap;
import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {

    @Test
    void addScoreToLeaderboard() throws IOException {
        Leaderboard.loadLeaderboard();
        String player = "Tester";
        int score = 10;
        int length = leaderboardmap.size();
        Leaderboard.updateScore(player, score);
        assertEquals(length + 1, leaderboardmap.size());


    }


}
