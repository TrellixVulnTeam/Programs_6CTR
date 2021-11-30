package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddQuestClass extends JDialog implements ActionListener, MouseListener {

    DBConnection connection = new DBConnection();

    JLabel headInfo;
    JLabel questContentInfo;
    JButton addQuest;
    JTextArea questContent;
    JPanel panelAdd;
    JTextField date;
    JLabel dateLabel;

    AddQuestClass(JFrame parentFrame)
    {
        super(parentFrame, "dodawanie zadania",true);
        panelAdd= new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panelAdd.setBackground(Color.black);
        constraints.insets = new Insets(10,10,10,10);



        headInfo = new JLabel("Dodawanie nowego zadania");
        constraints.gridx =0;
        constraints.gridy =0;
        panelAdd.add(headInfo,constraints);

        questContentInfo = new JLabel("treść zadania");
        constraints.gridx =0;
        constraints.gridy =1;
        panelAdd.add(questContentInfo,constraints);



        questContent = new JTextArea();
        constraints.gridx =0;
        constraints.gridy =2;
        constraints.weightx = 2;
        
        questContent.setPreferredSize(new Dimension(200,100));
        panelAdd.add(questContent,constraints);




        addQuest = new JButton("add");

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        addQuest.setCursor(cursor);

        addQuest.setFocusPainted(false);
        //addQuest.setBorder(null);
        addQuest.setBackground(Color.decode("#4CAF50"));
        addQuest.setOpaque(true);
        addQuest.setSize(100,75);
        addQuest.addMouseListener(this);


        constraints.gridx =0;
        constraints.gridy =30;
        constraints.anchor = GridBagConstraints.CENTER;
        panelAdd.add(addQuest,constraints);
        addQuest.addActionListener(this);


        dateLabel = new JLabel("Podaj datę w formacie rrrr-mm-dd hh:mm:ss");

        constraints.gridx =0;
        constraints.gridy =32;
        constraints.anchor = GridBagConstraints.CENTER;
        panelAdd.add(dateLabel,constraints);


        date = new JTextField("np. 2010-04-23 12-27-02");
        constraints.gridx =0;
        constraints.gridy =33;
        constraints.anchor = GridBagConstraints.CENTER;
        panelAdd.add(date,constraints);



        panelAdd.setPreferredSize(new Dimension(800,600));

        panelAdd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dodawanie zadania"));
        add(panelAdd);
        pack();

        setLocationRelativeTo(null);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addQuest)
        {
            if(questContent.getText() != "")
            {

                connection.addQuest(questContent.getText(),date.getText());
                dispose();
                questContent.setText("");
                date.setText("");

            }
            else{
                System.out.println("Puste zapytanie ");
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        addQuest.setBackground(Color.decode("#FFFFFF"));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        addQuest.setBackground(Color.decode("#4CAF50"));

    }
}
