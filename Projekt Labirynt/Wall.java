
import java.awt.*;

public class Wall extends MapSite {
    private Directions direction; // kierunek

    public Wall(int x, int y, Directions direction,  Color color) {
        super(x, y, color);
        this.direction = direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    @Override
    public void draw(Image image) {
        Graphics g = image.getGraphics();
        int x = getX();
        int y = getY();  // współrzędne
        g.setColor( getColor() );
        switch(direction) {
           case NORTH, SOUTH -> {g.drawLine(x, y, x + L, y);} // sciany poziome
           default -> {g.drawLine(x, y, x, y + L);} // ściany pionowe
       }
    }
}
