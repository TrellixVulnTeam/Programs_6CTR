package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class Graphic {
    private BufferedImage im = null;
    private WritableRaster raster = null;
    private int width = 0;
    private int height = 0;
    int x, y;
    public int pixels[][] = new int[30][30];
    public int nowa[][] = new int[30][30];
    int a=0;
    String filepath;


    public void loadFile()
    {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);

        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            filepath=selectedFile.getAbsolutePath();
        }
    }
    public Graphic() throws IOException {
    }
    public void wczytaj() throws IOException {
        loadFile();
        im = ImageIO.read(new File(filepath));

        for (int i=0; i<30; i++)
        {

            for (int j =0; j<30; j++)
            {
                Color color = new Color(im.getRGB(i,j));
                //System.out.println("R" + color.getRed() + " G " + color.getGreen() + " B " + color.getBlue());
                System.out.println(color);
                color.getRed();
                color.getGreen();
                color.getBlue();
                    if (color.getRed()==255 && color.getBlue() == 255 && color.getGreen() == 255)
                    {
                        nowa[i][j]=0;
                    }else if(color.getRed()==0 && color.getBlue() == 0 && color.getGreen() == 0)
                    {
                        nowa[i][j]=2;
                    }else
                    {
                        nowa[i][j]=0;
                    }

            }
        }
        System.out.println("gotowe");

    }

    public int[][] wczytane = nowa;



}
