package ui;

import javax.swing.*;
import java.awt.*;

public class UIManagerSettings {

        public static void setCustomUIManager() {
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 20));
            UIManager.put("OptionPane.yesButtonText", "Yes");
            UIManager.put("OptionPane.noButtonText", "NO");
            // Add more UIManager settings as needed
        }


}
