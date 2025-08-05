package com.ui.dataProviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utilty.CSVReaderUtility;
import com.utilty.ExcelReaderUtility;

public class LoginDataProvider {

	
	@DataProvider(name="loginTestDataProvider")
	public Iterator<Object[]> logindtataProvider() throws FileNotFoundException {
		Gson gson = new Gson();
		File testDataFile = new File(System.getProperty("user.dir") + "\\testData\\logindata.json");
		FileReader filereader = new FileReader(testDataFile);
		TestData data =gson.fromJson(filereader, TestData.class);
		
		List<Object[]> dataToReturn = new ArrayList<>();
		for(User user :data.getData()) {
			dataToReturn.add(new Object[] {user});
		}
		return dataToReturn.iterator();
	}
	
	
	@DataProvider(name = "loginTestCSVDataProvider")
	public Iterator<User> loginCSVDataProvider() {
		return CSVReaderUtility.readCSVFile("loginData.csv");
	}
	
	
	@DataProvider(name = "loginTestExcelDataProvider")
	public Iterator<User> loginExcelDataProvider() {
		return ExcelReaderUtility.readExcelFile("LoginData.xlsx");
	}
}
