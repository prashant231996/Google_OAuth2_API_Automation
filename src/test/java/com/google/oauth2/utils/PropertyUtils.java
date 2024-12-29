package com.google.oauth2.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
	//Following below singleton design pattern
	//Creating static object as we need only single copy
	private static Properties prop;
	
	//private constructor as we need to restrict object creation
	private PropertyUtils() throws IOException
	{
		File propertyFilePath=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		FileInputStream fis=new FileInputStream(propertyFilePath);
	    prop=new Properties();
		prop.load(fis);
	}
	
	//This method will create object of Properties file only once if not created
	public static String getPropertyValue(String key) throws IOException
	{
		if(prop==null)
		{
			PropertyUtils propUtil=new PropertyUtils();
		}
		return prop.getProperty(key);
	}	

}
