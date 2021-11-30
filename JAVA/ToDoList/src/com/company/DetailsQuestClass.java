package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailsQuestClass extends JDialog implements ActionListener {

        DBConnection connection = new DBConnection();
        JPanel detailsPanel;
        JLabel autor;
        JLabel zadanie;
        JLabel data_dodania;
        JLabel data_zakonczenia;

        JLabel autorDetail;
        JLabel zadanieDetail;
        JLabel dataDodaniaDetail;
        JLabel dataZakonczeniaDetail;

        String [] splitedDetails;

        JLabel details;


        DetailsQuestClass(JFrame parentFrame){
            super(parentFrame, "szczegóły zadania",true);
            autor = new JLabel("Autor");
            zadanie = new JLabel("Zadanie");
            data_dodania = new JLabel("Data dodania");
            data_zakonczenia = new JLabel("Data zakończenia");
            autorDetail = new JLabel("");
            zadanieDetail = new JLabel("");
            dataDodaniaDetail = new JLabel("");
            dataZakonczeniaDetail = new JLabel("");


            details = new JLabel("");


            detailsPanel= new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();

            constraints.insets = new Insets(10,10,10,10);



            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 0.5;
            detailsPanel.add(autor,constraints);

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 1;
            constraints.gridy = 0;
            constraints.weightx = 0.5;
            detailsPanel.add(zadanie,constraints);


            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 2;
            constraints.gridy = 0;
            constraints.weightx = 0.5;
            detailsPanel.add(data_dodania,constraints);

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 3;
            constraints.gridy = 0;
            constraints.weightx = 0.5;
            detailsPanel.add(data_zakonczenia,constraints);

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.weightx = 0.5;

            detailsPanel.add(autorDetail,constraints);

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 1;
            constraints.gridy = 1;
            constraints.weightx = 0.5;

            detailsPanel.add(zadanieDetail,constraints);

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 2;
            constraints.gridy = 1;
            constraints.weightx = 0.5;

            detailsPanel.add(dataDodaniaDetail,constraints);

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 3;
            constraints.gridy = 1;
            constraints.weightx = 0.5;

            detailsPanel.add(dataZakonczeniaDetail,constraints);








            detailsPanel.setPreferredSize(new Dimension(800,200));
            detailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Szczegóły zadania"));
            add(detailsPanel);
            pack();

            setLocationRelativeTo(null);



        }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void setDetails(String details) {
            this.details.setText(details);
        splitedDetails = details.split("\\|");

        this.autorDetail.setText(connection.showAuthor(splitedDetails[0]));
        this.zadanieDetail.setText(splitedDetails[1]);
        this.dataDodaniaDetail.setText(splitedDetails[2]);
        this.dataZakonczeniaDetail.setText(splitedDetails[3]);

        System.out.println("To mnie ciekawi" + splitedDetails[0] + " " + splitedDetails[1]);

    }






}
