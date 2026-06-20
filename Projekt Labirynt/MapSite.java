
import java.awt.*;

enum Directions {
    NORTH, EAST, SOUTH, WEST;
}

public abstract class MapSite {
    private int x;
    private int y;
    private Color color;
    public static int L =  50; // długość ściany
    public MapSite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MapSite(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {return color;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Rysowanie elementu labiryntu
     * @param image - obraz, na którym rysujemy
     */
    public abstract void draw(Image image);
}
