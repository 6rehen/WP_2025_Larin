
import java.awt.*;

public class Mine extends MapSite {

    public Mine(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public void draw(Image image) {
        Graphics g = image.getGraphics();
        g.setColor(getColor());
        
        // Rysujemy minę na środku pokoju
        int offset = L / 4; 
        int size = L / 2;
        g.fillOval(getX() + offset, getY() + offset, size, size);
    }
}