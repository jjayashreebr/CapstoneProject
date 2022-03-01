package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

	public static void writeToExcel(int rowNum, String result) throws IOException {
		String filepath = System.getProperty("user.dir") + "/src/test/java/com/qa/resources/testData/Registration_Data.xlsx";
		File excel = new File(filepath);
		FileInputStream fis = new FileInputStream(excel);
		try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
			XSSFSheet ws = wb.getSheet("RegistartionFormDetails");

			XSSFRow row = ws.getRow(rowNum);
			Cell cell = row.createCell(6);
			cell.setCellValue(result);
			fis.close();
			
			FileOutputStream out = new FileOutputStream(new File(filepath));
			wb.write(out);
			wb.close();
			out.close();
			
			
		}
		
		
		

	}

	public static HashMap<Integer, HashMap<String, Object>> getExcelData() throws IOException {
		String filepath = System.getProperty("user.dir") + "/src/test/java/com/qa/resources/testData/Registration_Data.xlsx";
		
		File excelSheet = new File(filepath);
		FileInputStream fis = new FileInputStream(excelSheet);

		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet("RegistartionFormDetails");

		int rowNum = ws.getLastRowNum() + 1;
		int colNum = ws.getRow(0).getLastCellNum();
		System.out.println(rowNum + " " + colNum);

		Map<Integer, HashMap<String, Object>> customerInfo = new HashMap<Integer, HashMap<String, Object>>();

		for (int i = 1; i < rowNum; i++) {
			XSSFRow firstRow = ws.getRow(0);
			XSSFRow row = ws.getRow(i);
			HashMap<String, Object> map = new HashMap<String, Object>();
			for (int j = 0; j < colNum; j++) {
				Cell cell = row.getCell(j);
				Cell cellFirst = firstRow.getCell(j);

				if (cell != null) {
					switch (cell.getCellType()) {
					case NUMERIC:
						// System.out.println(cell.getNumericCellValue());
						String str = NumberToTextConverter.toText(cell.getNumericCellValue());
						map.put(cellFirst.getStringCellValue(), str);
						break;

					case STRING:
						// System.out.println(cell.getStringCellValue());
						map.put(cellFirst.getStringCellValue(), cell.getStringCellValue());
						break;
					default:
						map.put(cellFirst.getStringCellValue(), cell.getStringCellValue());
						break;
					}
				}

			}
			customerInfo.put(i, map);

		}
		wb.close();
		fis.close();
		return (HashMap<Integer, HashMap<String, Object>>) customerInfo;
	}
}
