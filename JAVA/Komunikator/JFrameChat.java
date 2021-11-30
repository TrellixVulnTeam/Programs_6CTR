package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;

public class JFrameChat extends JFrame implements ActionListener {
    int userid;
    Dimension screenSize;
    JPanel panel;
    JLabel roomLabel;
    JScrollPane chatPane;
    JTextArea chatRoomArea;
    JScrollPane sendPane;
    JLabel sendLabel;
    JTextArea toSendArea;
    JButton send;


    public JFrameChat(String title)
    {
        super(title);
        userid = Integer.parseInt(JOptionPane.showInputDialog("Podaj id usera"));
        try(Connection con = getConnection()){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT username FROM account WHERE userid = "+userid);
            if(rs.next())
            {
                setTitle(title + " " + rs.getString(1));
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        panel = new JPanel();
        roomLabel = new JLabel("      ChatRoom:      ");
        sendLabel = new JLabel("        send:        ");
        //roomLabel.setPreferredSize(new Dimension((int)screenSize.getWidth()/10,20));
        send = new JButton("send");
        send.setPreferredSize(new Dimension((int)screenSize.getWidth()/10,20));


        send.addActionListener(this);


        chatRoomArea = new JTextArea(((int)screenSize.getHeight()/2)/28,((int)screenSize.getWidth()/5)/15);
//        chatRoomArea.setPreferredSize(new Dimension(100,100));
        chatPane = new JScrollPane(chatRoomArea);
        chatRoomArea.disable();
        toSendArea = new JTextArea(3,(int)screenSize.getWidth()/5/15);
        sendPane = new JScrollPane(toSendArea);

        //send.setSize(new Dimension(screenSize.width/5, send.getHeight()));
        panel.add(roomLabel);
        panel.add(chatRoomArea);
        panel.add(chatPane);
        panel.add(sendLabel);
        panel.add(sendPane);
        panel.add(send);
        add(panel);


        setSize(screenSize.width/5, screenSize.height/2);
        setResizable(false);
        setVisible(true);
        Connection con = getConnection();
        setTextChatRoom();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if( actionEvent.getActionCommand().equals("send"))
        {
            String t = toSendArea.geText();
            toSendArea.setText("");
            Connection con = getConnection();
            LocalDateTime localDateTime = LocalDateTime.now();
            try(con){
                    Statement statement = con.createStatement();
                     statement.executeUpdate("INSERT INTO message (chatroomid,authorid,content,createdate) "
                            + "VALUES (1,"+ userid+",'"+t+"','"+localDateTime+"')");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //System.out.println(toSendArea.getText());

//       chatRoomArea.setText(t);
//     chatRoomArea.append(t);

    }


    private void setTextChatRoom()
    {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try{
                        Thread.sleep(500);
                        Connection con = getConnection();
                        Statement statement = con.createStatement();
                        ResultSet rs = statement.executeQuery("SELECT createdate, account.username, content " +
                                "FROM message LEFT JOIN account on account.userid=message.authorid WHERE message.chatroomid=1 ORDER BY createdate");
                        con.close();
                        chatRoomArea.setText("");
                        while (rs.next()) {
                            String s = rs.getString(1) + rs.getString(2)+":"+rs.getString(3);
                            chatRoomArea.append(s);
                            chatRoomArea.append("\n");
                        }
                        chatRoomArea.setCaretPosition(chatRoomArea.getDocument().getLength());
                    }catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    } catch (SQLException e) {
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
