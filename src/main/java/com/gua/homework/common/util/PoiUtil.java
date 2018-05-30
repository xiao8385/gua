package com.gua.homework.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gua.homework.obj.PoiExcelObj;

public class PoiUtil {

	final static Logger logger = LoggerFactory.getLogger(PoiUtil.class);

	public static boolean isExcel2003(String fileName) {
		return fileName.endsWith(Constant.EXCEL_2003_SUBFIX);
	}

	public static boolean isExcel2007(String fileName) {
		return fileName.endsWith(Constant.EXCEL_2007_SUBFIX);
	}

	public static List<String[]> readFromExcelFile(final String fileName, final String sheetName) {

		List<String[]> l = new ArrayList<String[]>();

		logger.debug("readContent:{}", fileName);

		try {
			Workbook wb = null;
			if (isExcel2007(fileName)) {
				wb = new XSSFWorkbook(new FileInputStream(fileName));
			} else if (isExcel2003(fileName)) {
				wb = new HSSFWorkbook(new FileInputStream(fileName));
			} else {
				return l;
			}
			// Sheet sheet = wb.getSheetAt(0);
			Sheet sheet = wb.getSheet(sheetName);
			logger.debug("sheet:{}", sheet);
			if (sheet == null) {
				return l;
			}

			Iterator<Row> rows = sheet.rowIterator();
			logger.debug("rows:{}", rows);

			while (rows.hasNext()) {

				Row row = rows.next();

				String[] arr = new String[row.getLastCellNum() - row.getFirstCellNum()];
				for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {

					Cell cell = row.getCell(i);
					if (cell == null) {
						arr[i] = String.valueOf(Constant.EMPTY);
						continue;
					}

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						// arr[i] = String.valueOf(cell.getNumericCellValue());
						arr[i] = NumberToTextConverter.toText(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						arr[i] = String.valueOf(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						arr[i] = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						arr[i] = String.valueOf(cell.getCellFormula());
						break;
					case Cell.CELL_TYPE_BLANK:
					default:
						arr[i] = String.valueOf(Constant.EMPTY);
						break;
					}
				}

				l.add(arr);
				logger.debug("Row #{} {}", row.getRowNum(), StringUtil.transArrToStr(arr));
			}
		} catch (IOException ex) {
			logger.error("Read Excel file:" + fileName + " encounter error", ex);
		}

		return l;
	}

	public static void writeToExcelFile(Workbook wb, List<String[]> l, String sheetName, Font font) {

		Sheet sheet = wb.createSheet(sheetName);
		CellStyle style = wb.createCellStyle();
		style.setFont(font);

		for (int numOfRow = 0; numOfRow < l.size(); numOfRow++) {

			Row row = sheet.createRow(numOfRow);

			String[] sArr = l.get(numOfRow);
			if (sArr == null || sArr.length == 0) {
				logger.debug("When create row encounter blank value!");
				continue;
			}

			for (int numOfCol = 0; numOfCol < sArr.length; numOfCol++) {
				Cell cell = row.createCell(numOfCol);
				if (style != null) {
					cell.setCellStyle(style);
				}

				if (wb instanceof HSSFWorkbook) {
					cell.setCellValue(new HSSFRichTextString(sArr[numOfCol]));
				} else if (wb instanceof XSSFWorkbook) {
					cell.setCellValue(new XSSFRichTextString(sArr[numOfCol]));
				}
				sheet.autoSizeColumn(numOfCol);
			}

			logger.debug("Row #{} {}", row.getRowNum(), l.get(numOfRow));
		}
		
	}

	public static void writeToExcelFile(PoiExcelObj p) {

		String fileName = p.getFileName();
		logger.debug("writeToFile:{}", fileName);

		if (p == null || p.isEmpty()) {
			logger.debug("No file created as no value need output!");
			return;
		}

		Workbook wb = null;
		if (isExcel2007(fileName)) {
			wb = new XSSFWorkbook();
		} else if (isExcel2003(fileName)) {
			wb = new HSSFWorkbook();
		} else {
			logger.debug("Invalid Excel file extension:{}", fileName);
			return;
		}

		Font font = wb.createFont();
		if (p.getFontSize() <=0 ) {
			font.setFontHeightInPoints((short) 30);
		} else {
			font.setFontHeightInPoints((short) p.getFontSize());
		}

		int i = 0;
		for (String sheet : p.getSheetList()) {
			writeToExcelFile(wb, p.getSheetContent(sheet), sheet, font);
			wb.setSheetOrder(sheet, i++);
		}

		try {
			FileOutputStream file = new FileOutputStream(fileName);
			wb.write(file);
			file.close();
			logger.debug("Excel file:{} created!", fileName);
		} catch (Exception e) {
			logger.error("Write Excel file:" + fileName + " encounter error", e);
		}
	}
}