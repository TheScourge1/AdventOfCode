package org.adventOfCode.util;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	
	public static List<String> readStringsFromFile(String filePath) throws Exception
	{
		List<String> result = new ArrayList<String>();
		
		try {
		    BufferedReader in = new BufferedReader(new FileReader(filePath));
		    String str;
		    while ((str = in.readLine()) != null) {
		    	result.add(str);
		    }
		    in.close();
		} catch (IOException e) {
			return null;
		}
		
		return result;
	}

}
