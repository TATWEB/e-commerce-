package utilities;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestDbUtils {

    public static void main(String[] args) throws SQLException {


        //Create Connection
        // can be used in BeforeSuit annotation(TestNG), in Hooks(Cucumber)
        DBUtility.createConnection();


        // Get column names
        List<String> columnNames = DBUtility.getColumnNames("select * from users limit 10");
        // we pass the query limiting them to 10 first users
        System.out.println(columnNames);


        // Get row count
        int rowCount = DBUtility.getRowCount();
        System.out.println(rowCount);

        // Get QueryResult as List of List
        List<List<Object>> resultAsListOfLists = DBUtility.getQueryResultAsListOfLists("select * from users limit 10");
        // returns List of List<Object> because the result could be of any type

            // Get the usernames out of the table
        List<String> usernames = new ArrayList<>();
        for (List<Object> row : resultAsListOfLists) {
            usernames.add((String) row.get(1));  // 0-based, we have to cast it to String, as the return type before is Object
        }
        System.out.println(usernames);



        // Get QueryResult as List of Maps
        // the difference to List of List is that here we have the name of the rows
         List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps("select * from users limit 10");
        for (Map<String, Object> row : queryResultListOfMaps) {
            System.out.println(row);
        }
        // Get specific Data from the Map
        System.out.println(queryResultListOfMaps.get(6).get("email")); // 0 bases - first get row, then get column "email"



        // Update Query- updates the value (create, update, delete)
        DBUtility.updateQuery("UPDATE users SET email = 'leon123@gmail.com' WHERE username='leon.sc hroeder'");  // and exception will be thrown here



        // Close connection - we should close connection every time.
        // Close method can be used in AfterSuit annotation(TestNG), in Hooks(Cucumber)


        String md5HexPass = DigestUtils.md5Hex("markzucker66");
        System.out.println(md5HexPass);

    }
}
