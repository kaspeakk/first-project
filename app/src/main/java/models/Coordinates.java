package models;

public class Coordinates {
    private double x;
    private Float y;

    public Coordinates(double x, Float y) {
        if (y == null) {
            throw new IllegalArgumentException("Координата Y не может быть null");
        }
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public Float getY() { return y; }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}