package com.company;

import javax.swing.*;
import java.awt.*;

public class Time extends JFrame implements Runnable {


    JPanel pan;
    boolean Startapp = false;
    JLabel time;
    JFrame frame = new JFrame();
    JPanel panelik = new JPanel();
    JLabel lab = new JLabel();
    int timer = 10;

    public Time()
    {

    }


    @Override
    public void run() {
        panelik = new JPanel();
        lab = new JLabel("KURDE GDZEI TO");
        panelik.add(lab);
        add(panelik);

        setVisible(true);
        setSize(400,300);



        try {while (timer!=-1) {
            Thread.sleep(1000);
            lab.setText(String.valueOf(timer));
            timer = timer-1;
            System.out.println("zobaczymy " + timer);

        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Startapp = false;


    }



}
