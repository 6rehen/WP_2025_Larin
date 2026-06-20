import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CalculatorModel {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    private String currentInput = "0";
    private String lastInput = "";
    private String operator = "";
    private boolean newInput = true;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void inputDigit(String digit) {
        if (newInput) {
            currentInput = digit;
            newInput = false;
        } else {
            currentInput = currentInput.equals("0") ? digit : currentInput + digit;
        }
        fireDisplayChange(currentInput);
    }

    public void inputOperator(String op) {
        lastInput = currentInput;
        operator = op;
        newInput = true;
    }

    public void calculate() {
        if (lastInput.isEmpty() || newInput) return;
        try {
            long n1 = Long.parseLong(lastInput);
            long n2 = Long.parseLong(currentInput);
            long result;
            switch (operator) {
                case "+": result = n1 + n2; break;
                case "-": result = n1 - n2; break;
                case "*": result = n1 * n2; break;
                case "/":
                    if (n2 == 0) throw new ArithmeticException();
                    result = n1 / n2; break;
                default: return;
            }
            currentInput = String.valueOf(result);
            lastInput = "";
            operator = "";
            newInput = true;
            fireDisplayChange(currentInput);
        } catch (ArithmeticException ex) {
            lastInput = "";
            operator = "";
            newInput = true;
            fireDisplayChange("Błąd");
        }
    }

    public void clearAll() {
        currentInput = "0";
        lastInput = "";
        operator = "";
        newInput = true;
        fireDisplayChange("0");
    }

    public void clearEntry() {
        currentInput = "0";
        newInput = true;
        fireDisplayChange("0");
    }

    public void backspace() {
        if (newInput) return;
        currentInput = currentInput.length() > 1
            ? currentInput.substring(0, currentInput.length() - 1)
            : "0";
        fireDisplayChange(currentInput);
    }

    private void fireDisplayChange(String value) {
        support.firePropertyChange("display", null, value);
    }
}
