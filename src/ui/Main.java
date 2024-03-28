package ui;



import javax.swing.*;
import java.awt.*;


import utils.GetScreenWidthAndHeight;


class Main extends JFrame{

     public Main() {

         setTitle("SalesPro Retailer");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         setSize(screenSize.width, screenSize.height);

         setExtendedState(JFrame.MAXIMIZED_BOTH);
         Login login = new Login(this);
         add(login);
         setVisible(true);

         new GetScreenWidthAndHeight(this).ShowScreenWidthAndHeight();
         //ImagePanel panel = new ImagePanel("src/assets/SalesProPic.png");
         //add(panel);

     }
    public static void main(String[] args){
         UIManagerSettings.setCustomUIManager();
         SwingUtilities.invokeLater(Main::new);
    }
}






