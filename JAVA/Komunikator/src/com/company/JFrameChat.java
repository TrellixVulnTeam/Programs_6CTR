package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;

public class JFrameChat extends JFrame implements ActionListener {
    String userName;

    int userid;
    double width, height;
    String userNameMessage = "Podaj nazwę użytkownika";
    Dimension screenSize ;
    JFrame errorMessagePane;
    JPanel panel;
    JLabel roomLabel;
    JScrollPane chatPane;
    JTextArea chatRoomArea;
    JLabel sendLabel;
    JScrollPane sendPane;
    JTextArea toSendArea;
    JButton send;
    JButton restart;
    JFrame regOrLog;



    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setUserNameMessage(String userNameMessage) {
        this.userNameMessage = userNameMessage;
    }





    public JFrameChat(String title){
        super(title);

//       int response =  JOptionPane.showConfirmDialog(regOrLog, JOptionPane.QUESTION_MESSAGE, "Czy chcesz się zarejestrować", JOptionPane.YES_NO_OPTION);
//
//
//
//        if(response==JOptionPane.YES_OPTION)
//        {
//            String newUserName = JOptionPane.showInputDialog("Podaj imie nowego użytkownika");
//            try(Connection con = getConnection()){
//                Statement statement = con.createStatement();
//                ResultSet resultSet =  statement.executeQuery("INSERT INTO account ");
//                if(resultSet.next())
//                {
//                    userid = Integer.parseInt(resultSet.getString("userid"));
//                    System.out.println("USER ID " + userid);
//                    setTitle(title +" " + userName);
//                }else if(!resultSet.next())
//                {
//
//                    JOptionPane.showMessageDialog(errorMessagePane, "Nie znaleziono użykownika o podanej nazwie " );
//
//                    new JFrameChat("Chat");
//                }
//
//
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//
//
//
//
//        }else if(response == JOptionPane.CLOSED_OPTION)
//        {
//            System.exit(0);
//        }else if (response==JOptionPane.NO_OPTION)
//        {

            userName = (JOptionPane.showInputDialog(errorMessagePane,userNameMessage));

            try(Connection con = getConnection()){
                Statement statement = con.createStatement();
                ResultSet rs =  statement.executeQuery("SELECT userid FROM account where username="+"'"+userName+"'");
                if(rs.next())
                {
                    userid = Integer.parseInt(rs.getString("userid"));
                    System.out.println("USER ID " + userid);
                    setTitle(title +" " + userName);
                }else if(!rs.next())
                {

                    JOptionPane.showMessageDialog(errorMessagePane, "Nie znaleziono użykownika o podanej nazwie " );

                    new JFrameChat("Chat");
                }


            }catch(SQLException e){
                e.printStackTrace();
            }

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            height =  getSize().getHeight();
            width = getSize().getWidth();
            screenSize=Toolkit.getDefaultToolkit().getScreenSize();
            panel = new JPanel();
            //roomLabel=new JLabel("        ChatRoom        ");
            //sendLabel=new JLabel("        Send        ");
            send = new JButton("send");
            send.setPreferredSize(new Dimension((int)screenSize.getWidth()/12,(int)screenSize.getHeight()/30));

            send.addActionListener(this);

            chatRoomArea = new JTextArea(20,32);
            chatRoomArea.disable();
            chatRoomArea.setFont(new Font("SansSerif", Font.BOLD, 10));
            chatPane = new JScrollPane(chatRoomArea);
            toSendArea = new JTextArea(2,(int)screenSize.getWidth()/4/15);
            sendPane = new JScrollPane(toSendArea);
            //  panel.add(roomLabel);
            panel.add(chatPane);
            // panel.add(sendLabel);
            panel.add(sendPane);
            panel.add(send);
            add(panel);



            setSize(screenSize.width/3,screenSize.height/2);
            setResizable(false);
            setVisible(true);
            setTextChatRoom();

 // OptionPane   }




    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("send") ) {
            String t = toSendArea.getText();
            toSendArea.setText("");

            Connection con = getConnection();
            try(con){
                Statement statement = con.createStatement();
                LocalDateTime localDateTime = LocalDateTime.now();
                System.out.println(localDateTime);
                statement.executeUpdate("INSERT INTO message (chatroomid,authorid,content,createdate) "
                        + "VALUES (1,"+ userid+",'"+t+"','"+localDateTime+"')");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }


        }

    }

    private void setTextChatRoom(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(4000);
                        Connection con = getConnection();
                        Statement statement = con.createStatement();
                        ResultSet rs = statement.executeQuery("SELECT createdate,account.username,content " +
                                "FROM message LEFT JOIN account on account.userid=message.authorid WHERE message.chatroomid=1 ORDER BY createdate");
                        con.close();
                        chatRoomArea.setText("");
                        while (rs.next()) {
                            String s= rs.getString(2)+": "+rs.getString(3)+ "                   " + rs.getString(1);
                            chatRoomArea.append(s);
                            chatRoomArea.append("\n");
                        }
                        chatRoomArea.setCaretPosition(chatRoomArea.getDocument().getLength());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        t.start();
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
}