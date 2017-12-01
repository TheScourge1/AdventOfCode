package org.adventOfCode.code2016;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;


public class Excercise20 extends GenericExcercise {

	//private static long MAX_IP = 4294967295L;
	
	@Override
	public void executeTest() throws Exception {

		String testInput = "resource/Excercise20.txt";
		List<String> input = FileUtil.readStringsFromFile(testInput);
		
		long result = getLowestValidIp(input); 
		logger.debug("Result for excercise 20A:" + result);
		
		result = getAllowedIps(input); 
		logger.debug("Result for excercise 20B:" + result);

	}
 
	
	public long getLowestValidIp(List<String> input)
	{
		List<long[]> rangeList = convertInput(input);
		
		List<long[]> sortedRangeList = rangeList.stream()
					.sorted((a1,a2) -> Long.compare(a1[0],a2[0]))
					.collect(Collectors.toList());

		long result = 0;

		for(long[] range : sortedRangeList)
		{
			if(result < range[0]) return result;
			else result = range[1]+1;
		}
		
		return result;	
	}
	
	public long getAllowedIps(List<String> input)
	{
		long result = 0;
		
		List<long[]> rangeList = convertInput(input);
		List<long[]> sortedRangeList = rangeList.stream()
				.sorted((a1,a2) -> Long.compare(a1[0],a2[0]))
				.collect(Collectors.toList());

		long currentIp = 0;
		
		for(long[] range : sortedRangeList)
		{
			while(currentIp < range[0]) 
			{
				result++;
				currentIp++;
			}
			if(currentIp < range[1]) currentIp = range[1]+1;
		}
		return result;
	}
	
	
	private List<long[]> convertInput(List<String> input)
	{
		List<long[]> rangeList = new ArrayList<long[]>();
		
		for(String s : input)
		{
			String[] ranges = s.split("-");
			long[] newRange = new long[2];
			newRange[0] = Long.parseLong(ranges[0]);
			newRange[1] = Long.parseLong(ranges[1]);
			rangeList.add(newRange);
		}
		
		return rangeList;
	}
	
	

	
	
}