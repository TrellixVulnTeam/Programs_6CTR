package com.company;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Plansza extends DrawPlansza {

    static int size = 30;
    final static int BlockSize =800/size;

    final static int WIDTH = BlockSize *size;
    final static int HEIGTH = BlockSize *size;

    public int[][] tablica = new int[size][size];
    Random rand = new Random();
    static int algorithm_count = 0;
    Graphic graph = new Graphic();


    Plansza() throws IOException {
        super(WIDTH, HEIGTH);
        resetTab();
    }


    public  void sandGenerator(){

        for (int r = 4; r < size-4; r++) {
            for (int c = 2; c < 11; c++) {

                if (tablica[r][c] == 0) {
                    tablica[r][c] = rand.nextInt((1 - 0) + 1) + 0;
                }

            }
        }

    }


    public  void generuj_naczynie(){

        int wybor;
        wybor = rand.nextInt(4);
        System.out.println(wybor);
        switch (wybor)
        {
            case 0:
            {
                //Miska
                for (int r = 8; r < size-8; r++) {
                    for (int c = 25; c < 26; c++) {

                        if (tablica[r][c] == 0) {
                            tablica[r][c] = 2;
                        }

                    }
                }
                int w = 3;
                int q = 20;
                int t = 4;
                int u = 20;

                while(w!=8)
                {
                    if (tablica[w][q] == 0) {
                        tablica[w][q] = 2;
                        tablica[size - t][u] = 2;
                    }
                    System.out.println(q);
                    w=w+1;
                    q=q+1;
                    t = t+1;
                    u = u+1;

                }

                break;
            }
            case 1:
            {
                //okrąg


                int horizontal = 8;
                int vertical = 22;
                int horizontal_right = 8;
                int vertical_right = 22;


                int horizontalup = 8;
                int verticalup = 19;
                int horizontal_rightup = 8;
                int vertical_rightup = 19;


                while(horizontal!=13)
                {
                    if (tablica[horizontal][vertical] == 0) {
                        tablica[horizontal][vertical] = 2;
                        tablica[size - horizontal_right][vertical_right] = 2;
                    }

                    horizontal=horizontal+1;
                    vertical=vertical+1;
                    horizontal_right = horizontal_right+1;
                    vertical_right = vertical_right+1;

                }
                while(horizontalup!=13)
                {
                    if (tablica[horizontalup][verticalup] == 0) {
                        tablica[horizontalup][verticalup] = 2;
                        tablica[size - horizontal_rightup][vertical_rightup] = 2;
                    }

                    horizontalup=horizontalup+1;
                    verticalup=verticalup-1;
                    horizontal_rightup = horizontal_rightup+1;
                    vertical_rightup = vertical_rightup-1;

                }
                for (int r = 13; r < size-12; r++) {
                    for (int c = 26; c < 27; c++) {

                        if (tablica[r][c] == 0) {
                            tablica[r][c] = 2;
                        }

                    }
                }
                for (int r = 13; r < size-12; r++) {
                    for (int c = 15; c < 16; c++) {

                        if (tablica[r][c] == 0) {
                            tablica[r][c] = 2;
                        }

                    }
                }
                for (int r = 8; r < 9; r++) {
                    for (int c = 19; c < 22; c++) {

                        if (tablica[r][c] == 0) {
                            tablica[r][c] = 2;
                        }

                    }
                }
                for (int r = 22; r < 23; r++) {
                    for (int c = 19; c < 22; c++) {

                        if (tablica[r][c] == 0) {
                            tablica[r][c] = 2;
                        }

                    }
                }

                break;
            }
            case 2:
            {
                //klepsydra
                for (int r = 6; r < size-6; r++) {
                    for (int c = 27; c < 28; c++) {

                        if (tablica[r][c] == 0) {
                            tablica[r][c] = 2;
                        }

                    }
                }

                int horizontal = 6;
                int vertical = 26;
                int horizontal_right = 7;
                int vertical_right = 26;

                int horizontalup = 13;
                int verticalup = 19;
                int horizontal_rightup = 14;
                int vertical_rightup = 19;

                while(horizontal!=13)
                {
                    if (tablica[horizontal][vertical] == 0) {
                        tablica[horizontal][vertical] = 2;
                        tablica[size - horizontal_right][vertical_right] = 2;
                    }

                    horizontal=horizontal+1;
                    vertical=vertical-1;
                    horizontal_right = horizontal_right+1;
                    vertical_right = vertical_right-1;

                }

                while(horizontalup!=5)
                {
                    if (tablica[horizontalup][verticalup] == 0) {
                        tablica[horizontalup][verticalup] = 2;
                        tablica[size - horizontal_rightup][vertical_rightup] = 2;
                    }

                    horizontalup=horizontalup-1;
                    verticalup=verticalup-1;
                    horizontal_rightup = horizontal_rightup-1;
                    vertical_rightup = vertical_rightup-1;

                }




                break;
            }
            case 3:
            {

                //Skosy

                int horizontal = 8;
                int vertical = 15;
                int horizontal_right = 7;
                int vertical_right = 22;

                int horizontalup = 14;
                int verticalup = 14;
                int horizontal_rightup = 15;
                int vertical_rightup = 20;


                while(horizontal!=13)
                {
                    if (tablica[horizontal][vertical] == 0) {
                        tablica[horizontal][vertical] = 2;
                        tablica[size - horizontal_right][vertical_right] = 2;
                    }

                    horizontal=horizontal+1;
                    vertical=vertical+1;
                    horizontal_right = horizontal_right+1;
                    vertical_right = vertical_right+1;

                }

                while(horizontalup!=18)
                {
                    if (tablica[horizontalup][verticalup] == 0) {
                        tablica[horizontalup][verticalup] = 2;
                        tablica[size - horizontal_rightup][vertical_rightup] = 2;
                    }

                    horizontalup=horizontalup+1;
                    verticalup=verticalup-1;
                    horizontal_rightup = horizontal_rightup+1;
                    vertical_rightup = vertical_rightup-1;

                }





                break;
            }
        }





    }


//    public void printTable(){
//
//        for (int r = 0; r < size; r++) {
//
//            for (int c= 0; c < size; c++) {
//
//            }
//        }
//    }

    public  void resetTab(){

        for (int x = 0; x < size; x++) {

            for (int y = 0; y < size; y++) {
                tablica[x][y] = 0;
            }
        }


        for(int j = 0; j < size; j++){
            for(int i = 0; i < size; i++) {

                if (i == 0  || i == 1 || i == size - 2 ||   i == size - 1) {

                    tablica[i][j] = 2;
                }
            }

        }




    }

    public  void reset_naczynie(){

        for (int x = 0; x < size; x++) {

            for (int y = 11; y < size; y++) {
                tablica[x][y] = 0;
            }
        }


        for(int j = 0; j < size; j++){
            for(int i = 0; i < size; i++) {

                if (i == 0  || i == 1 || i == size - 2 ||   i == size - 1) {

                    tablica[i][j] = 2;
                }
            }

        }




    }

    public void loadImage(){

        for (int x = 0; x < size; x++) {

            for (int y = 0; y < size; y++) {

                if (graph.pixels[x][y] == -1)
                {

                    tablica[x][y] = 2;
                }else
                {
                    tablica[x][y] = 0;
                }
            }
        }







    }







    public void algorytm()  {



        if(algorithm_count == size -1){
            algorithm_count = 0; // powrót na początek tablicy

        }



        int nr_wiersz= 0;
        int nr_kol = 0;
        int dl_tablicy = size; //30

        if(algorithm_count % 2 == 0   ){ // parzyste
            dl_tablicy = size -2;
            nr_wiersz = algorithm_count;
            nr_kol = 0;

        }
        else if (algorithm_count % 2 != 0) { //nieparzyste
            dl_tablicy  = size -3;
            nr_wiersz= algorithm_count;
            nr_kol = 1;


        }

            System.out.println("count = " + algorithm_count);

        for(int  r = nr_wiersz; r <= dl_tablicy; r=r+2){ //row
            for(int  c= nr_kol; c <= dl_tablicy; c=c+1){


                int tab_porownywania[][] = {
                        {tablica[c][r], tablica[c+1][r]},
                        {tablica[c][r+1], tablica[c+1][r+1]}
                };


                int wynikowa[][] = porownaj(tab_porownywania);


                tablica[c][r] = wynikowa[0][0];
                tablica[c + 1][r + 1] = wynikowa[1][1];

                tablica[c][r + 1] = wynikowa[1][0];
                tablica[c+ 1][r] = wynikowa[0][1];

            }

        }



        algorithm_count ++;


    }






    public int[][] porownaj(int[][] wejsciowa){




        if(Arrays.deepEquals(wejsciowa, new int[][]{ {0,1}, {0,0}}))

            return new int[][]{ {0,0}, {0,1}};

        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {1,0},
                {0,0}})) return new int[][]{
                {0,0},
                {1,0}};
        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {1,1},
                {0,0}})) return new int[][]{
                {0,0},
                {1,1}};
        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {0,1},
                {0,1}})) return new int[][]{
                {0,0},
                {1,1}};
        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {1,0},
                {1,0}})) return new int[][]{
                {0,0},
                {1,1}};

        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {0,1},
                {1,0}})) return new int[][]{
                {0,0},
                {1,1}};
        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {1,0},
                {0,1}})) return new int[][]{
                {0,0},
                {1,1}};





        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {1,1},
                {0,1}})) return new int[][]{
                {0,1},
                {1,1}};
        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {1,1},
                {1,0}})) return new int[][]{
                {1,0},
                {1,1}};

            // tutaj zaczynają się naczynia
        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {1,2},
                {0,0}})) return new int[][]{
                {0,2},
                {1,0}};
        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {2,1},
                {0,0}})) return new int[][]{
                {2,0},
                {0,1}};
        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {0,1},
                {2,0}})) return new int[][]{
                {0,0},
                {2,1}};
        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {1,0},
                {0,2}})) return new int[][]{
                {0,0},
                {1,2}};
        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {2,1},
                {2,0}})) return new int[][]{
                {2,0},
                {2,1}};
        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {1,2},
                {0,2}})) return new int[][]{
                {0,2},
                {1,2}};

        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {1,1},
                {0,2}})) return new int[][]{
                {0,1},
                {1,2}};
        else if(Arrays.deepEquals(wejsciowa, new int[][]{
                {1,1},
                {2,0}})) return new int[][]{
                {1,0},
                {2,1}};


        else{
            return wejsciowa;
        }


    }





    @Override
    public void rysujObraz() {
        rysujPlansze();
    }

    private void rysujPlansze(){


        for (int x = 0; x < size; x++){
            for(int y = 0; y< size; y++){



                if (tablica[x][y] == 0){
                    graphics.setColor(Color.WHITE);
                    graphics.drawRect(x*BlockSize, y*BlockSize, BlockSize-1, BlockSize-1);
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(x*BlockSize, y*BlockSize, BlockSize, BlockSize);

                }




                if (tablica[x][y] == 1){
                    graphics.setColor(Color.WHITE);
                    graphics.drawRect(x*BlockSize, y*BlockSize, BlockSize-1, BlockSize-1);
                    graphics.setColor(Color.YELLOW);
                    graphics.fillRect(x*BlockSize, y*BlockSize, BlockSize, BlockSize);


                }

                if (tablica[x][y] == 2){
                    graphics.setColor(Color.BLACK);
                    graphics.drawRect(x*BlockSize, y*BlockSize, BlockSize-1, BlockSize-1);
                    graphics.setColor(Color.BLACK);
                    graphics.fillRect(x*BlockSize, y*BlockSize, BlockSize, BlockSize);

                }





            }


        }

    }

}
