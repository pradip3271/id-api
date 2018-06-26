package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Helper {
	
	// Load file from given path and return as string
	public static String GenerateStringFromFile( String path ) {
		
		String fileString=null;
		
		try {
			fileString = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileString;
	}
	
	// Load file from given path and return as Properties
	public static Properties loadPropertyFile(String path) {
		
		Properties prop = new Properties();
		FileInputStream fisResources;
		try {
			fisResources = new FileInputStream(path);
			prop.load(fisResources);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
		
	}

}
