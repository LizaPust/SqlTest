import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Scanner;

public class TestDelete implements Command{
    /**
     * JDBC Driver and database url
     */
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/shop";

    /**
     * User and Password
     */
    static final String USER = "root";
    static final String PASSWORD = "1111";

    @Override
    public void process() throws SQLException {

        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        Scanner in = new Scanner(System.in);
        System.out.println("таблица");
        String table = in.nextLine();
        //in.close();

        String sql = "SELECT * FROM " + table;
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        PrintStream out = System.out;

        if (rs != null) {
            while (rs.next()) {
                ResultSetMetaData rsmd = (rs.getMetaData());
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String name = rsmd.getColumnName(i);
                    out.print(name + "  ");

                    int type = rsmd.getColumnType(i);
                    if (type == Types.VARCHAR || type == Types.CHAR) {
                        out.println(rs.getString(i) + " ");
                    } else {
                        out.println(rs.getLong(i) + " ");
                    }
                }

                out.println();

            }
        }

        System.out.println("ведите значение");
        String vvod = in.nextLine();

        System.out.println("Removing record with id = "+ vvod);
        String SQL = "DELETE FROM " + table + " WHERE Item_Id = " + vvod;
        statement.executeUpdate(SQL);

        System.out.println("Getting records...");
        String s = "SELECT * FROM " + table;
        PreparedStatement statemint = connection.prepareStatement(s);
        ResultSet res = statemint.executeQuery();
        PrintStream o = System.out;

        if (res != null) {
            while (res.next()) {
                ResultSetMetaData rsmd = (res.getMetaData());
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String name = rsmd.getColumnName(i);
                    o.print(name + "  ");

                    int type = rsmd.getColumnType(i);
                    if (type == Types.VARCHAR || type == Types.CHAR) {
                        o.println(res.getString(i) + " ");
                    } else {
                        o.println(res.getLong(i) + " ");
                    }
                }

                o.println();

            }
        }
    }
}
