
import java.awt.Color;

public abstract class MazeFactory {
    public abstract Wall makeWall(int x, int y, Directions direction, Color color);
    public abstract Room makeRoom(int x, int y, int nr);
    public abstract Door makeDoor(Room r1, Room r2, Color color);
    public abstract Mine makeMine(int x, int y);
}
