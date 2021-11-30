package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Frame extends JFrame implements ActionListener, MouseListener {


    DBConnection connection = new DBConnection();
    JFrame frame;
    JPanel panel;
    JButton deleteButton;
    JButton addButton;
    JButton showButton;

    DefaultListModel<String> lista = new DefaultListModel<>();
    JList<String> list = new JList<>(lista);

    String detail;






    Frame()
    {

        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        panel.setBackground(Color.black);
        GridBagConstraints constraints = new GridBagConstraints();

        addButton = new JButton("add");
        addButton.setFocusPainted(false);
        //addButton.setBorder(null);
        addButton.setBackground(Color.decode("#4CAF50"));
        addButton.setOpaque(true);
        addButton.setSize(100,75);
        addButton.addMouseListener(this);
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        addButton.setCursor(cursor);



        deleteButton = new JButton("delete");
        deleteButton.setFocusPainted(false);
        //deleteButton.setBorder(null);
        deleteButton.setBackground(Color.decode("#4CAF50"));
        deleteButton.setOpaque(true);
        deleteButton.setSize(new Dimension(100,75));
        deleteButton.addMouseListener(this);

        deleteButton.setCursor(cursor);



        showButton = new JButton("show");
        showButton.setFocusPainted(false);
        //showButton.setBorder(null);
        showButton.setBackground(Color.decode("#4CAF50"));
        showButton.setOpaque(true);
        showButton.setSize(new Dimension(100,75));
        showButton.addMouseListener(this);

        showButton.setCursor(cursor);



        frame.setPreferredSize(new Dimension(600,800));

        list.setBounds(100,100, 300,500);
        setFont(list.getFont ().deriveFont (16.0f));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        frame.setTitle("To Do List");
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.ipady = 300;

        constraints.gridwidth =3;


        constraints.gridx = 0;
        constraints.gridy = 0;

        panel.add(list,constraints);
        constraints.gridwidth =1;
        constraints.weightx = 0.5;
        constraints.ipady = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;

        panel.add(deleteButton, constraints);

        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;

        panel.add(addButton, constraints);

        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 1;

        panel.add(showButton, constraints);

        frame.add(panel);

        frame.pack();

        AddQuestClass addQuestClass = new AddQuestClass(frame);
        addQuestClass.setLocationRelativeTo(frame);
        addQuestClass.setVisible(false);


        DetailsQuestClass detailsQuestClass = new DetailsQuestClass(frame);
        detailsQuestClass.setLocationRelativeTo(frame);
        detailsQuestClass.setVisible(false);


        LogInClass loginClass = new LogInClass(frame);

        loginClass.setLocationRelativeTo(frame);


        loginClass.setVisible(true);
        connection.showAllQuest();
        if(loginClass.isLoginOk())
        {
            frame.setVisible(true);
            panel.setVisible(true);
            connection.showAllQuest();
            for (int i=0; i<connection.zadania.size(); i++)
            {

                lista.addElement(connection.zadania.get(i));

            }




        }else

            {
                loginClass.dispose();
                frame.dispose();
                System.exit(0);

            }

        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (list.getSelectedIndex() != -1)
                {
                    connection.deleteQuest(list.getSelectedValue());

                }
                refreshList();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addQuestClass.setVisible(true);
            }
        });
        
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (list.getSelectedIndex() != -1)
                {
                    setDetail(connection.showOnlyOne(list.getSelectedValue()));
                    detailsQuestClass.setVisible(true);
                    detailsQuestClass.setDetails(getDetail());

                }
            }
        });
        

        autoRefresh();



    }

    @Override
    public void actionPerformed(ActionEvent e) {




    }

    public void refreshList()
    {

        connection.showAllQuest();
        lista.removeAllElements();
        System.out.println("po usun " + lista);
        for (int i=0; i<connection.zadania.size(); i++)
        {

            lista.addElement(connection.zadania.get(i));

        }
        System.out.println(lista);
        list.setModel(lista);


    }

    public void autoRefresh()
    {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    try {
                        Thread.sleep(2000);

                        connection.showAllQuest();
                        lista.removeAllElements();
                        System.out.println("po usun " + lista);
                        for (int i=0; i<connection.zadania.size(); i++)
                        {

                            lista.addElement(connection.zadania.get(i));

                        }
                        System.out.println(lista);
                        list.setModel(lista);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        t.start();
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
        if(e.getSource() == addButton)
        {
            addButton.setBackground(Color.decode("#FFFFFF"));

        }
        if(e.getSource() == deleteButton)
        {
            deleteButton.setBackground(Color.decode("#FFFFFF"));

        }
        if(e.getSource() == showButton)
        {
            showButton.setBackground(Color.decode("#FFFFFF"));

        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == addButton)
        {
            addButton.setBackground(Color.decode("#4CAF50"));
        }
        if(e.getSource() == deleteButton)
        {
            deleteButton.setBackground(Color.decode("#4CAF50"));
        }
        if(e.getSource() == showButton)
        {
            showButton.setBackground(Color.decode("#4CAF50"));
        }
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}
