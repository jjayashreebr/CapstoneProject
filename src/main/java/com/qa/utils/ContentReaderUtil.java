package com.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ContentReaderUtil {
	public static String getPropertyFile(String propertyFileName, String propertyName) throws IOException {
        String path = System.getProperty("user.dir");
    	Properties prop = new Properties();
        path = path + "/src/test/java/com/qa/resources/"+propertyFileName+".properties";
		//System.out.println(path);
		FileInputStream file;

		file = new FileInputStream(path);
		prop.load(file);
	    file.close();

	    return prop.getProperty(propertyName);

}
}
