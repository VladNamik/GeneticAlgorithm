package algorithm;

/**
 * The smallest functional unit
 * Dot with coordinates
 */
public class City {
    private double x;
    private double y;
    private int id;

    public City(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public City(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public City() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
