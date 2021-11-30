package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DBConnection {
    String q;
    String author;
    String login, haslo;
    HashMap<String, String> auth = new HashMap<>();
    String trescZadania;
    String autor;
    String czas;
    ArrayList <String> zadania = new ArrayList<>();

    public ArrayList<String> showAllQuest()
    {
        try(Connection con = getConnection()){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM zadania");

            zadania.clear();
            trescZadania = "";
            while(rs.next()) {

                 trescZadania=rs.getString(3);


                 zadania.add(trescZadania);

            }

            System.out.println("To są właśnie zadania " + zadania);


        }catch(SQLException e)
        {
            e.printStackTrace();
        }


        return zadania;



    }

    public void addQuest(String quest, String date)
    {
        try(Connection con = getConnection()){
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO zadania (id_login, zadanie, data_zakonczenia ) VALUES ('1', '"+ quest + "','"+ date +"')");


        }catch(SQLException e)
        {
            e.printStackTrace();
        }


    }

    public void deleteQuest(String quest)
    {

        try(Connection con = getConnection()){
            Statement statement = con.createStatement();
            statement.executeUpdate("DELETE FROM zadania WHERE zadanie like '" + quest + "'");

        }catch(SQLException e)
        {
            e.printStackTrace();
        }


    }


    public void showAll()
    {

        try(Connection con = getConnection()){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM logowanie");


            while(rs.next()) {

                login = rs.getString(2);
                haslo = rs.getString(3);
                auth.put(login,haslo);


            }


        }catch(SQLException e)
        {
            e.printStackTrace();
        }



    }
    Connection getConnection()
    {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://arjuna.db.elephantsql.com/uazcjixe", "uazcjixe", "mzOUVcrYWU40MGNIA_17Za9GKNHcTL1U");
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public boolean logIn(String log, String pass) {


        System.out.println(auth.get(log));
        System.out.println(auth.containsKey(log));


        if (auth.containsKey(log) && auth.get(log).equals(pass)) {
            System.out.println("Zalogowany");
            return true;

        } else {
            System.out.println("Niezalogowany");
            return false;
        }


    }


    public String showOnlyOne(String quest)
    {

        try(Connection con = getConnection()){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM zadania WHERE zadanie like '" + quest + "'");


            while(rs.next()) {

                q=rs.getString(2) + "|"  + rs.getString(3) + "|" + rs.getString(4) + "|" + rs.getString(5);

                System.out.println(q);
            }


        }catch(SQLException e)
        {
            e.printStackTrace();
        }
            return q;
    }


    public String showAuthor(String id)
    {
        try(Connection con = getConnection()){
            Statement statement = con.createStatement();
           // System.out.println("TO JEST TO ID " + id);
            ResultSet rs = statement.executeQuery("SELECT login FROM `logowanie` WHERE id="+id);
          //  System.out.println("KESTEM TU");

            while(rs.next()) {

               author = rs.getString(1);

                System.out.println("autor " + author);
            }


        }catch(SQLException e)
        {
            e.printStackTrace();
        }


        return author;
    }

}