package com.andy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.iterators.EntrySetMapIterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.andy.common.BusinessName;

public class ExcelUtil {

	// 读取excel文件 返回list<Map>

	// 先读取首行，获取表头
	// 然后遍历数据，并根据数据位置对应表头
	

	public static ArrayList<Map<String, String>> readExcelByFilePath(String filePath) {
		ArrayList<Map<String, String>> lm = null;
		Workbook wb = getWorkBookByFilePath(filePath);
		Sheet sheet1 = wb.getSheetAt(0);
		// 获取有效行数
		int rowcount = sheet1.getLastRowNum();

		if (rowcount < 1) {
			return lm;
		} else {
			// 获取表头
			Row row1 = sheet1.getRow(0);
			List<String> businessNameList = getBusinessNameList(row1);

			if (businessNameList.size() < 1)
				return lm;

			System.out.println("businessNameList 的 size：" + businessNameList.size());
			lm = new ArrayList<Map<String, String>>();
			// 获取数据内容
			for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
				Map<String, String> map = new HashMap<>();
				// 循环每一行数据
				Row row = sheet1.getRow(i);
				// 根据表头的长度和内容循环获取cell内容
				for (int c = 0; c < businessNameList.size(); c++) {
					Cell cell = row.getCell(c);
					String cellValue = getCellVal(cell);
					//String cellValue = getCellStringVal(cell);
					// 将cell内容的key-value值放入map中
					map.put(businessNameList.get(c), cellValue);
				}

				lm.add(map);
			}

		}
		return lm;
	}
	
	public static List<Map<String,String>> readExcelByFile(MultipartFile file) {
		//ArrayList<Map<String, String>> lm = null;
		List<Map<String,String>> lm = null;
		Workbook wb = getWorkBook(file);
		Sheet sheet1 = wb.getSheetAt(0);
		// 获取有效行数
		int rowcount = sheet1.getLastRowNum();

		if (rowcount < 1) {
			return lm;
		} else {

			// 获取表头
			Row row1 = sheet1.getRow(0);
			List<String> businessNameList = getBusinessNameList(row1);

			if (businessNameList.size() < 1)
				return lm;

			System.out.println("businessNameList 的 size：" + businessNameList.size());
			lm = new ArrayList<Map<String, String>>();
			// 获取数据内容
			for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
				Map<String, String> map = new HashMap<>();
				// 循环每一行数据
				Row row = sheet1.getRow(i);
				// 根据表头的长度和内容循环获取cell内容
				for (int c = 0; c < businessNameList.size(); c++) {
					Cell cell = row.getCell(c);
					String cellValue = getCellVal(cell);
					// 将cell内容的key-value值放入map中
					map.put(businessNameList.get(c), cellValue);
				}

				lm.add(map);
			}

		}
		return lm;
	}

	private static List<String> getBusinessNameList(Row row) {
		List<String> businessNameList = new ArrayList<>();
		for (Cell cell : row) {
			String cellValue = getCellVal(cell);
			if (cellValue == BusinessName.USERNAME.getDescChiness()) {
				businessNameList.add("username");
			}
			switch (BusinessName.getByDescChiness(cellValue)) {
			case USERNAME:
				businessNameList.add("username");
				break;
			case USERCODE:
				businessNameList.add("usercode");
				break;
			case TBLCLASS:
				businessNameList.add("tblclass");
				break;
			case INSTITUTE:
				businessNameList.add("institute");
				break;
			case SEX:
				businessNameList.add("sex");
				break;

			default:
				businessNameList.add("cellValue");
				break;
			}
		}
		return businessNameList;
	}

	/**
	 * 解析Excel文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public static HSSFWorkbook parseExcel(String filePath) {
		System.out.println("filePath======>" + filePath);
		FileInputStream is;
		HSSFWorkbook wb = null;
		File f = new File(filePath);
		String type = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
		try {
			is = new FileInputStream(f);
			if (type.equals("xlsx")) {
				wb = new HSSFWorkbook(is);
			} else {
				POIFSFileSystem fs = new POIFSFileSystem(is);
				HSSFWorkbook hswb = new HSSFWorkbook(fs);
				wb = (HSSFWorkbook) hswb;
			}
			return wb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Workbook getWorkBook(MultipartFile file) {
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		try {
			// 获取excel文件的io流
			InputStream is = file.getInputStream();
			// 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if (fileName.endsWith("xls")) {
				// 2003
				workbook = new HSSFWorkbook(is);
			} else if (fileName.endsWith("xlsx")) {
				// 2007
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook;
	}

	public static Workbook getWorkBookByFilePath(String filePath) {
		// 获得文件名
		File file = new File(filePath);
		String fileName = file.getName();
		// 创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		try {
			// 获取excel文件的io流
			InputStream is = new FileInputStream(file);
			// 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if (fileName.endsWith("xls")) {
				// 2003
				workbook = new HSSFWorkbook(is);
			} else if (fileName.endsWith("xlsx")) {
				// 2007
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook;
	}

	// 获取Excel列的值
	@SuppressWarnings({ "unused" })
	public static String getCellVal(Cell cell) {

		String tempvalue = "";
		if (cell == null) {
			return tempvalue;
		}
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			if (cell == null) {
				tempvalue = "";
			} else {
				tempvalue = cell.getStringCellValue();
			}

		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

			long strCell =(long) cell.getNumericCellValue();
			//int num = Integer.valueOf(strCell);
			tempvalue = String.valueOf(strCell);
			/*if (strCell < 1) {
				DecimalFormat df = new DecimalFormat("0.000000000");
				tempvalue = df.format(strCell) + "";
			} else {
				// DecimalFormat df1 = new DecimalFormat("###.###");
				// tempvalue = df1.format(strCell)+"";
				tempvalue = String.valueOf(cell.getNumericCellValue());
			}*/

		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			tempvalue = "";
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			try {
				java.text.DecimalFormat formatter = new java.text.DecimalFormat("#,#########0.000000000");
				tempvalue = String.valueOf(formatter.format(cell.getNumericCellValue()));

			} catch (IllegalStateException e) {
				tempvalue = String.valueOf(cell.getRichStringCellValue());
			}
		}
		return tempvalue;
	}
	
	public static String getCellStringVal(Cell cell) {
		RichTextString cellVal =  cell.getRichStringCellValue();
		return cellVal.toString();
	}

	public static void main(String[] args) {
		String filePath = "C:\\Users\\Andy\\Desktop\\testExcel.xlsx";
		Workbook wb = getWorkBookByFilePath(filePath);
		Sheet sheet1 = wb.getSheetAt(0);
		// 获取有效行数
		int rowcount = sheet1.getLastRowNum();
		System.out.println(rowcount);
		Row row = sheet1.getRow(0);
		List<String> l = getBusinessNameList(row);
		ArrayList<Map<String, String>> lm = readExcelByFilePath(filePath);
		for (Map<String, String> map1 : lm) {
			for (Entry<String, String> entry : map1.entrySet()) {
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			}
		}
		System.out.println(l.toString());
	}

}
