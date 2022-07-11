package prod.mysupercw;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DBconnector {
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
//        String password = "321";
//        String hashpass = String.valueOf(password.hashCode());
//        String hashpass2 = String.valueOf(password.hashCode());
//        String hashpass3 = String.valueOf(password.hashCode());
//        System.out.println(hashpass);
//        System.out.println(hashpass2);
//        System.out.println(hashpass3);
//        System.out.println(getHashPassFromPass(password));
//        System.out.println(getHashPassFromPass(password));

        //loginToGleb();
//        ResultSet resultSet = (getDBConnection().createStatement().executeQuery("SELECT * FROM Teacher;"));
//        if (resultSet.next()) System.out.println(resultSet.getInt("id")+ " " + resultSet.getString("name"));
    }
//    private static void loginToGleb() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection
//                    = DriverManager.getConnection(
//                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2005_cw",
//                    "std_2005_cw",
//                    "std_2005_cw"
//            );
//            Statement statement = connection.createStatement();
//            statement.executeQuery("SELECT * FROM Subjects");
//            ResultSet resultSet = statement.getResultSet();
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("id"));
//
//                System.out.println(resultSet.getString("name"));
//            }
//
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

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
//    public static String getHashPassFromPass(String pass) throws NoSuchAlgorithmException{
//        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//        byte[] bytes = messageDigest.digest(pass.getBytes());
//        StringBuilder str = new StringBuilder();
//        for (byte b : bytes){
//            str.append(String.format("%01X",b));
//        }
//        return str.toString();
//    }
}
