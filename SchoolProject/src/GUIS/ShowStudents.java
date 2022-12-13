package GUIS;

import javax.swing.*;
import java.sql.*;
public class ShowStudents {

    private static Connection connect;
    public ShowStudents(){

        Conectar();
    }

    private JLabel title;
    private JTable tableStudents;
    private JButton button1;
    private JTextField CedulaID;
    private JPanel panel;

    public void Conectar() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "user_practice_1", "aaa123");
            System.out.println("conectado");


        } catch (Exception e) {
            System.out.println(e);
        }

    }



        public void GetDatos(){

        try {
            Statement sentency = connect.createStatement();

            try {
                ResultSet rs=sentency.executeQuery("select * from tbmaterias");
                while(rs.next()){
                    String Materia = rs.getInt(1) + rs.getString(2);
                    System.out.println(Materia);
                }
            }catch (Exception e ){
                System.out.println(e);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        }




    public static void main(String[] args) {

        JFrame jFrame = new JFrame("App");
        jFrame.setContentPane( new ShowStudents().panel);
        jFrame.setTitle("Hola");
        jFrame.setVisible(true);
        jFrame.setSize( 700, 1400);
        ShowStudents ss = new ShowStudents();
        ss.Conectar();
        ss.GetDatos();

    }


}
