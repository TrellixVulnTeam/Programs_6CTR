package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MasterKeyboard extends JFrame implements ActionListener, KeyListener, Runnable {
    int userid;
    String nazwa;
    JPanel panel;
    JLabel label;
    TextArea wordArea;
    JButton start;
    JLabel nowy;
    JButton next;
    JButton stop;
    JLabel lab;

    JLabel score;
    String secret = "s";
    JTextField text;
    Dimension screenSize;
    String filepath;
    String newLine = System.getProperty("line.separator");
    List<String> lista = new ArrayList<>();
    List<String> listaInput = new ArrayList<>();
    List<String> correct = new ArrayList<>();
    long millisActualTime =0;
    long executionTime=0;
    boolean endtime = false;
    int sprawdza;
    int delay = 1000;

    Runnable runner = new Time();
    Thread thread3 = new Thread(runner);


    int timer = 10;
    int count=0;
    public MasterKeyboard()
    {


        userid = Integer.parseInt(JOptionPane.showInputDialog("Podaj ID usera"));
        try(Connection con = getConnection()){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT username FROM accaunt where userid="+userid);
            if(rs.next()){
                nazwa = rs.getString("username");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("MasterKeyboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MasterKeyboard");
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        panel = new JPanel();
        label = new JLabel("Witaj " + nazwa);
        start = new JButton("Start");

        wordArea = new TextArea();
        next = new JButton("Next");
        score = new JLabel("Score ");
        stop = new JButton("Stop");
        text = new JTextField((int)screenSize.getWidth()/5/15);
        start.setPreferredSize(new Dimension((int)screenSize.getWidth()/10, (int)screenSize.getHeight()/20));
        start.addActionListener( this);
        next.addActionListener(this);
        stop.addActionListener(this);






        panel.add(score);
        panel.add(label);
        panel.add(start);
        panel.add(wordArea);
        panel.add(text);
        panel.add(stop);
        panel.add(next);




        add(panel);


        next.setEnabled(false);
        stop.setEnabled(false);
        setSize(screenSize.width/2,screenSize.height/2);
        setResizable(false);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(start.getText()) ) {
            wordArea.setText("");
             millisActualTime = System.currentTimeMillis();
            next.setEnabled(true);
            stop.setEnabled(true);
             count = 0;
             thread3.start();

            for (int i = 0; i < lista.size(); i++) {
                wordArea.append(lista.get(i) + newLine);

            }









//            Timer t2 = new Timer();
//            t2.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    time.setText(String.valueOf((System.currentTimeMillis()/1000)));
//                }
//            },0,10000);


        }
            if(e.getActionCommand().equals(next.getText()))
            {
                listaInput.add(text.getText());
                text.setText("");
            }

            if(e.getActionCommand().equals(stop.getText()))
            {
                try {
                    thread3.join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                executionTime = System.currentTimeMillis() - millisActualTime;
                for (int i = 0; i < lista.size(); i++) {

                    for (int j = 0; j < listaInput.size(); j++) {

                        if (lista.get(i).equals(listaInput.get(j))) {
                            count=count+1;
                            score.setText(String.valueOf(count));
                            correct.add(lista.get(i));
                            System.out.println(count);

                        }
                    }
                }
                System.out.println("czas: " + executionTime);
                wordArea.setText("");
                wordArea.setText("Czas gry: " + executionTime/1000 + " sekund" + newLine + "Ilość poprawnie wpisanych wyrazów: " + count + newLine + "Poprawnie wpisane wyrazy: " + newLine);
                for (int i=0; i<correct.size(); i++)
                {
                    wordArea.append(correct.get(i) + newLine);
                }

                System.out.println(count);

                next.setEnabled(false);
                stop.setEnabled(false);



            }




    }

    public void WordList() {

        // wybieranie pliku
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);

        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            filepath=selectedFile.getAbsolutePath();
        }

        // wczytywanie pliku
        try {
            FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            String linia;
            while((linia = bufferReader.readLine()) != null) {
                lista.add(linia);
                System.out.println(lista);
        }
            fileReader.close();


    }catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //sposób łaczenia z bazą
    Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://arjuna.db.elephantsql.com/uazcjixe", "uazcjixe", "mzOUVcrYWU40MGNIA_17Za9GKNHcTL1U");
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }


    @Override
    public void run() {
        System.out.println("NIE WIEM CZY TO SIE DA");
        }
    }


