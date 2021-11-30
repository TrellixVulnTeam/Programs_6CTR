package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CheckingClass implements PalindromChecker {
    FileWriter fw = null;
    BufferedReader fileReader = null;
    List<String>lista = new ArrayList<>();
    List<String>palindrom = new ArrayList<>();
    List<String>niepalindrom = new ArrayList<>();

    public void wczytywanie() throws IOException {

        try{
            fileReader= new BufferedReader(new FileReader("palindromy2.txt"));
            String napis;
            String poprawka = null;
            List<String> ls = new ArrayList<>();

            while((napis = fileReader.readLine()) != null) {

                poprawka = napis.replaceAll("[^\\p{IsLetter}]+", "");
                lista.add(poprawka.toLowerCase());

            }

//            for(int i=0; i<lista.size(); i++)
//            {
//                System.out.println(lista.get(i));
//            }
            for (int i=0; i<lista.size(); i++)
            {
                check(lista.get(i));
            }
           System.out.println(lista);
//            System.out.println(lista.get(7));
//            check(lista.get());

        }finally {
            if(fileReader !=null)
            {
                fileReader.close();

            }
        }

    }



    @Override
    public boolean check(String s) {
        int h = s.length();

        int kk = 0;
        int k = (h - 1);
        for (int i = 0; i < h; i++) {

            if (s.charAt(i) != s.charAt(k)) {

                kk = 1;
                break;
            }
            k--;
        }

        if (kk == 1) {
            System.out.println(s + " Nie jest palindromem");
            niepalindrom.add(s);
        }else {
            System.out.println(s+ " Jest palindromem");
            palindrom.add(s);
        }
        return false;
    }


    public void zapisdoPliku() throws IOException
    {

        BufferedWriter zapis = new BufferedWriter(new FileWriter("wynik.txt"));
        zapis.write("\n"+" Zdania które nie są palindromami " + " \n");
        for (int i=0; i<niepalindrom.size(); i++)
        {
            zapis.append(niepalindrom.get(i) + " \n");
        }

        zapis.write("\n"+" Palindromy: " + " \n");
        for (int i=0; i<palindrom.size(); i++)
        {
            zapis.append(palindrom.get(i) + " \n");

        }

        zapis.close();
    }




}
