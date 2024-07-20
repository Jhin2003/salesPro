package ui;

import db.AdminLogin;
import utils.BCrypt;
import utils.PoppinsFontManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class LoginForm extends JPanel {
   JFrame frame;
    public LoginForm(JFrame frame){
        this.frame = frame;
        setLayout(new FlowLayout(FlowLayout.CENTER,40,40));
        setPreferredSize(new Dimension(550,1000));
        setBackground(Color.white);
        setOpaque(false);

        // admin label
        JLabel adminLabel = new JLabel("Admin Login");
        adminLabel.setBorder(new EmptyBorder(180, 0,0,0));
        PoppinsFontManager.applyPoppinsFont(adminLabel, true, 32);
        adminLabel.setForeground(Color.white);
        add(adminLabel);

        //username panel
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
        usernamePanel.setPreferredSize(new Dimension(400,60));
        usernamePanel.setBackground(new Color(84,35,146));
        //username label

        //username field
        JTextField usernameField = new JTextField("Username", 10);
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.black);


                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Username");
                    usernameField.setForeground(Color.GRAY);
                   ;


                }
            }
        });
        usernameField.setPreferredSize(new Dimension(340,40));
        usernameField.setBorder(null);
        PoppinsFontManager.applyPoppinsFont(usernameField, true, 20);
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 0));
        usernamePanel.add(usernameField);
        add(usernamePanel);

        //password panel
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordPanel.setPreferredSize(new Dimension(400,60));


        JTextField passwordField = new JPasswordField("Password", 10);

        passwordField.setForeground(Color.GRAY);


        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordField.getText().equals("Password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.black);


                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getText().isEmpty()){
                    passwordField.setText("Password");
                    passwordField.setForeground(Color.GRAY);
                }

            }
        });
        passwordField.setBorder(null);
        //add
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 0));
        PoppinsFontManager.applyPoppinsFont(passwordField, true, 20);
        passwordPanel.add(passwordField);
        add(passwordPanel);

        JPanel ActionsPanel = new JPanel();
        ActionsPanel.setOpaque(false);
        ActionsPanel.setPreferredSize(new Dimension(400,120));

        //login button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setPreferredSize(new Dimension(400,60));
        loginButton.setBackground(new Color(0,155,200));
        loginButton.setForeground(new Color(248,248,248));
        PoppinsFontManager.loadFonts("src/assets");
        PoppinsFontManager.applyPoppinsFont(loginButton, true, 20);
        //if success, remove login panel and add home panel
        loginButton.addActionListener(e -> {

            AdminLogin adminLogin = new AdminLogin();
            if(adminLogin.IsPasswordValidated(usernameField.getText(), passwordField.getText())){


                frame.getContentPane().removeAll();
                frame.add(new Home(frame));
                frame.revalidate();
                frame.repaint();
            }

            else{
                JOptionPane.showMessageDialog(
                        frame,
                        "Incorrect username or password",
                        "Wrong credentials",
                        JOptionPane.PLAIN_MESSAGE
                );
            }

        });

        ActionsPanel.add(loginButton);
        //add
        add(ActionsPanel);


    }


    //rounded panel and decreased opacity
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Color bgColor = getBackground();
        Color alphaColor = new Color(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), 140);
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(alphaColor);
        //int cornerRadius = 20;
        graphics.fillRoundRect(0, 0, width - 1, height - 1, 0, 0);
        graphics.dispose();
    }
}

