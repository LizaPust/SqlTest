import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.Properties;

public class ShowTable {
    /**
     * JDBC Driver and database url
     */
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/shop";

    /**
     * User and Password
     */
    static final String USER = "root";
    static final String PASSWORD = "";
    public static void main(String[] args) throws ClassNotFoundException, SQLException, UnsupportedEncodingException, InstantiationException, IllegalAccessException {

        Connection connection = null;
        Statement statement = null;

        Properties props = new Properties();
        try {
            Class.forName(JDBC_DRIVER).newInstance();
            props.put("useUnicode","true");
            props.put("characterEncoding","utf8");
            props.put("user", USER);
            props.put("password", PASSWORD);

            connection = DriverManager.getConnection(DATABASE_URL, props);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Registering JDBC driver...");

        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Creating database connection...");
        //connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement...");
        statement = connection.createStatement();

        String sql;
        sql = "SELECT * FROM client";

        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("Retrieving data from database...");
        System.out.println("\nclients:");
        while (resultSet.next()) {
            int id = resultSet.getInt("Client_id");
            String name = resultSet.getString("Client_NAME");
            String adress = resultSet.getString("Adress");
            String phoneNumber = resultSet.getString("Phone_number");

            System.out.println("\n================\n");
            System.out.println("id: " + id);


           // PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, "Cp1252"), true);

           // pw.println(name);
            System.out.println(("Name: " + name));
           // String s = "наши";
            //System.out.println("System.out puts: " + s);
            //pw.println("PrintWriter puts: " + s);


            System.out.println("Adress: " + adress);
            System.out.println("Phone number: " + phoneNumber);


            String ru = "Русский язык";
            PrintStream ps = new PrintStream(System.out, true, "Cp866");
            System.out.println(ru.length());
            System.out.println(ru);
            ps.println(ru);
            ps.println(name);
            System.out.println("GGGGG");
            System.out.println("\u0394");
        }

        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        statement.close();
        connection.close();
    }
}