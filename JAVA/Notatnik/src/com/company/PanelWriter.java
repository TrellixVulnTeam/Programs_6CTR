package com.company;

import javax.swing.*;
import java.awt.*;

public class PanelWriter extends JPanel {

    JTextArea poleDoPisanie;
    JScrollPane jScrollPane;
    public PanelWriter()
    {


        poleDoPisanie = new JTextArea(30,30);
        poleDoPisanie.setWrapStyleWord(true);
        poleDoPisanie.setLineWrap(true);
        poleDoPisanie.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        poleDoPisanie.setMargin(new Insets(5, 5, 5, 5));
        poleDoPisanie.setBackground(new Color(248, 255, 107));



        setVisible(true);

    }

}

