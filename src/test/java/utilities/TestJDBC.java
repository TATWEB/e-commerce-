package utilities;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

public class TestJDBC {

    public static void main(String[] args) throws SQLException {

        // 1st. Step - > Create a connection

        // url syntax for postgresql -> jdbc:postgresql://host/database
        // url syntax for oracle -> jdbc:oracle:<drivertype>:@database
        //     example is           jdbc:oracle:thin:@myhost:1521:orcl

        // url sytax for mysql -> jdbc:mysql://host/database


        String url = "jdbc:mysql://db-duotech.cc652zs7kmja.us-east-2.rds.amazonaws.com/duotify"; // this is the syntax for writing th url
        // here we pass the url to the database and / slash the name of the database schema we are interested in
        String username = "duotech";
        String pass = "duotech2021";
        Connection connection = DriverManager.getConnection(url, username, pass);  // requires 3 things, the return type is Connection interface
        System.out.println("Connection successful!");


        // 2nd Step -> Create Statement (query)
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        // ResultSet.TYPE_SCROLL_INSENSITIVE the value inside the parameter gives us the option to move up and down (scrolling) through the rows
        // ResultSet.CONCUR_UPDATABLE give the option to update the table, when  I send the statement that should update the data


        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");// executeQuery method is for SELECT statements
        // executeQuery method works only for Select query
        // for Create, Update, Delete use    executeUpdate

        //Move to the next row
        resultSet.next(); // moves the cursor to the next row
        // ( we are on the fist row of the database, as by default it goes to the column names)
        // Moves the cursor forward one row from its current position.
        // A ResultSet cursor is initially positioned before the first row;
        // the first call to the method next makes the first row the current row;
        // the second call makes the second row the current row, and so on.


        // Get the value of a specific cell
        String string = resultSet.getString(2);  // returns String specifically instead of getObject
        String string2 = (String) resultSet.getObject(2);  // or we use getObject (generic type) and cast it to String
        // it is better to use getObject, as not everything are stored in String data type.
        System.out.println(resultSet.getObject(2)); // result is 1. In JDBC the indexes start from 1


        //Go to the last row and find out how many rows are there
        resultSet.last();  // moves the cursor to the last row
        int rowCount = resultSet.getRow();// returns the current row number, in this case the last row
        System.out.println(resultSet.getObject(2));


        // Go to the first row
        resultSet.first(); // moves the cursor to the first row
        System.out.println(resultSet.getObject(2));

        // Go to the wanted row
        resultSet.absolute(7); //  moves the cursor to the indicated row. 1 based index
        System.out.println(resultSet.getObject(2));

        // Go the header and footer rows of the table
        resultSet.beforeFirst();  // moves the cursor to the position before the first row
        resultSet.afterLast();  // moves the cursor to the position after the last  row

        resultSet.first();
        System.out.println(resultSet.getObject(2));


        resultSet.beforeFirst();

//        for (int i = 1; i <rowCount ; i++) {
//            resultSet.absolute(i);    // moves the cursor
//            System.out.println(resultSet.getObject(2));  // all results of the 2nd column
//
//        }

        // Another way to print out the second column
        while (resultSet.next()) {    // return falls if there are no more rows
            System.out.println(resultSet.getObject(2));
        }


        // Find out the number of columns

        ResultSetMetaData metaData = resultSet.getMetaData();// returns metadata- data about data
        int columnCount = metaData.getColumnCount();  // metaData contains information about the number of columns

        resultSet.beforeFirst();


        // We use nested loop to print out the entire table
        for (int i = 1; i <= rowCount; i++) {    // or outer loop can be also while loop
            resultSet.absolute(i);   // or resultSet.next() both do the same thing -> move the cursor to the next row
            for (int j = 1; j <= columnCount; j++) {  // inner loop should be for loop
                System.out.print(resultSet.getObject(j) + "\t");
            }
            System.out.println();
        }

        // Updating the data
        statement.executeUpdate("update users Set email ='User564@gmail.com' where  username ='ji3v53!n'");
        // here we pass actually the statement that we use in SQL
        // executeUpdate is used for Update, Create, Delete


        String md5HexPass = DigestUtils.md5Hex("markzucker66");
        System.out.println(md5HexPass);
    }
}


// but we don't need to do it by ourselves, instead we use the DBUtility