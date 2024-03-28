package ui.components;

import utils.PoppinsFontManager;

import javax.swing.*;
import java.awt.*;

public class GreenButton extends JButton{

   public GreenButton(String text){
       super(text);
       setBackground(new Color(6,191,25));
       setForeground(Color.white);
       setFocusPainted(false);
       PoppinsFontManager.applyPoppinsFont(this, true, 16);
       setBorderPainted(false);
   }


}
