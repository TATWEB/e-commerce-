package utilities;


import io.cucumber.java.an.E;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestApachePOI {

    public static void main(String[] args) throws IOException {
        // Workbook -> Sheet -> Row -> Cell
        // HSSF version works with older  file types before 2003 (doc, xls)
        // XSSF version works with newer  file types after 2003 (docx, xlsx)

//        XSSFWorkbook workbook;
//        XSSFSheet sheet;
//        XSSFRow row;
//        XSSFCell cell;

        FileInputStream fis = new FileInputStream("testDataEcommerce.xlsx");
        // this class reads the information as a Stream of bytes
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        XSSFRow row = sheet.getRow(1);  // Apache POI method indexes are 0 based
        XSSFCell cell = row.getCell(1);

        System.out.println(cell);



        // Get Number of row
         int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
         int lastRowNum = sheet.getLastRowNum();
        System.out.println(physicalNumberOfRows);
        System.out.println(lastRowNum);


        // Get Number of columns
         XSSFRow columnRow = sheet.getRow(0);
         int physicalNumberOfCells = columnRow.getPhysicalNumberOfCells();
        System.out.println(physicalNumberOfCells);


        // Get the content of the sheet
        for (int i = 0; i < physicalNumberOfRows; i++) {
            for (int j = 0; j < physicalNumberOfCells; j++) {
                System.out.print(sheet.getRow(i).getCell(j) + "\t");
            }
            System.out.println();

        }

         // Write back to the file

             XSSFCell cellTestStatus = sheet.getRow(1).getCell(6);
             // first we need to get the cell where changes are done

            cellTestStatus.setCellValue("PASSED");
            // and then make changes to that cell

            FileOutputStream fos = new FileOutputStream("testDataEcommerce.xlsx");  //= closing the file and saving the changes
            workbook.write(fos);  // we save the changes





            ExcelUtils excelUtils = new ExcelUtils("testDataEcommerce.xlsx", "Sheet1");
            // it initializes the workbook and the sheet

            System.out.println(excelUtils.getCellData(2,1));  // 0 based

            String[][] dataAs2DArray = excelUtils.getDataAs2DArray();
            System.out.println(Arrays.deepToString(dataAs2DArray));


             List<Map<String, String>> dataAsListOfMaps = excelUtils.getDataAsListOfMaps();

            for (Map<String, String> dataAsListOfMap : dataAsListOfMaps) {
                System.out.println(dataAsListOfMap);
            }

            excelUtils.setCellData("Bitcoin","Status",1);
        }
    }

