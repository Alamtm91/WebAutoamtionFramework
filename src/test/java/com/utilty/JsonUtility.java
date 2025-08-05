package com.utilty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.constants.Env;
import com.constants.Env.*;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

public class JsonUtility {

	public static String readJson(Env env)  {
		// TODO Auto-generated method stub
		Gson gson= new Gson();
		File jsonFile = new File(System.getProperty("user.dir")+"\\config\\config.json");
		FileReader jsonFR = null;
		try {
			jsonFR = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Config config = gson.fromJson(jsonFR, Config.class);
		Environment environment= config.getEnvironments().get("QA");
		System.out.println(environment);
		return environment.getUrl();

		
	}

}
