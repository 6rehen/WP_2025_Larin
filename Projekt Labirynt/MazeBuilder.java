
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeBuilder extends JFrame {
    private JMyPanel panel; // panel, na którym rysujemy labirynt
    private Image image; // obiekt, na którym rysujemy labirynt

    public MazeBuilder() {
        setSize(800, 600);
        panel = new JMyPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // określamy sposób zamykania okna
        setLayout( new BorderLayout() ); 
        
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(1, 2));
        
        JButton btnStandard = new JButton("Draw Standard Maze");
        btnStandard.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawMaze(100, 100);
                drawMaze2(300, 100, new StandardMazeFactory());
            }
        });

        JButton btnMagic = new JButton("Draw Magic Maze");
        btnMagic.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawMaze2(300, 300, new MagicMazeFactory()); 
            }
        });

        panelButtons.add(btnStandard);
        panelButtons.add(btnMagic);
        add(panelButtons, BorderLayout.NORTH);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * "Ręczne" rysowanie labiryntu
     * @param x
     * @param y
     */
    public void drawMaze(int x, int y) {
        image = panel.getImage();
        Graphics g = image.getGraphics();
        int L = MapSite.L;  // długość ściany
        g.drawLine(x, y, x + L, y); // poziom góra
        g.drawLine(x, y + L, x + L, y + L); // poziom dół
        g.drawLine(x, y, x, y + L); // pion lewy
        g.drawLine(x + L, y, x + L, y + L / 3); // drzwi po prawej, górna część
        g.drawLine(x + L, y + 2 * L / 3, x + L, y + L); // drzwi po prawej, dolna część

        x = x + L;
        g.drawLine(x, y, x + L, y); // poziom góra
        g.drawLine(x, y + L, x + L, y + L); // poziom dół
        g.drawLine(x + L, y, x + L, y + L); // pion prawy
        g.drawLine(x, y, x, y + L / 3); // drzwi po lewej, górna część
        g.drawLine(x, y + 2 * L / 3, x, y + L); // drzwi po lewej, dolna część

        panel.repaint();   // odrysowanie obrazu na panelu
    }

    /**
     * Testowanie klas Wall, Door, Room
     */
    public void drawMaze2(int x, int y, MazeFactory factory) {
        image = panel.getImage();
        
        Wall w1 = factory.makeWall(x, y, Directions.NORTH, Color.GREEN);
        Color color = Color.BLACK;
        int nr = 1;
        
        Room r1 = factory.makeRoom(x, y, nr++);
        r1.setSite(Directions.WEST, w1);
        r1.setSite(Directions.NORTH, factory.makeWall(x, y, Directions.NORTH, color));
        r1.setSite(Directions.SOUTH, factory.makeWall(x, y, Directions.NORTH, color));
        r1.setSite(Directions.EAST, factory.makeWall(x, y, Directions.NORTH, color));

        Room r2 = factory.makeRoom(x + MapSite.L, y, nr++);
        r2.setSite(Directions.NORTH, factory.makeWall(x, y, Directions.NORTH, color));
        r2.setSite(Directions.EAST, factory.makeWall(x, y, Directions.NORTH, color));
        r2.setSite(Directions.SOUTH, factory.makeWall(x, y, Directions.NORTH, color));
        r2.setSite(Directions.WEST, factory.makeWall(x, y, Directions.NORTH, color));

        Room r3 = factory.makeRoom(x + MapSite.L, y + MapSite.L, nr++);
        r3.setSite(Directions.NORTH, factory.makeWall(x, y, Directions.NORTH, color));
        r3.setSite(Directions.EAST, factory.makeWall(x, y, Directions.NORTH, color));
        r3.setSite(Directions.SOUTH, factory.makeWall(x, y, Directions.NORTH, color));
        r3.setSite(Directions.WEST, factory.makeWall(x, y, Directions.NORTH, color));

        r3.setMine(factory.makeMine(r3.getX(), r3.getY()));

        Room r4 = factory.makeRoom(x + 2 * MapSite.L, y, nr++);
        r4.setSite(Directions.NORTH, factory.makeWall(x, y, Directions.NORTH, color));
        r4.setSite(Directions.EAST, factory.makeWall(x, y, Directions.NORTH, color));
        r4.setSite(Directions.SOUTH, factory.makeWall(x, y, Directions.NORTH, color));
        r4.setSite(Directions.WEST, factory.makeWall(x, y, Directions.NORTH, color));

        r4.setMine(factory.makeMine(r4.getX(), r4.getY()));

        r1.draw(image);
        r2.draw(image);
        r3.draw(image);
        r4.draw(image);

        panel.repaint();   // odrysowanie obrazu na panelu
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MazeBuilder().setVisible(true);  // tworzymy i wyświetlamy aplikację na ekranie
            }
        });

    }
}