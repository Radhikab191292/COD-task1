import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    public VisualCalculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

       
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(Color.DARK_GRAY);

        
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "Del"," "," "
        };

       
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.setBackground(new Color(173, 216, 230)); // Light blue color
            button.setOpaque(true);
            button.setBorderPainted(false);
            if (!text.isEmpty()) {
                button.addActionListener(this);
            }
            panel.add(button);
        }

        
        add(panel, BorderLayout.CENTER);

        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            if (isOperatorClicked) {
                display.setText("");
                isOperatorClicked = false;
            }
            display.setText(display.getText() + command);
        } else if (command.equals("C")) {
            display.setText("");
        } else if (command.equals("Del")) {
            String currentText = display.getText();
            if (currentText.length() > 0) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            firstNumber = Double.parseDouble(display.getText());
            operator = command;
            isOperatorClicked = true;
        } else if (command.equals("=")) {
            secondNumber = Double.parseDouble(display.getText());
            double result = calculate(firstNumber, secondNumber, operator);
            display.setText(String.valueOf(result));
            isOperatorClicked = true;
        }
    }

    private double calculate(double num1, double num2, String op) {
        switch (op) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            case "/": 
                if (num2 != 0) return num1 / num2;
                else {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
            default: return 0;
        }
    }

    public static void main(String[] args) {
        new VisualCalculator();
    }
}

