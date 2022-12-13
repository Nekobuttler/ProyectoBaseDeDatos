package GUIS;

import java.sql.*;

import javax.swing.*;

public class Student_Register   extends JFrame{

    private JTextField ano;
    private JTextField cedulaEstudiante;
    private JTextField telefono;
    private JCheckBox hombreCheckBox;
    private JCheckBox mujerCheckBox;
    private JTextField nameEstudiante;
    private JTextField apellidosEstudiante;
    private JComboBox mes;
    private JComboBox grupoCB;
    private JPanel panel  ;
    private JComboBox dia;


    public Student_Register() {



}

public void llenarData(){
        mes.addItem("Hola");

}


    public static void main(String[] args) {

        Student_Register SR =new Student_Register();
        SR.setContentPane(SR.panel);

        JFrame jFrame = new JFrame("App");
        jFrame.setContentPane( new Student_Register().panel);
        jFrame.setTitle("Hola");
        jFrame.setVisible(true);
        SR.llenarData();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "user_practice_1", "aaa123" );
            System.out.println("conectado");
            Statement stmt=con.createStatement();


            ResultSet rs=stmt.executeQuery("select * from tbmateria");
            while(rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  ");
            }


        }catch (Exception e ){
            System.out.println(e);
        }


        jFrame.pack();
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);


    }


}
