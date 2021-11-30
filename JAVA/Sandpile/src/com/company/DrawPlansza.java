package com.company;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class DrawPlansza extends Canvas {


    BufferedImage image;
    Graphics2D graphics;

    DrawPlansza(int width, int height ){
        super();
        setSize(width,height);
        image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();


    }

    public abstract void rysujObraz();
    public abstract void algorytm() throws InterruptedException;

    private void naEkran(){
        Graphics graphica= getGraphics();

        graphica.drawImage(image, 0, 0,null);
        graphica.dispose();

    }

    public void uruchom() {


        rysujObraz();
        naEkran();
    }

}
