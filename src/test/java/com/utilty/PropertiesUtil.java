package com.utilty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtil {
	
	public static String readProperty(Env env, String propertyName)  {
		
		
		File propfile = new File(System.getProperty("user.dir")+"\\config\\"+ env +".properties");
		FileReader fr=null;
		try {
			fr = new FileReader(propfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Properties properties = new Properties();
		try {
			properties.load(fr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = properties.getProperty(propertyName);
		return value;
	}

}
