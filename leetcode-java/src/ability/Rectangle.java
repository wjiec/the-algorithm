package ability;

public class Rectangle {

    public record Coordinate(int x, int y) {}

    private final Coordinate topLeft, bottomRight;
    public Rectangle(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        this.topLeft = new Coordinate(topLeftX, topLeftY);
        this.bottomRight = new Coordinate(bottomRightX, bottomRightY);
    }

}
