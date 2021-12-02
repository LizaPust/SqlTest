import java.sql.SQLException;
import java.util.Scanner;

public class ShowTable {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);
    System.out.println("действие");
    String commandName = in.nextLine();
    Command command = ChoisesFactory.getCommand(commandName);
    command.process();
    in.close();
    }
}