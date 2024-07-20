package ui;

import utils.PoppinsFontManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Login extends JPanel {
    JFrame frame;


    //login panel
    public Login(JFrame frame) {

    this.frame = frame;
        setLayout(new BorderLayout());
        PoppinsFontManager.loadFonts("src/assets");



        ImageIcon icon = new ImageIcon("src/assets/salesprologo.png");
        JLabel salesproLogo = new JLabel(icon);
        add(salesproLogo, BorderLayout.CENTER);


        JPanel loginForm = new LoginForm(frame);

        add(loginForm,BorderLayout.EAST);
    }

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

