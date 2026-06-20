public class Main {
    public static void main(String[] args) {
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView(model);
        new CalculatorController(view, model);

        view.setVisible(true);
    }
}
