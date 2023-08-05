package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelTest {
    public static void main(String[] args) {

        String path = System.getProperty("user.dir")+"/src/test/resources/testdata/TestData.xlsx";

        try {
            FileInputStream file = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet1 = workbook.getSheet("Sheet1");
//            System.out.println(sheet1.getRow(1).getCell(0).toString());
//            System.out.println(sheet1.getRow(1).getCell(1).toString());
//            System.out.println(sheet1.getRow(1).getCell(2).toString());
//            System.out.println(sheet1.getRow(1).getCell(3).toString());

//            sheet1.createRow(2).createCell(0).setCellValue("harsh");
//            sheet1.getRow(2).createCell(1).setCellValue("Patel");
//            sheet1.getRow(2).createCell(2).setCellValue("harsh@gmail.com");
//            sheet1.getRow(2).createCell(3).setCellValue("321 Lee ave");

            FileOutputStream output = new FileOutputStream(path);
            workbook.write(output);


        } catch (FileNotFoundException e) {
            System.out.println("Path for excel file is invalid");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExcelUtils.openExcelFile("TestData", "Sheet1");
        System.out.println(ExcelUtils.getValue(2,0));
    }
}
