
import java.awt.*;

public class Door extends MapSite {

    private Room roomOne;
    private Room roomTwo;
    private Directions direction;

    public Door(Room room1, Room room2, Color color) {
        super(-1,-1, color);
        roomOne = room1;
        roomTwo = room2;

        int x1 = room1.getX();
        int y1 = room1.getY();
        int x2 = room2.getX();
        int y2 = room2.getY();

        if (x1 == x2) { // pokoje leżą w pionie
            setX(x1);
            if (y2 > y1) {
                setY(y2);
                roomOne.setSite(Directions.SOUTH, this);
                roomTwo.setSite(Directions.NORTH, this);
            }
            else {
                setY(y1);
                roomTwo.setSite(Directions.SOUTH, this);
                roomOne.setSite(Directions.NORTH, this);
            }
            direction = Directions.NORTH;
        }
        else { // pokoje leżą w poziomie
            setY(y1);
            if (x2 > x1) {
                setX(x2);
                roomOne.setSite(Directions.EAST, this);
                roomTwo.setSite(Directions.WEST, this);
            }
            else {
                setX(x1);
                roomTwo.setSite(Directions.EAST, this);
                roomOne.setSite(Directions.WEST, this);
            }
            direction = Directions.EAST;
        }
    }

    @Override
    public void draw(Image image) {
        Graphics g = image.getGraphics();
        int x = getX();
        int y = getY();  // współrzędne
        g.setColor( getColor() );
        switch(direction) {
            case NORTH, SOUTH -> {
                g.drawLine(x, y, x + L/3, y);
                g.drawLine(x + 2 * L / 3, y, x + L, y);
            } // sciany poziome
            default -> {
                g.drawLine(x, y, x, y + L/3);
                g.drawLine(x, y + 2 * L / 3, x, y + L);
            }
        }


    }
}
