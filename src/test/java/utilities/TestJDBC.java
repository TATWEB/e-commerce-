package utilities;

import java.sql.*;

public class TestJDBC {

    public static void main(String[] args) throws SQLException {


        // url syntax for postgresql -> jdbc:postgresql://host/database
        // url syntax for oracle -> jdbc:oracle:<drivertype>:@<database>
        //                         jdbc:oracle:thin:@myhost:1521:orcl

        // url syntax for mysql -> jdbc:mysql://host/database


        String url = "jdbc:mysql://db-duotech.cc652zs7kmja.us-east-2.rds.amazonaws.com/duotify";
        String username = "duotech";
        String pass = "duotech2021";
        Connection connection = DriverManager.getConnection(url, username, pass);
        System.out.println("Connection successful!");

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");// executeQuery method is for SELECT statements

        resultSet.next(); // moves the cursor to the next row
        //A ResultSet cursor is initially positioned before the first row;
        // the first call to the method next makes the first row the current row;
        // the second call makes the second row the current row, and so on.

        String string = resultSet.getString(2);
        String string2 = (String)(resultSet.getObject(2));
        System.out.println(resultSet.getObject(2));  // In JDBC the indexes start from 1

        resultSet.last(); // moves the cursor to the last row
        int rowCount = resultSet.getRow();// returns the current row number, in his case last row

        System.out.println(resultSet.getObject(2));

        resultSet.first(); // moves the cursor to the first row
        System.out.println(resultSet.getObject(2));

        resultSet.absolute(7);  // moves the cursor to the indicated row
        System.out.println(resultSet.getObject(2));

        resultSet.beforeFirst();  //moves the cursor to the position before the first row
        resultSet.afterLast(); //moves the cursor to the position after the last row

        resultSet.first();
        System.out.println(resultSet.getObject(2));

        resultSet.beforeFirst();


//        for (int i = 1; i <=rowCount ; i++) {
//            resultSet.absolute(i); // moves the cursor
//            System.out.println(resultSet.getObject(2));
//
//        }

        while(resultSet.next()){ //returns false if there are no more rows
            System.out.println(resultSet.getObject(2));
        }

        // Find out the number of columns

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        resultSet.beforeFirst();

        for (int i = 1; i <=rowCount ; i++) {
             resultSet.absolute(i);
//            resultSet.next();
            for (int j = 1; j <=columnCount ; j++) {

                System.out.print(resultSet.getObject(j) + "\t");

            }

            System.out.println();

        }


        statement.executeUpdate("UPDATE users SET email='magdalena2021@gmail.com' where username='magdalena'");





    }
}
