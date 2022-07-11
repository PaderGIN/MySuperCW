package prod.mysupercw;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DBconnector {
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {

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
