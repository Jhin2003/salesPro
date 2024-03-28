package ui.components;

import utils.PoppinsFontManager;

import javax.swing.*;
import java.awt.*;

public class RedButton extends JButton {

   public RedButton(String text){
       super(text);
        setBackground(new Color(237,8,0));
       setForeground(Color.white);
       setFocusPainted(false);
       PoppinsFontManager.applyPoppinsFont(this, true, 16);
       setBorderPainted(false);
    }
}
