package util;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

	public static String fileToText(String path)
	{
		try {
			return new String(Files.readAllBytes(Paths.get(path)));
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
