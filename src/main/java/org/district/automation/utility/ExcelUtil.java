package org.district.automation.utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelUtil {

    private static final String EXCEL_PATH =
            System.getProperty("user.dir")
                    + "/src/test/resources/testdata/District_TestData.xlsx";

    /* ================= CREATE EXCEL WITH ALL TEST DATA ================= */

    public static void createTestDataExcel() {

        try {
            Workbook workbook = new XSSFWorkbook();

            /* ================= LOCATION DATA ================= */
            Sheet locationSheet = workbook.createSheet("LocationData");

            Row locHeader = locationSheet.createRow(0);
            locHeader.createCell(0).setCellValue("TestCase");
            locHeader.createCell(1).setCellValue("City");
            locHeader.createCell(2).setCellValue("State");
            locHeader.createCell(3).setCellValue("EntireLocation");

            Row locRow = locationSheet.createRow(1);
            locRow.createCell(0).setCellValue("TC_05");
            locRow.createCell(1).setCellValue("Pune");
            locRow.createCell(2).setCellValue("Maharashtra");
            locRow.createCell(3).setCellValue("Pune, Maharashtra");

            for (int i = 0; i < 4; i++) {
                locationSheet.autoSizeColumn(i);
            }

            /* ================= LOGIN DATA ================= */
            Sheet loginSheet = workbook.createSheet("LoginData");

            Row loginHeader = loginSheet.createRow(0);
            loginHeader.createCell(0).setCellValue("TestCase");
            loginHeader.createCell(1).setCellValue("Country");
            loginHeader.createCell(2).setCellValue("MobileNumber");
            loginHeader.createCell(3).setCellValue("ExpectedMessage");

            loginSheet.createRow(1).createCell(0).setCellValue("TC_22");
            loginSheet.getRow(1).createCell(1).setCellValue("France");
            loginSheet.getRow(1).createCell(2).setCellValue("12ab45@#");
            loginSheet.getRow(1).createCell(3).setCellValue("Numeric input only");

            loginSheet.createRow(2).createCell(0).setCellValue("TC_23");
            loginSheet.getRow(2).createCell(1).setCellValue("India");
            loginSheet.getRow(2).createCell(2).setCellValue("N/A");
            loginSheet.getRow(2).createCell(3).setCellValue("Country selected");

            loginSheet.createRow(3).createCell(0).setCellValue("TC_24");
            loginSheet.getRow(3).createCell(1).setCellValue("India");
            loginSheet.getRow(3).createCell(2).setCellValue("94301548445");
            loginSheet.getRow(3).createCell(3).setCellValue("Invalid number error");

            loginSheet.createRow(4).createCell(0).setCellValue("TC_25");
            loginSheet.getRow(4).createCell(1).setCellValue("India");
            loginSheet.getRow(4).createCell(2).setCellValue("9430154833");
            loginSheet.getRow(4).createCell(3).setCellValue("Enter OTP");

            for (int i = 0; i < 4; i++) {
                loginSheet.autoSizeColumn(i);
            }

            File file = new File(EXCEL_PATH);
            file.getParentFile().mkdirs();

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);

            workbook.close();
            fos.close();

            System.out.println("✅ Excel created for ALL test cases");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ================= READ CELL DATA ================= */

    public static String getCellData(String sheet, int row, int col) {
        try {
            FileInputStream fis = new FileInputStream(EXCEL_PATH);
            Workbook workbook = new XSSFWorkbook(fis);
            DataFormatter formatter = new DataFormatter();

            String value = formatter.formatCellValue(
                    workbook.getSheet(sheet).getRow(row).getCell(col));

            workbook.close();
            fis.close();
            return value;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}