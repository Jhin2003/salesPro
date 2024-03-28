package ui;

import javax.swing.*;
import java.awt.*;

public class Home extends JPanel {
    JFrame frame;

    public Home(JFrame frame){
        this.frame = frame;

        setLayout(new BorderLayout());


        Records records = new Records(frame);
        add(records, BorderLayout.CENTER);

        JPanel sideTab = new SideTab(frame, records);
        add(sideTab, BorderLayout.WEST);
    }
}
