package ui;

import javax.swing.*;
import java.awt.*;

public class Records extends JPanel {
    JFrame frame;
  CardLayout cardLayout;

  static String currentCard;

    JPanel customersPanel;
    JPanel salesrepPanel;

    JPanel productsPanel;

    JPanel discountsPanel;
    public  Records(JFrame frame){
        this.frame = frame;

        cardLayout = new CardLayout();
         setLayout(cardLayout);


         customersPanel = new Customers(frame, this);
        add(customersPanel, "Customers");

        productsPanel = new Products(frame,this);
        add(productsPanel, "Products");

        salesrepPanel = new Salesreps(frame, this);
        add(salesrepPanel, "Salesreps");

        discountsPanel = new Discounts(frame, this);
        add(discountsPanel, "Discounts");


        cardLayout.show(this, "products");
    }

    public void showPanel(String panelName) {
        cardLayout.show(this, panelName);
    }


}
