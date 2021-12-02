import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Scanner;

public class Test implements Command{
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
        in.close();

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
    }
}
