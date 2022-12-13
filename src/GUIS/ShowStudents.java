package GUIS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class ShowStudents {

    private static Connection connect;
    public ShowStudents(){

        Conectar();
    }

    private JLabel title;
    private JButton button1;
    private JTextField CedulaID;
    private JPanel panel;
    private  JTextArea TFdata;
    private JLabel aaa;

    private List<Materias> List;

    private Connection connection;


    private CallableStatement enable_stmt;
    private CallableStatement disable_stmt;
    private CallableStatement show_stmt;



    public void show() throws SQLException
    {
        int done = 0;

        show_stmt.registerOutParameter( 2, java.sql.Types.INTEGER );
        show_stmt.registerOutParameter( 3, java.sql.Types.VARCHAR );

        for(;;)
        {
            show_stmt.setInt( 1, 32000 );
            show_stmt.executeUpdate();
            System.out.print( show_stmt.getString(3) );
            if ( (done = show_stmt.getInt(2)) == 1 ) break;
        }
    }

    public ShowStudents( Connection conn ) throws SQLException
    {
        enable_stmt = conn.prepareCall( "begin dbms_output.enable(:1); end;" );
        disable_stmt = conn.prepareCall( "begin dbms_output.disable; end;" );

        show_stmt = conn.prepareCall(
                "declare " +
                        " l_line varchar2(255); " +
                        " l_done number; " +
                        " l_buffer long; " +
                        "begin " +
                        " loop " +
                        " exit when length(l_buffer)+255 > :maxbytes OR l_done = 1; " +
                        " dbms_output.get_line( l_line, l_done ); " +
                        " l_buffer := l_buffer || l_line || chr(10); " +
                        " end loop; " +
                        " :done := l_done; " +
                        " :buffer := l_buffer; " +
                        "end;" );

    }

    /*
     * enable simply sets your size and executes
     * the dbms_output.enable call
     *
     */
    public void enable( int size ) throws SQLException
    {
        enable_stmt.setInt( 1, size );
        enable_stmt.executeUpdate();
    }

    /*
     * disable only has to execute the dbms_output.disable call
     */
    public void disable() throws SQLException
    {
        disable_stmt.executeUpdate();
    }

    public void close() throws SQLException
    {
        enable_stmt.close();
        disable_stmt.close();
        show_stmt.close();
    }





    public void Conectar() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "user_practice_1", "aaa123");
            System.out.println("conectado");


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void Mostrar() throws SQLException {
        DriverManager.registerDriver
                (new oracle.jdbc.driver.OracleDriver());

        Connection c = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:1521:orcl", "user_practice_1", "aaa123");

        try (CallableStatement call = c.prepareCall(
                "declare "
                        + "  num integer := 1000;"
                        // Adapt this as needed
                        + "begin "

                        // You have to enable buffering any server output that you may want to fetch
                        + "  dbms_output.enable();"

                        // This might as well be a call to third-party stored procedures, etc., whose
                        // output you want to capture
                        +"CRUD_SCHOOL_MANAGMENT.RECUPERAR_MATERIA;"+


                        // This is again your call here to capture the output up until now.
                        // The below fetching the PL/SQL TABLE type into a SQL cursor works with Oracle 12c.
                        // In an 11g version, you'd need an auxiliary SQL TABLE type
                        "  dbms_output.get_lines(?, num);"

                        // Don't forget this or the buffer will overflow eventually
                        + "  dbms_output.disable();"
                        + "end;"
        )) {
            call.registerOutParameter(1, Types.ARRAY, "DBMSOUTPUT_LINESARRAY");
            call.execute();

            Array array = null;

            try {
                array = call.getArray(1);
                System.out.println(Arrays.asList((Object[]) array.getArray()));
                Object[] materias = ((Object[]) array.getArray());
                for(int i =0;i<materias.length;i++){
                    if(materias[i] != null) {
                        TFdata.setText(TFdata.getText()+ "\n" + materias[i].toString());
                        aaa.setText(TFdata.getText() + materias[i].toString());
                    }else {
                        System.out.println("null");
                    }
                };
            }
            finally {
                if (array != null)
                    array.free();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



        public void GetDatos() {

            //tableStudents

            DefaultTableModel table = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String title[] = {"ID Materia", "Nombre Materia"};
            table.setColumnIdentifiers(title);

            //carga los datos de la base
            try {
                Conectar();
                Statement stmt = connection.createStatement();
                stmt.executeQuery(" SET SERVEROUTPUT ON;");
                ResultSet rs = stmt.executeQuery("EXECUTE CRUD_SCHOOL_MANAGMENT.RECUPERAR_MATERIA");
                while (rs.next()) {
                    TFdata.setText(TFdata.getText() + rs.getString(1));
                    System.out.println(rs.getString(1));

                }


            } catch (Exception e) {
                System.out.println(e);
            }
            try{
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("begin dbms_output.enable(); end;");


            try (CallableStatement call = connection.prepareCall(
                    "set serveroutput on;" +
                            "EXECUTE CRUD_SCHOOL_MANAGMENT.RECUPERAR_MATERIA;   "
            )) {
                call.registerOutParameter(1, Types.VARCHAR,
                        "DBMSOUTPUT_LINESARRAY");
                call.execute();
                String parametros = call.getString(1 );
                System.out.println(parametros);
                Array array = null;


}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }



            public static void main(String[] args) throws SQLException {

        JFrame jFrame = new JFrame("App");
        jFrame.setContentPane( new ShowStudents().panel);
        jFrame.setTitle("Hola");
        jFrame.setVisible(true);
        jFrame.setSize( 700, 1400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ShowStudents ss = new ShowStudents();
        ss.Mostrar();





    }


}
