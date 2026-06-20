
import java.awt.Color;

public class MazeFactory {
    public Wall makeWall(int x, int y, Directions direction, Color color) {
        return new Wall(x, y, direction, color);
    }
    
    public Room makeRoom(int x, int y, int nr) {
        return new Room(x, y, nr);
    }
    
    public Door makeDoor(Room r1, Room r2, Color color) {
        return new Door(r1, r2, color);
    }

    public Mine makeMine(int x, int y) {
        return new Mine(x, y, Color.RED);
    }
}