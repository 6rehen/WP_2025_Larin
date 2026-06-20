import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CalculatorView extends JFrame implements PropertyChangeListener {
    private JTextField display = new JTextField("0");
    private String[] labels = {
        "AC", "CE", "←", "/",
        "7",  "8",  "9",  "*",
        "4",  "5",  "6",  "-",
        "1",  "2",  "3",  "+",
        "0",  "=",  "",   ""
    };

    public CalculatorView(CalculatorModel model) {
        model.addPropertyChangeListener(this);

        setTitle("Kalkulator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));
        for (String text : labels) {
            if (text.isEmpty()) {
                panel.add(new JLabel(""));
            } else {
                JButton btn = new JButton(text);
                btn.setFont(new Font("Arial", Font.PLAIN, 18));
                btn.setActionCommand(text);
                panel.add(btn);
            }
        }
        add(panel, BorderLayout.CENTER);
    }

    public void addButtonListener(ActionListener listener) {
        for (Component c : getContentPane().getComponents()) {
            if (c instanceof JPanel) {
                for (Component b : ((JPanel) c).getComponents()) {
                    if (b instanceof JButton) {
                        ((JButton) b).addActionListener(listener);
                    }
                }
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if ("display".equals(e.getPropertyName())) {
            display.setText((String) e.getNewValue());
        }
    }
}
