package com.company;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {

    JPanel panel;
    JTextField text;
    JButton button;
    Dimension screenSize;
    JLabel label;
    String slowo;
    int dlugosc;
    char[] Array = new char[40];
    String zmienione = new String();
    char temp;
    String newLine = System.getProperty("line.separator");

    public GUI(){
        JFrame frame = new JFrame("MasterKeyboard");

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocation(50,50);



         panel = new JPanel();
         text = new JTextField(15);
         button = new JButton("odwróć");
         label = new JLabel("Słowo po odwróceniu");
         label.setFont(new Font("dialog",Font.BOLD, 20));

         button.addActionListener(this);

        panel.add(text);
        panel.add(button);
        panel.add(label);
        add(panel);
        setSize(screenSize.width/2,screenSize.height/2);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(button.getText()) ) {

            label.setText("");
            System.out.println("sdsdd");
            slowo=text.getText();
            dlugosc = slowo.length()-1;

            for(int i=0; i<slowo.length(); i++)
            {

                Array[i] = slowo.charAt(dlugosc);
                dlugosc--;
                System.out.println(Array[i]);
            }

            String str = new String(Array);
            System.out.println(str);
            label.setText(str);
            str = "";








        }
    }


}

