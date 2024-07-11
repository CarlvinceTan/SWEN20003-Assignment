package Utilities;

/**
 * Class that represents a point in the game screen.
 */
public class Point {

    /** The x-coordinate of the point. */
    public double x;

    /** The y-coordinate of the point. */
    public double y;

    /**
     * Constructs a new Point object with the specified coordinates.
     *
     * @param x The x-coordinate of the point
     * @param y The y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between two points.
     *
     * @param point1 The first point
     * @param point2 The second point
     * @return The distance between the two points
     */
    private static double calculateDistance(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
    }

    /**
     * Checks if two points are within a certain radius of each other.
     *
     * @param point1 The first point
     * @param point2 The second point
     * @param distance The maximum distance between the points
     * @return True if the points are within the specified radius, otherwise false
     */
    public static boolean withinRadius(Point point1, Point point2, double distance) {
        return distance >= calculateDistance(point1, point2);
    }
}
