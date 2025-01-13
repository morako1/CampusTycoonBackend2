package CampusTycoon.GameLogic;

// A simple class to enable easier passing of coordinate pairs (commonly needed for spritesheets)
public class CoordinatePair {
    public Coordinate start, end;

    public CoordinatePair() {
        start = new Coordinate();
        end = new Coordinate();
    }

    public CoordinatePair(Coordinate Start, Coordinate End) {
        start = Start;
        end = End;
    }
}
