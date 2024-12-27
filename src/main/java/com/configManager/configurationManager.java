package com.configManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class configurationManager {
	private Properties prop;
	private FileInputStream fis;
	
	public Properties initProp() {
		
		//String env = System.getProperty("env");
		prop = new Properties();
		try {
			//if (env == null) {
				System.out.println("no env is given...hence running tests on QA env... ");
				fis = new FileInputStream("./src/test/resources/config/config.properties");
//			} else {
//				System.out.println("Running tests on env: " + env);
//			}
//			switch (env.toLowerCase().trim()) {
//			case "qa":
//			    fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
//			    break;
//			case "dev":
//				fis = new FileInputStream("./src/test/resources/config/dev.config.properties");
//				break;
//			case "stage":
//				fis = new FileInputStream("./src/test/resources/config/stage.config.properties");
//				break;
//			default:
//				fis= new FileInputStream("./src/test/resources/config/config.properties");
//				break;
//		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}
