import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController implements ActionListener {
    private CalculatorModel model;

    public CalculatorController(CalculatorView view, CalculatorModel model) {
        this.model = model;
        view.addButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            model.inputDigit(command);
        } else if (command.matches("[+\\-*/]")) {
            model.inputOperator(command);
        } else if (command.equals("=")) {
            model.calculate();
        } else if (command.equals("←")) {
            model.backspace();
        } else if (command.equals("CE")) {
            model.clearEntry();
        } else if (command.equals("AC")) {
            model.clearAll();
        }
    }
}
