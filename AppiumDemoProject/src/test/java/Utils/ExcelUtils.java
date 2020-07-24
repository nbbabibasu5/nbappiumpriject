package Utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	static String projectPath;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	public ExcelUtils(String excelPath, String sheetName) {
		
		try{
			
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	
	public static int getRowCount(){
		
		int rowCount=0;
		
		try{
			
			rowCount = sheet.getPhysicalNumberOfRows();
			
		}
		
		catch(Exception exp){
			
			exp.getMessage();
		}
		
		return rowCount;
		
	}
	
	public static int getColCount(){
		
		int colCount =0;
		
		try{
			
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			
		}
		
		catch(Exception exp){
			
			exp.getMessage();
		}
		
		return colCount;
		
	}
	
	public static String getCellDataString(int rowNum, int colNum){
		
		String cellData=null;
		
		try{
			
			//int rowCount= sheet.getPhysicalNumberOfRows();
			
			cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			
		}
		
		catch(Exception exp){
			
			exp.getMessage();
		}
		
		return cellData;
		
	}
	
	public static double getCellDataNumber(int rowNum, int colNum){
		
		double cellData=0;
		
		try{
			
			//int rowCount= sheet.getPhysicalNumberOfRows();
			
			cellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
	
		}
		
		catch(Exception exp){
			
			exp.getMessage();
		}
		
		return cellData;
		
	}

}
