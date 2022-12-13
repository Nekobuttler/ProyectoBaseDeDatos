package GUIS;

import java.sql.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student_Register   extends JFrame{

    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JCheckBox hombreCheckBox;
    private JCheckBox mujerCheckBox;
    private JTextField textField5;
    private JTextField textField6;
    private JComboBox comboBox1;
    private JTextField textField7;
    private JComboBox comboBox2;
    private JPanel panel  ;


public Student_Register() {



}

    public static void main(String[] args) {

        Student_Register SR =new Student_Register();
        SR.setContentPane(SR.panel);

        JFrame jFrame = new JFrame("App");
        jFrame.setContentPane( new Student_Register().panel);
        jFrame.setTitle("Hola");
        jFrame.setVisible(true);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "user_practice_1", "aaa123" );
            System.out.println("conectado");
            Statement stmt=con.createStatement();


            ResultSet rs=stmt.executeQuery("select * from tbmateria");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  ");
            }
//step5 close the connection object
            con.close();

        }catch (Exception e ){
            System.out.println(e);
        }


        jFrame.pack();
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


}
