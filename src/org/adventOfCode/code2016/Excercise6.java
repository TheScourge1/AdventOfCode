package org.adventOfCode.code2016;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;




public class Excercise6 extends GenericExcercise {

	
	Comparator<Integer> MOST_COMMON = (a,b) -> (a>b)? 1:-1;
	Comparator<Integer> LEAST_COMMON = (a,b) -> (a<b)? 1:-1;
	
	@Override
	public void executeTest() throws Exception {
		
		String code = decodeFile("resource/Excercise6.txt",MOST_COMMON);
		logger.info("Solution 6A: "+code);
		
		code = decodeFile("resource/Excercise6.txt",LEAST_COMMON);
		logger.info("Solution 6B: "+code);
	}
	
	 
	public String decodeFile(String input,Comparator<Integer> comparator) throws Exception
	{
		StringBuilder result = new StringBuilder();
		
		List<List<String>> dataArray = readFile(input);
		
		Map<Integer,Map<String,Integer>> dataCountList = new TreeMap<Integer,Map<String,Integer>>();
		{
			for(List<String> row : dataArray)
			{
				for(int i = 0; i < row.size();i++)
				{
					String valueToAdd = row.get(i);
					if(dataCountList.get(i) == null) 
						dataCountList.put(i,new HashMap<String,Integer>());
					if(dataCountList.get(i).get(valueToAdd) == null)
						dataCountList.get(i).put(valueToAdd, 1);
					else
						dataCountList.get(i).put(valueToAdd, 
								dataCountList.get(i).get(valueToAdd)+1);
				}
			}
			
			for(Map<String,Integer> map : dataCountList.values())
			{ 
				int maxval = map.values().stream()
					                   .max(comparator)
					                   .get();
				for(String s : map.keySet())
				{
					if(map.get(s) == maxval) 
					{
						result.append(s);
						break;
					}
				}
			}
		}
		
		
		return result.toString();
	}
	
	
	private List<List<String>> readFile(String fileName) throws Exception
	{
		List<String> dataList = FileUtil.readStringsFromFile(fileName);
		if(dataList == null || dataList.size() == 0)
			throw new Exception("No data found in file: "+fileName);
		
		List<List<String>> dataArray = new ArrayList<List<String>>();
		
		for(String s : dataList)
		{
			List<String> row = new ArrayList<String>();
			dataArray.add(row);
			s.chars().forEach(c -> row.add(String.valueOf((char)c)));
		}
				
		return dataArray;
		
	}
	
	
	
}
