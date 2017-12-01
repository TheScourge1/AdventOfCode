package org.adventOfCode.code2016;

import java.util.List;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;





public class Excercise9 extends GenericExcercise {

	
	
	
	@Override
	public void executeTest() throws Exception {
		
		String testInput = "resource/Excercise9.txt";
		long result = getBasicFileLength(testInput);
		
		logger.debug("Result for excercise 9A:" + result);
		result = getAdvancedFileLength(testInput);
		logger.debug("Result for excercise 9B:" + result);

	}
	
	public long getBasicFileLength(String fileName) throws Exception
	{
		List<String> codeList = FileUtil.readStringsFromFile(fileName);
		
		return decodeString(codeList.get(0)).length();
	}
	
	public long getAdvancedFileLength(String fileName) throws Exception
	{
		List<String> codeList = FileUtil.readStringsFromFile(fileName);
		
		return getAdvancedStringLength(codeList.get(0));
	}
	
	
	public String decodeString(String line) throws Exception
	{
		StringBuilder result = new StringBuilder();
		
		int index = 0;
		while(line.substring(index).indexOf("(") >= 0)
		{
			int openBracketLocation = line.substring(index).indexOf("(");
			result.append(line.substring(index,index + openBracketLocation));
			int closeBracketLocation = line.substring(index).indexOf(")");
			String[] digitsToRepeat = line.substring(index + openBracketLocation+1,index + closeBracketLocation).split("x");
			if(digitsToRepeat.length != 2) 
				throw new Exception("Expecting 2 digits but found: "
					+line.substring(openBracketLocation+1, closeBracketLocation));
			else
			{
				int digitCount = Integer.parseInt(digitsToRepeat[0]);
				int repeatCount = Integer.parseInt(digitsToRepeat[1]);
				for(int i = 0; i < repeatCount;i++)
					result.append(line.substring(index + closeBracketLocation+1,index +  closeBracketLocation+1+digitCount));
				index = index + closeBracketLocation+1+digitCount;
			}
		}
		result.append(line.substring(index));
		
		return result.toString();
	}
	
	public long getAdvancedStringLength(String line) throws Exception
	{
		long result = 0;
		//logger.debug("Calculating string: "+line);
		if(line == null || line.equals("")) return 0;
		if(! line.contains("(")) return line.length();
		else
		{
			int startIndex = line.indexOf("(");
			int endIndex = line.indexOf(")");
			
			String[] digitsToRepeat = line.substring(startIndex+1,endIndex).split("x");
			if(digitsToRepeat.length != 2) 
				throw new Exception("Expecting 2 digits but found: "
					+line.substring(startIndex+1, endIndex));
			else
			{
				int digitCount = Integer.parseInt(digitsToRepeat[0]);
				int repeatCount = Integer.parseInt(digitsToRepeat[1]);
				result += startIndex + repeatCount * getAdvancedStringLength(line.substring(endIndex+1,endIndex+1+digitCount)) 
						+  getAdvancedStringLength(line.substring(endIndex+1+digitCount));
			}
			
		}
		
		
		return result;
	}
	
}
