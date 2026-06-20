
import java.awt.*;

public class Room extends MapSite {

    private int nr; // numer pokoju
    private MapSite[] sites = new MapSite[4];
    private Mine mine;

    public Room(int x, int y, int nr) {
        super(x, y, Color.BLACK);
        this.nr = nr;
    }

    public void setMine(Mine mine) {
        this.mine = mine;
    }

    public void setSite(Directions d, MapSite site) {
        if (site instanceof Wall) {
            switch (d) {
                case NORTH, WEST: site.setX( getX() );
                                  site.setY( getY() );
                                  break;
                case SOUTH: site.setX( getX() );
                            site.setY( getY() + L);
                            break;
                default: site.setX( getX() + L);
                         site.setY( getY());
            }
            ((Wall) site).setDirection(d);
        }
        sites[d.ordinal()] = site;
    }

    @Override
    public void draw(Image image) {
        for (MapSite site : sites) {
            if (site != null)
                site.draw(image);
        }
        
        if (mine != null) {
            mine.draw(image);
        }

        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.drawString("" + nr, getX() + 5, getY() + 15);
    }
}