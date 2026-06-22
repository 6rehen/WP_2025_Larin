
import java.awt.Color;

public class MagicMazeFactory extends MazeFactory {
    @Override
    public Room makeRoom(int x, int y, int nr) {
        return new Room(x, y, nr);
    }

    @Override
    public Wall makeWall(int x, int y, Directions direction, Color color) {
        // Ignorujemy przekazany kolor i nadpisujemy na fioletowy dla magicznych ścian
        return new Wall(x, y, direction, new Color(138, 43, 226)); // Fioletowy
    }
    
    @Override
    public Door makeDoor(Room r1, Room r2, Color color) {
        // Magiczne drzwi są pomarańczowe
        return new Door(r1, r2, Color.ORANGE);
    }
    
    @Override
    public Mine makeMine(int x, int y) {
        // Magiczne miny są niebieskie
        return new Mine(x, y, Color.BLUE);
    }
}