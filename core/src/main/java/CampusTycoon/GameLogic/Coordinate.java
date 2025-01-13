package CampusTycoon.GameLogic;

// A simple class to facilitate easier usage of 2D coordinates (think of it like a struct from C#)
public class Coordinate {
    public int x, y;

    public Coordinate() {
        x = 0;
        y = 0;
    }

    public Coordinate(int X, int Y) {
        x = X;
        y = Y;
    }

    public double distance(Coordinate point) {
        double x = point.x - this.x;
        double y = point.y - this.y;
        return Math.sqrt(x * x + y * y);
    }
}
