package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;
import java.awt.event.*;

public class Checkout extends JFrame implements ActionListener {
    
    Choice ccustomer;
    JLabel lblcheckouttime;
    JButton check,checkout,back;
    JTextField lblroomnumber,lblcheckintime;
    Checkout() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel text = new JLabel("Checkout");
        text.setFont(new Font("Tahoma",Font.PLAIN,20));
        text.setBounds(90,20,250,30);
        text.setForeground(Color.BLUE);
        add(text);
        
        JLabel lblid = new JLabel("Customer Id");
        lblid.setFont(new Font("Tahoma",Font.PLAIN,15));
        lblid.setBounds(30,85,100,20);
        add(lblid);
        
        ccustomer = new Choice();
        ccustomer.setBounds(150,83,150,25);
        add(ccustomer);
        
        try {
            Conn c = new Conn();
            ResultSet rs =c.s.executeQuery("select * from customer");
            while(rs.next()) {
                ccustomer.add(rs.getString("number"));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel tick = new JLabel(i3);
        tick.setBounds(100,43,450,100);
        add(tick);
        
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setFont(new Font("Tahoma",Font.PLAIN,15));
        lblroom.setBounds(30,140,100,20);
        add(lblroom);
        
        lblroomnumber = new JTextField();
        lblroomnumber.setFont(new Font("Tahoma",Font.PLAIN,15));
        lblroomnumber.setBounds(150,140,100,20);
        add(lblroomnumber);
        
        JLabel lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setFont(new Font("Tahoma",Font.PLAIN,15));
        lblcheckin.setBounds(30,195,100,20);
        add(lblcheckin);
        
        lblcheckintime = new JTextField();
        lblcheckintime.setFont(new Font("Tahoma",Font.PLAIN,12));
        lblcheckintime.setBounds(150,195,190,20);
        add(lblcheckintime);
        
        JLabel lblcheckout = new JLabel("Checkout Time");
        lblcheckout.setFont(new Font("Tahoma",Font.PLAIN,15));
        lblcheckout.setBounds(30,250,100,20);
        add(lblcheckout);
        
        Date date = new Date();
        lblcheckouttime = new JLabel("" +date);
        lblcheckouttime.setFont(new Font("Tahoma",Font.PLAIN,15));
        lblcheckouttime.setBounds(150,250,190,20);
        add(lblcheckouttime);
        
        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        check.setBounds(30,300,90,30);
        add(check);
        
        checkout = new JButton("Checkout");
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.setBounds(130,300,90,30);
        checkout.addActionListener(this);
        add(checkout);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230,300,90,30);
        back.addActionListener(this);
        add(back);
              
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i5 = i4.getImage().getScaledInstance(400, 250,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image = new JLabel(i6);
        image.setBounds(340,20,450,300);
        add(image);
        
        setBounds(250,130,830,400);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == check) {
            String id = ccustomer.getSelectedItem();
            String query = "select * from customer where number = '"+id+"'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()) {
                    lblroomnumber.setText(rs.getString("room"));
                    lblcheckintime.setText(rs.getString("checkintime"));
                }
            }
             catch (Exception e) {
             e.printStackTrace();
            }
        }
        else if (ae.getSource() == checkout) {
            String query1 = "delete from customer where number = '"+ccustomer.getSelectedItem()+"'";
            String query2 = "update room set availability = 'Available' where roomnumber = '"+lblroomnumber.getText()+"'" ;
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null,"Checkout done");
                
                setVisible(false);
                new Reception();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else {
            setVisible(false);
            new Reception();
        }
    }
            
    
    public static void main(String [] agrs) {
        new Checkout();
    }
    
}
