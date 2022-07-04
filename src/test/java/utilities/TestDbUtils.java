package utilities;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestDbUtils {


    public static void main(String[] args) throws SQLException {



        DBUtility.createConnection();

        List<String> columnNames = DBUtility.getColumnNames("select * from users limit 10");

        System.out.println(columnNames);


        int rowCount = DBUtility.getRowCount();

        System.out.println(rowCount);


        List<List<Object>> resultAsListOfLists = DBUtility.getQueryResultAsListOfLists("select * from users limit 10");
         List<String> usernames = new ArrayList<>();
        for (List<Object> row : resultAsListOfLists) {
                 usernames.add((String)(row.get(1)));
        }


        System.out.println(usernames);


        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps("select * from users limit 10");


        for (Map<String, Object> row : queryResultListOfMaps) {
            System.out.println(row);
        }

        System.out.println(queryResultListOfMaps.get(6).get("email"));




        DBUtility.updateQuery("UPDATE users SET email='leon@gmail.com' where username='leon.schroeder'");


        String bobevans23 = DigestUtils.md5Hex("mickeymouse");

        System.out.println(bobevans23);


    }
}
