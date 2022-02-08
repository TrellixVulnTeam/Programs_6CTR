package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class PanelMenu extends JPanel {


    JMenuBar menuBar;
    JMenu menu;
    JMenu help;
    JMenuItem wczytaj;
    JMenuItem zapisz;
    JMenuItem oNas;
    JMenuItem instrukcja;



    public PanelMenu()
    {
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(47,79,79));


        menu = new JMenu("Plik");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("that only menu in this program that has menu items");
        menu.setForeground(Color.WHITE);

        zapisz = new JMenuItem("zapisz",KeyEvent.VK_T);
        zapisz.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        zapisz.getAccessibleContext().setAccessibleDescription("Tu możesz zapisać utworzony plik");

        menu.add(zapisz);

        wczytaj = new JMenuItem("wczytaj", KeyEvent.VK_B);
        wczytaj.getAccessibleContext().setAccessibleDescription("Tu możesz wczytać plik z komputera");
        wczytaj.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        menu.add(wczytaj);

        help = new JMenu("Pomoc");
        help.setMnemonic(KeyEvent.VK_H);
        help.getAccessibleContext().setAccessibleDescription("Tutaj uzyskasz pomoc");
        help.setForeground(Color.WHITE);

        instrukcja = new JMenuItem("Instrukcja");
        help.add(instrukcja);

        oNas = new JMenuItem("O nas");
        help.add(oNas);

        menuBar.add(menu);
        menuBar.add(help);
        setLayout(null);
        setVisible(true);


    }


}

