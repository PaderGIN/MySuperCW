package prod.mysupercw;

import java.sql.*;

public class DBconnector {
    public static void main(String[] args) throws SQLException {
        loginToGleb();
        ResultSet resultSet = (getDBConnection().createStatement().executeQuery("SELECT * FROM Teacher;"));
        if (resultSet.next()) System.out.println(resultSet.getInt("id")+ " " + resultSet.getString("name"));
    }
    private static void loginToGleb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection
                    = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2005_cw",
                    "std_2005_cw",
                    "std_2005_cw"
            );
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM Subjects");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));

                System.out.println(resultSet.getString("name"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection
                    = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2005_cw",
                    "std_2005_cw",
                    "std_2005_cw"
            );
            return connection;


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
