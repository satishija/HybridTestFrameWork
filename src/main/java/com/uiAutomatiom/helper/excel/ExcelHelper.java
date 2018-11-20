package com.uiAutomatiom.helper.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.uiAutomatiom.helper.logger.LogHelper;
import com.uiAutomatiom.resource.ResourceHelper;

public class ExcelHelper {

	private  Logger log = LogHelper.getLogger(ExcelHelper.class);

	public Object[][] getExcelData(String excelLocation, String sheetName) {
		try {
			Object dataSets[][] = null;
			FileInputStream file = new FileInputStream(new File(excelLocation));
			// Create work book instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Create sheet object
			XSSFSheet sheet = workbook.getSheet(sheetName);
			workbook.close();
			// Count no of active rows in the sheet
			int rowCount = sheet.getLastRowNum();
			// Count active columns in row
			int totalColumn = sheet.getRow(0).getLastCellNum();
			dataSets = new Object[rowCount + 1][totalColumn];

			// Iterate each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				i++;
				Row row = rowIterator.next();

				Iterator<Cell> cellIterator = row.iterator();

				int j = 0;
				while (cellIterator.hasNext()) {
					j++;
					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {
					case STRING:
						dataSets[i - 1][j - 1] = cell.getStringCellValue();
						break;
					case NUMERIC:
						dataSets[i - 1][j - 1] = cell.getNumericCellValue();
						break;
					case BOOLEAN:
						dataSets[i - 1][j - 1] = cell.getBooleanCellValue();
						break;
					case FORMULA:
						dataSets[i - 1][j - 1] = cell.getCellFormula();
					default:
						log.info("no matching enum data type found ");
						break;
					}
				}
			}
			return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateResults(String excelLocation, String sheetName, String testCaseName, String status,
			int colIndex) {

		try {

			FileInputStream file = new FileInputStream(new File(excelLocation));
			// Create work book instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Create sheet object
			XSSFSheet sheet = workbook.getSheet(sheetName.trim());
			workbook.close();
			// Count no of active rows in the sheet
			int rowCount = sheet.getLastRowNum();

			for (int i = 1; i < rowCount + 1; i++) {

				XSSFRow r = sheet.getRow(i);

				String cc = r.getCell(0).getStringCellValue();

				if (cc.contains(testCaseName)) {

					r.createCell(colIndex - 1).setCellValue(status);

					file.close();

					log.info("result updated..");

					FileOutputStream out = new FileOutputStream(new File(excelLocation));

					workbook.write(out);

					out.close();
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ExcelHelper excelHelper = new ExcelHelper();
		String excelLocation = ResourceHelper.getResourcePath("\\src\\main\\resources\\configfile\\testData.xlsx");
		// Object[][] data = excelHelper.getExcelData(excelLocation, "Login");
		excelHelper.updateResults(excelLocation, "TestScripts  ", "Login", "PASS", 2);
		excelHelper.updateResults(excelLocation, "TestScripts", "Satish", "PASS", 2);
		
	}

}
