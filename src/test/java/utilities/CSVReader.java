package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {



    public static Object[][] readFromCSV(String pathToFile) {


        List<String[]> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while((line = br.readLine()) != null){
                String[] row = line.split(",");

                list.add(row);}
        } catch (Exception e) {
            e.printStackTrace();
        }



        int columnSize = list.get(0).length;
        int row = list.size();
        Object [][] arr = new Object[row][columnSize];


        for (int i = 0; i < list.size(); i++) {
             arr[i] = list.get(i);
        }

        return arr;
    }
}
