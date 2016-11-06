package datastructures;

/**
 * Created by un4 on 05/11/16.
 */
public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        Coordinates coordinate = (Coordinates) obj;
        return coordinate.getX() == this.getX() || coordinate.getY() == this.getY();
    }

    @Override
    public String toString() {
        return "x: " + getX() + " y: " + getY();
    }
}
