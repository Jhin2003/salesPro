package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import utils.PoppinsFontManager;

public class SideTab extends JPanel {
       JFrame frame;
       Records records;
     public SideTab(JFrame frame, Records records){
         this.frame = frame;
         this.records = records;

         setLayout(new FlowLayout());
         setBackground(Color.BLUE);
         setPreferredSize(new Dimension(500, 350));

         //salespro label
         JLabel salesproLabel = new JLabel("SalesPro Retailer");
         salesproLabel.setPreferredSize(new Dimension(500, 120));
         salesproLabel.setBorder(new EmptyBorder(60, 30, 20, 0));
         salesproLabel.setForeground(new Color(248,248,248));
         PoppinsFontManager.loadFonts("src/assets");
         PoppinsFontManager.applyPoppinsFont(salesproLabel, true, 36);
         add(salesproLabel);

         JPanel productsSelectionPanel = new JPanel();
         productsSelectionPanel.setLayout(new BoxLayout(productsSelectionPanel, BoxLayout.X_AXIS));
         productsSelectionPanel.setPreferredSize(new Dimension(500,60));
         productsSelectionPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
         productsSelectionPanel.setOpaque(false);

         ImageIcon icon = new ImageIcon("src/assets/products.png");
         JLabel productsIcon = new JLabel(icon);
         productsIcon.setPreferredSize(new Dimension(30, 30));
         productsSelectionPanel.add(productsIcon);



         JButton productsLabel = new JButton("Products");
         productsLabel.setHorizontalAlignment(SwingConstants.LEFT);
         productsLabel.setBorderPainted(false);
         productsLabel.setFocusPainted(false);
         productsLabel.setContentAreaFilled(false);
         productsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
         productsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
         productsLabel.setPreferredSize(new Dimension(400, 80));
         productsLabel.setFont(new Font("Arial", Font.PLAIN, 28));
         PoppinsFontManager.applyPoppinsFont(productsLabel, false, 24);
         productsLabel.setForeground(new Color(248,248,248));
         productsLabel.addActionListener(e -> records.showPanel("Products"));
         productsSelectionPanel.add(productsLabel);
         add(productsSelectionPanel);




         JPanel customersSelectionPanel = new JPanel();
         customersSelectionPanel.setLayout(new BoxLayout(customersSelectionPanel, BoxLayout.X_AXIS));
         customersSelectionPanel.setPreferredSize(new Dimension(500,60));
         customersSelectionPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
         customersSelectionPanel.setOpaque(false);

         icon = new ImageIcon("src/assets/customers.png");
         JLabel customersIcon = new JLabel(icon);
         customersSelectionPanel.add(customersIcon);


         JButton customersLabel = new JButton("Customers");
         customersLabel.setHorizontalAlignment(SwingConstants.LEFT);
         customersLabel.setBorderPainted(false);
         customersLabel.setFocusPainted(false);
         customersLabel.setContentAreaFilled(false);
         customersLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
         customersLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
         customersLabel.setPreferredSize(new Dimension(400, 80));
         customersLabel.setFont(new Font("Arial", Font.PLAIN, 24));
         PoppinsFontManager.applyPoppinsFont(customersLabel, false, 24);
         customersLabel.setForeground(new Color(248,248,248));
         customersLabel.addActionListener(e -> records.showPanel("Customers"));
         customersSelectionPanel.add(customersLabel);
         add(customersSelectionPanel);

         JPanel salesrepsSelectionPanel = new JPanel();
         salesrepsSelectionPanel.setLayout(new BoxLayout(salesrepsSelectionPanel, BoxLayout.X_AXIS));
         salesrepsSelectionPanel.setPreferredSize(new Dimension(500,60));
         salesrepsSelectionPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
         salesrepsSelectionPanel.setOpaque(false);


         icon = new ImageIcon("src/assets/salesreps.png");
         JLabel salesrepsIcon = new JLabel(icon);
         salesrepsSelectionPanel.add(salesrepsIcon);

         JButton salesrepresentativesLabel = new JButton("Sales Representatives");
          salesrepresentativesLabel.setHorizontalAlignment(SwingConstants.LEFT);
          salesrepresentativesLabel.setBorderPainted(false);
          salesrepresentativesLabel.setFocusPainted(false);
          salesrepresentativesLabel.setContentAreaFilled(false);
          salesrepresentativesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
          salesrepresentativesLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
          salesrepresentativesLabel.setPreferredSize(new Dimension(400, 80));
          salesrepresentativesLabel.setFont(new Font("Arial", Font.PLAIN, 24));
         PoppinsFontManager.applyPoppinsFont( salesrepresentativesLabel, false, 24);
          salesrepresentativesLabel.setForeground(new Color(248,248,248));

          salesrepresentativesLabel.addActionListener(e -> records.showPanel("Salesreps"));
          salesrepsSelectionPanel.add(salesrepresentativesLabel);
          add(salesrepsSelectionPanel);


         JPanel discountsSelectionPanel= new JPanel();
         discountsSelectionPanel.setLayout(new BoxLayout(discountsSelectionPanel, BoxLayout.X_AXIS));
         discountsSelectionPanel.setPreferredSize(new Dimension(500,60));
         discountsSelectionPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
         discountsSelectionPanel.setOpaque(false);

         icon = new ImageIcon("src/assets/discounts.png");
         JLabel discountsIcon = new JLabel(icon);
         discountsSelectionPanel.add(discountsIcon);


         JButton discountsLabel= new JButton("Discounts and Promotions");
         discountsLabel.setHorizontalAlignment(SwingConstants.LEFT);
         discountsLabel.setBorderPainted(false);
         discountsLabel.setFocusPainted(false);
         discountsLabel.setContentAreaFilled(false);
         discountsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
         discountsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
         discountsLabel.setPreferredSize(new Dimension(400, 80));
         discountsLabel.setFont(new Font("Arial", Font.PLAIN, 24));
         PoppinsFontManager.applyPoppinsFont(discountsLabel, false, 24);
         discountsLabel.setForeground(new Color(248,248,248));

         discountsLabel.addActionListener(e -> records.showPanel("Discounts"));
         discountsSelectionPanel.add(discountsLabel);
         add(discountsSelectionPanel);

     }

    //gradient background color
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        Color startColor = new Color(110,49,186);
        Color endColor = new Color(4,102,137);
        GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, 0, panelHeight, endColor);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, panelWidth, panelHeight);
        g2d.dispose();
    }
}


