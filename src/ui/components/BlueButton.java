package ui.components;

import utils.PoppinsFontManager;

import javax.swing.*;
import java.awt.*;

public class BlueButton extends JButton{
        public BlueButton(String text){
            super(text);
            setBackground(new Color(10,33,245));
            setForeground(Color.white);
            setFocusPainted(false);
            PoppinsFontManager.applyPoppinsFont(this, true, 16);
            setBorderPainted(false);
        }

}
