package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ramka extends JFrame implements ActionListener, ItemListener {

    PanelWriter panelWriter;
    PanelOptions panelOptions;
    PanelMenu panelMenu;
    JScrollPane sp;
    String fileName = "";
    String filePath = "";
    boolean boildFont = false;
    boolean cursiveFont = false;
    String currentFont = "";
    PrintWriter pw;
    Object[] options = {"Tak", "Nie"};
    int curs, bol;
    int fSize = 25;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double screenHeight = screenSize.getHeight();
    double screenWidth = screenSize.getWidth();

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setBoildFont(boolean boildFont) {
        this.boildFont = boildFont;
    }

    public void setCursiveFont(boolean cursiveFont) {
        this.cursiveFont = cursiveFont;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public Ramka()
    {
        setTitle("Notatnik++");

        panelWriter = new PanelWriter();
        panelMenu = new PanelMenu();
        panelOptions = new PanelOptions();


        panelMenu.zapisz.addActionListener(this);
        panelMenu.wczytaj.addActionListener(this);
        panelMenu.instrukcja.addActionListener(this);
        panelMenu.oNas.addActionListener(this);
        panelOptions.boild.addActionListener(this);
        panelOptions.cursive.addActionListener(this);
        panelOptions.clear.addActionListener(this);
        panelOptions.color.addActionListener(this);
        panelOptions.font.addActionListener(this);
        panelOptions.backgroundColor.addActionListener(this);
        panelOptions.putEmoji.addActionListener(this);
        sp = new JScrollPane(panelWriter.poleDoPisanie);

        panelOptions.fontSize.addItemListener(this);

        panelWriter.poleDoPisanie.setFont(new Font(currentFont,bol + curs,fSize));

        setJMenuBar(panelMenu.menuBar);
        add(BorderLayout.CENTER, sp);
        add(BorderLayout.SOUTH, panelOptions);


        setSize((int) (screenWidth/2), (int) (screenHeight/2));
        setLocation((int) (screenWidth/3), (int) (screenHeight/5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource() == panelOptions.clear)
        {
            panelWriter.poleDoPisanie.setText("");

        }

        if(e.getSource() == panelOptions.boild)
        {
            if(boildFont == false && cursiveFont == false)
            {
                setBoildFont(true);
                bol = 1;
            }else if(boildFont == true && cursiveFont == false)
            {
                bol = 0;
                setBoildFont(false);
            }else if(boildFont == false && cursiveFont == true)
            {
                setBoildFont(true);
                bol = 1;
            }else if(boildFont == true && cursiveFont == true)
            {
                setBoildFont(false);
                bol = 0;
            }
        }


        if(e.getSource() == panelOptions.cursive)
        {
            if(cursiveFont == false && boildFont == false )
            {
                setCursiveFont(true);
                curs = 2;
            }else if(cursiveFont == true && boildFont == false)
            {
                setCursiveFont(false);
                curs = 0;
            }else if(cursiveFont == false && boildFont == true )
            {
                setCursiveFont(true);
                curs = 2;
            }else if(cursiveFont == true && boildFont == true)
            {
                setCursiveFont(false);
                curs = 0;
            }
        }
        if(e.getSource() == panelOptions.color)
        {

            Color color = JColorChooser.showDialog(this,"Wybierz kolor czcionki", panelWriter.getBackground());
            if(color != null)
            {
                System.out.println("jest kolor" + color);
                panelWriter.poleDoPisanie.setForeground(color);
            }

        }
        if(e.getSource() == panelOptions.backgroundColor)
        {
            Color color = JColorChooser.showDialog(this, "Wybierz kolor tła", panelWriter.getBackground());
            if(color != null)
            {
                panelWriter.poleDoPisanie.setBackground(color);

            }
        }

// Wybór emotki


        if(e.getSource() == panelOptions.putEmoji)
        {
            String emo = (String) panelOptions.emoji.getSelectedItem();

            panelWriter.poleDoPisanie.append(emo);

        }





// Wybór fonta
        String s = (String) panelOptions.font.getSelectedItem();

        switch(s){
            case "Dialog":
                System.out.println("Wybrałeś Dialog");
                currentFont = "Dialog";
                System.out.println(currentFont);
                panelWriter.poleDoPisanie.setFont(new Font(currentFont, bol + curs,fSize));
                System.out.println("STAN BOLD " + boildFont + "STAN CURS "+ cursiveFont );

                break;
            case "DialogInput":
                System.out.println("Wybrałeś DialogInput");
                currentFont = "DialogInput";
                System.out.println(currentFont);
                panelWriter.poleDoPisanie.setFont(new Font(currentFont, bol + curs,fSize));
                System.out.println("STAN BOLD " + boildFont + "STAN CURS "+ cursiveFont );
                break;
            case "Monospaced":
                System.out.println("Wybrałeś Monospaced");
                currentFont = " - 8\\u1d00.\\u1d0d.";
                System.out.println(currentFont);
                panelWriter.poleDoPisanie.setFont(new Font(currentFont, bol + curs,fSize));
                System.out.println("STAN BOLD " + boildFont + "STAN CURS "+ cursiveFont );
                break;
            case "Serif":
                System.out.println("Wybrałeś Serif");
                currentFont = "Serif";
                System.out.println(currentFont);
                panelWriter.poleDoPisanie.setFont(new Font(currentFont, bol + curs,fSize));
                break;
            case "SansSerif":
                System.out.println("Wybrałeś SansSerif");
                currentFont = "SansSerif";
                System.out.println(currentFont);
                panelWriter.poleDoPisanie.setFont(new Font(currentFont, bol + curs,fSize));
                break;
        }












        if(e.getSource() == panelMenu.wczytaj)
        {

            System.out.println("Kliknąłeś menuItem wczytaj");

            JFileChooser jf = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
            jf.setFileFilter(filter);
            jf.showOpenDialog(null);
            File plikOdczytany = jf.getSelectedFile();
            setFileName(plikOdczytany.getName());


            try {
                Scanner odczyt = new Scanner(plikOdczytany);

                panelWriter.poleDoPisanie.setText("");

                while(odczyt.hasNext())
                {
                    String text = odczyt.nextLine();
                    panelWriter.poleDoPisanie.append(text + "\n");
                }


            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

        }


        if(e.getSource() == panelMenu.zapisz)
        {
            //setFileName(JOptionPane.showInputDialog("Podaj nazwe pliku",getFileName()));
            JFileChooser jf = new JFileChooser();
            jf.showSaveDialog(null);
            File plikDoZapisu = jf.getSelectedFile();
            setFileName(jf.getName(plikDoZapisu));
            setFilePath(plikDoZapisu.getPath());
            System.out.println(jf.getName(plikDoZapisu));
            System.out.println(plikDoZapisu.getPath());
            System.out.println("Klikasz menuItem zapisz");

            try {

                int n = JOptionPane.showOptionDialog(null, "Czy jesteś pewien że chcesz zapisać ten plik","Zapisać plik?",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[1]);


                if(n == 0)
                {

                    if(getFileName().charAt(getFileName().length()-1) == 't' &&
                            getFileName().charAt(getFileName().length()-2) == 'x' &&
                            getFileName().charAt(getFileName().length()-3) == 't')
                    {
                        pw = new PrintWriter(getFilePath());
                    }else
                    {
                        pw = new PrintWriter(getFilePath() + ".txt");
                    }


                    pw.println(panelWriter.poleDoPisanie.getText());
                    pw.close();
                }

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

        }

        if (e.getSource() == panelMenu.oNas)
        {
            String oNas = "Jestem programistą java a to jest program, który powstał w ramach wypełnienia wolnego czasu";
            JOptionPane.showMessageDialog(null, oNas);
        }
        if (e.getSource() == panelMenu.instrukcja)
        {
            String instrukcja = "Zapis: Crtl + S \n" +
                    "Odczyt: Crtl + O \n" +
                    "Otwórz opcje pliku: Alt + A \n" +
                    "Otwórz opcje pomoc: Alt + H";

            JOptionPane.showMessageDialog(null, instrukcja);
        }



    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if( e.getSource() == panelOptions.fontSize)
        {
            String fontSize = (String) panelOptions.fontSize.getSelectedItem();

            fSize = Integer.parseInt(fontSize);

            System.out.println(fSize);
            panelWriter.poleDoPisanie.setFont(new Font(panelWriter.poleDoPisanie.getFont()+"", curs + bol , fSize));

        }


    }
}

// Jeśli otwieram plik i go modykuje to wtedy żeby jego nazwa była od razu w polu do zapisu
// dodawanie notatek na serwer
// wczytywanie notatek z serwera
// ulepszyć emotikony
// pokazać jaka czcionka jest aktywna w danej chwili
