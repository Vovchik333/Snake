package objects;

public class BodyPart {
    private double x;
    private double y;
    private final double size = 10;

    public BodyPart(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getSize() {
        return size;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
