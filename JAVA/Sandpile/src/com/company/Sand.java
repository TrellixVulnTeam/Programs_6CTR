package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class Sand extends JPanel implements Runnable {

    static JFrame frame = new JFrame("Symulator spadającego piasku");
    static Plansza plansza;
    static {
        try {
            plansza = new Plansza();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Sand sand = new Sand();
    static Thread thread = new Thread(sand);

    public static JButton clean = new JButton("Czyść");
    public static JButton stopBt = new JButton("Stop");
    public static JButton startBt = new JButton("Start");
    public static JButton sandGenerate = new JButton("Piasek");
    public static JButton generuj_naczynie = new JButton("Naczynie");
    public static JButton load = new JButton("Wczytaj");

    static boolean startAlgorithm = false;

    boolean start = false;

    Sand() {
        super();
        start = true;
        setBackground(Color.BLACK);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));


    }

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 850);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.add(sand);
        clean.setSize(80, 15);
        sandGenerate.setSize(80, 15);
        startBt.setSize(80, 15);
        stopBt.setSize(80, 15);
        generuj_naczynie.setSize(80, 15);
        load.setSize(80, 15);

        clean.setLocation(890, 100);
        sandGenerate.setLocation(790, 100);
        startBt.setLocation(790, 50);
        stopBt.setLocation(890, 50);
        generuj_naczynie.setLocation(830, 150);
        load.setLocation(830, 200);

        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAlgorithm = false;
                plansza.resetTab();
            }
        });

        sandGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JButton b = (JButton) e.getSource();

                System.out.println(b.getText());

                plansza.sandGenerator();
            }
        });

        startBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAlgorithm = true;
            }
        });
        stopBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAlgorithm = false;
            }
        });
        generuj_naczynie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plansza.reset_naczynie();
                plansza.generuj_naczynie();

            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Graphic graph = new Graphic();
                    graph.wczytaj();


                    for (int x = 0; x < plansza.size; x++) {

                        for (int y = 0; y < plansza.size; y++) {
                            if (graph.nowa[x][y] == 2) {

                                plansza.tablica[x][y] = 2;
                            } else {

                                plansza.tablica[x][y] = 0;
                            }
                        }
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });


        plansza.setLocation(0, 0);
        sand.add(plansza);
        sand.add(clean);
        sand.add(sandGenerate);
        sand.add(startBt);
        sand.add(stopBt);
        sand.add(generuj_naczynie);
        sand.add(load);


        frame.setVisible(true);

        thread.start();

    }


    @Override
    public void run() {
        while (start) {
            plansza.uruchom();

            if (startAlgorithm == true) {

                plansza.algorytm();

            }


            try {
                thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


    }


}
