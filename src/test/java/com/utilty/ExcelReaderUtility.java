package com.utilty;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {

	public static Iterator<User> readExcelFile(String fileName) {
		
		File file = new File(System.getProperty("user.dir")+ "//testData//"+fileName);
		XSSFWorkbook xssfwb;
		XSSFSheet xssfsheet;
		List<User> userList = null;
		Row row;
		Cell emailaddressCell;
		Cell passwordCell;
		User user ;
		try {
			xssfwb= new XSSFWorkbook(file);
			xssfsheet = xssfwb.getSheet("LoginTestData");
			
			Iterator<Row> rowIterator = xssfsheet.iterator();
			rowIterator.next();//skip the col name
			while(rowIterator.hasNext()) {
				row= rowIterator.next();
				emailaddressCell=row.getCell(0);
				passwordCell = row.getCell(1);
				user = new User(emailaddressCell.toString(), passwordCell.toString());
				userList.add(user);
				xssfwb.close();
				
				
				
			}
			
			
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList.iterator();
	}
}
