package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LogInClass extends JDialog implements ActionListener, MouseListener {
    DBConnection connection = new DBConnection();
    JLabel loginLabel;
    JLabel passwordLabel;
    JLabel badValidationLabel;

    JTextField loginField;
    JTextField passwordField;

    JButton button;
    boolean loginOk = false;
    JPanel jpanel;

    LogInClass(JFrame parentFrame)
    {
        super(parentFrame, "Login",true);
        jpanel= new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10,10,10,10);
        jpanel.setBackground(Color.black);
        loginLabel = new JLabel("login");


        constraints.gridx =0;
        constraints.gridy =0;
        loginLabel.setPreferredSize(new Dimension(70,30));
        loginLabel.setFont(loginLabel.getFont ().deriveFont (19.0f));

        jpanel.add(loginLabel, constraints);


        passwordLabel = new JLabel("hasło");
        passwordLabel.setPreferredSize(new Dimension(70,30));
        passwordLabel.setFont(loginLabel.getFont ().deriveFont (19.0f));


        constraints.gridx=0;
        constraints.gridy = 1;

        jpanel.add(passwordLabel,constraints);


        loginField = new JTextField("");

        constraints.gridx=1;
        constraints.gridy=0;
        loginField.setPreferredSize(new Dimension(70,30));


        jpanel.add(loginField,constraints);


        passwordField = new JTextField("");

        constraints.gridx = 1;
        constraints.gridy = 1;
        passwordField.setPreferredSize(new Dimension(70,30));

        jpanel.add(passwordField,constraints);

        button = new JButton("Zaloguj");

        button = new JButton("add");
        button.setFocusPainted(false);
        //button.setBorder(null);
        button.setBackground(Color.decode("#4CAF50"));
        button.setOpaque(true);
        button.setSize(100,75);
        button.addMouseListener(this);
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        button.setCursor(cursor);



        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth =2;
        constraints.anchor = GridBagConstraints.CENTER;
        jpanel.add(button,constraints);

        button.addActionListener(this);

        badValidationLabel = new JLabel();

        constraints.gridx = 0;
        constraints.gridy=3;
        constraints.gridwidth=2;

        constraints.anchor = GridBagConstraints.CENTER;

        jpanel.add(badValidationLabel,constraints);

        jpanel.setPreferredSize(new Dimension(400,200));

        jpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login"));



        add(jpanel);

        pack();

        setLocationRelativeTo(null);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button)
        {
            System.out.println("Próba logowania");
            connection.showAll();
            checkLogin();

            pack();
        }
    }


    public void checkLogin()
    {
        if( connection.logIn(loginField.getText(), passwordField.getText()) == true)
        {
           // co ma się stać jak się uda
            System.out.println("YEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            loginOk = true;
            dispose();

        }else
        {

            badValidationLabel.setText("Nie poprawne dane");
            loginField.setText("");
            loginField.setSize(new Dimension(70,30));
            passwordField.setText("");
            loginField.setSize(new Dimension(70,30));


        }
    }

    public boolean isLoginOk(){
        return loginOk;
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
        button.setBackground(Color.decode("#FFFFFF"));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        button.setBackground(Color.decode("#4CAF50"));

    }
}
