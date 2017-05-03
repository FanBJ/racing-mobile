package com.hy.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * POI操作Excel文件
 * 
 * @author FBJ
 * 
 */
public class ExcelUtils {
	/**
	 * 读取Excel文件，返回第一个工作薄中的内容
	 * 
	 * @param file
	 *            Excel文件
	 * @param ignoreRows
	 *            忽略的前几行
	 * @return
	 */
	public static List<String[]> readExcel(File file, int ignoreRows) {
		List<String[]> result = null;
		FileInputStream in = null;
		Workbook wb = null;
		try {
			in = new FileInputStream(file);
			// 加载文件
			wb = WorkbookFactory.create(in);
			result = getData(wb, ignoreRows);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != in)
					in.close();
				if (wb != null)
					wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * @param wb
	 * @param ignoreRows
	 * @return
	 */
	private static List<String[]> getData(Workbook wb, int ignoreRows) {
		List<String[]> result = new ArrayList<String[]>();
		Cell cell = null;
		Sheet st = wb.getSheetAt(0);// 只读取第一个工作薄中的内容
		int count = st.getLastRowNum();// 总行数
		for (int rowIndex = ignoreRows; rowIndex <= count; rowIndex++) {// 遍历行
			Row row = st.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			int cellCount = row.getLastCellNum();
			if (cellCount > 0) {
				String[] data = new String[cellCount];
				for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {// 遍历列
					cell = row.getCell(columnIndex);
					if (cell != null) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						data[columnIndex] = cell.getStringCellValue();
					}
				}
				result.add(data);
			}

		}
		return result;
	}

	public static boolean isExcel(String name) {
		return null == name ? false : ("xls".equals(name.substring(name.lastIndexOf(".") + 1))) || ("xlsx".equals(name.substring(name.lastIndexOf(".") + 1)));
	}
}
