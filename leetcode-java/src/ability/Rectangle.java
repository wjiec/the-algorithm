package ability;

public class Rectangle {

    public record Coordinate(int x, int y) {}

    private final Coordinate bottomLeft, topRight;
    public Rectangle(int bottomLeftX, int bottomLeftY, int topRightX, int topRightY) {
        this.bottomLeft = new Coordinate(bottomLeftX, bottomLeftY);
        this.topRight = new Coordinate(topRightX, topRightY);
    }

    public Rectangle intersection(Rectangle rect) {
        int bottomLeftX = Math.max(this.bottomLeft.x, rect.bottomLeft.x);
        int bottomLeftY = Math.max(this.bottomLeft.y, rect.bottomLeft.y);
        int topRightX = Math.min(this.topRight.x, rect.topRight.x);
        int topRightY = Math.min(this.topRight.y, rect.topRight.y);
        if (topRightX - bottomLeftX < 0 || topRightY - bottomLeftY < 0) return null;
        return new Rectangle(bottomLeftX, bottomLeftY, topRightX, topRightY);
    }

}
