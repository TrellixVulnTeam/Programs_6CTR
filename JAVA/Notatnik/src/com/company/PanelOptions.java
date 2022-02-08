package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.StyledEditorKit;
import java.awt.*;

public class PanelOptions extends JPanel {
    //Style elements

    Border line = new LineBorder(Color.BLACK);
    Color buttonFontColor = new Color(255,255,255);
    Color buttonBackgroundColor = new Color(47,79,79);
    Dimension buttonSize1 = new Dimension(60,25);
    Dimension buttonSize2 = new Dimension(25,25);


    JButton boild;
    JButton cursive;
    JButton clear;
    JButton color;
    JButton backgroundColor;
    JButton putEmoji;
    JLabel fontLabel;
    JComboBox font;
    JComboBox emoji;
    String [] emojis = {new String(Character.toChars(10084)), new String(Character.toChars(128515)),
            new String(Character.toChars(128523)),
            new String(Character.toChars(128514)),
            new String(Character.toChars(128517)),
            new String(Character.toChars(128531)),
            new String(Character.toChars(128527)),
            new String(Character.toChars(128521)),
            new String(Character.toChars(128536)),
            new String(Character.toChars(128555))};

    String [] fonts = {"Dialog","DialogInput","Monospaced","Serif","SansSerif"};

    JComboBox fontSize;
    String [] fontSizeValue = new String[40];


    PanelOptions()
    {
        boild = new JButton("B");
        cursive = new JButton("I");
        clear = new JButton("wyczyść");
        color = new JButton("kolor");
        backgroundColor = new JButton("tło");
        font = new JComboBox(fonts);
        fontLabel = new JLabel("czcionka");
        emoji = new JComboBox(emojis);
        putEmoji = new JButton("wstaw emoji");

        //styles

        boild.setBorder(null);
        cursive.setBorder(null);
        clear.setBorder(null);
        color.setBorder(null);
        backgroundColor.setBorder(null);
        font.setBorder(null);
        emoji.setBorder(null);
        putEmoji.setBorder(null);

        boild.setForeground(buttonFontColor);
        cursive.setForeground(buttonFontColor);
        clear.setForeground(buttonFontColor);
        color.setForeground(buttonFontColor);
        backgroundColor.setForeground(buttonFontColor);
        font.setForeground(Color.black);
        emoji.setForeground(Color.black);
        putEmoji.setForeground(buttonFontColor);

        boild.setBackground(buttonBackgroundColor);
        cursive.setBackground(buttonBackgroundColor);
        clear.setBackground(buttonBackgroundColor);
        color.setBackground(buttonBackgroundColor);
        backgroundColor.setBackground(buttonBackgroundColor);
        font.setBackground(Color.white);
        emoji.setBackground(Color.white);
        putEmoji.setBackground(buttonBackgroundColor);

        boild.setPreferredSize(buttonSize2);
        cursive.setPreferredSize(buttonSize2);
        clear.setPreferredSize(buttonSize1);
        color.setPreferredSize(buttonSize1);
        backgroundColor.setPreferredSize(buttonSize1);
        font.setPreferredSize(buttonSize1);
        emoji.setPreferredSize(buttonSize1);
        putEmoji.setPreferredSize(buttonSize1);


        for (int i=1; i<40; i++)
        {
            fontSizeValue[i] = i+"";
        }

        fontSize = new JComboBox(fontSizeValue);
        fontSize.setBorder(null);
        fontSize.setForeground(Color.black);
        fontSize.setBackground(Color.white);
        fontLabel.setForeground(Color.WHITE);
        fontSize.setSelectedItem("25");


        add(putEmoji);
        add(emoji);
        add(boild);
        add(cursive);
        add(clear);
        add(color);
        add(backgroundColor);
        add(fontLabel);
        add(font);
        add(fontSize);
        setBackground(new Color(47,79,79));

        setVisible(true);
    }


//    button.addMouseListener(new java.awt.event.MouseAdapter() {
//        public void mouseEntered(java.awt.event.MouseEvent evt) {
//            jButton1.setBackground(Color.GREEN);
//        }
//
//        public void mouseExited(java.awt.event.MouseEvent evt) {
//            jButton1.setBackground(UIManager.getColor("control"));
//        }
//    });




}

