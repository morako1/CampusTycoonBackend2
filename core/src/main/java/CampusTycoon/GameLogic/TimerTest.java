package CampusTycoon.GameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerTest {

    @Test
    void updateShouldDecreaseTimeRemaining() {
        Timer timer = new Timer(60);
        timer.start();
        timer.update(0.5f);
        assertEquals(59.5f, Timer.getTimeRemaining());
    }

    @Test
    void updateShouldEndWhenTimeUp() {
        Timer timer = new Timer(0.5f);
        timer.start();
        timer.update(0.5f);
        assertTrue(timer.hasEnded());
    }

    @Test
    void updateShouldNotChangeTimeRemainingWhenPaused() {
        Timer timer = new Timer(60);
        timer.start();
        Timer.pause();
        timer.update(0.5f);
        assertEquals(60.0f, Timer.getTimeRemaining(), 0.00001);
    }

    @Test
    void updateShouldNotEndWhenPausedAndTimeUp() {
        Timer timer = new Timer(0.5f);
        timer.start();
        Timer.pause();
        timer.update(0.5f);
        assertFalse(timer.hasEnded());
    }
}
