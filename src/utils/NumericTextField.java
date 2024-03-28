package utils;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class NumericTextField extends JTextField {
    public NumericTextField() {
        super();
        initialize();
    }

    public NumericTextField(int columns) {
        super(columns);
        initialize();
    }

    private void initialize() {
        this.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0' && c <= '9') || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public int getIntValue() {
        try {
            return Integer.parseInt(getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public double getDoubleValue() {
        try {
            return Double.parseDouble(getText());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}