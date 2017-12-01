package org.adventOfCode.code2016;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;




public class Excercise7 extends GenericExcercise {

	
	@Override
	public void executeTest() throws Exception {
		
		long resultCount = getValidIPCount("resource/Excercise7.txt",isValidIP);
		logger.info("Valid IP's in part A: "+resultCount);
		
		resultCount = getValidIPCount("resource/Excercise7.txt",isValidSSL);
		logger.info("Valid SSL's in part B: "+resultCount);
		
	}
	
	public long getValidIPCount(String fileName,Predicate<String> validator) throws Exception
	{
		List<String> dataList = FileUtil.readStringsFromFile(fileName);
		if(dataList == null || dataList.size() == 0)
			throw new Exception("No data found in file: "+fileName);
		
		long result = dataList.stream()
								.filter(validator)
								.count();
		
		return result;
	}
	
	
	public static Predicate<String> isValidIP = (ip) -> 
	{
		boolean result = false;
		
		Predicate<String> ABBA_CHECK = (s) -> 
		{
			for(int i = 0; i <= s.length()-4;i++)
			{
				if(s.charAt(i) == s.charAt(i+3) && 
				   s.charAt(i+1) == s.charAt(i+2) &&
				   s.charAt(i) != s.charAt(i+1) ) return true;
			}
			return false;
		};
		
		String ipToProcess = ip;
		while(ipToProcess.length() > 0)
		{
			int nextBracketOpen = ipToProcess.indexOf("[");
			if(nextBracketOpen > 0)
			{
				int nextBracketClose = ipToProcess.indexOf("]");
				
				// valid code between brackets so certainly no valid IP
				if(ABBA_CHECK.test(ipToProcess.substring(nextBracketOpen+1,nextBracketClose))) return false; 
				
				//test for valid IP and save ok if found
				result = result || ABBA_CHECK.test(ipToProcess.substring(0,nextBracketOpen));
				ipToProcess = ipToProcess.substring(nextBracketClose+1);
			}
			else
			{
				//logger.debug(""+result+"\t"+ip);
				return result || ABBA_CHECK.test(ipToProcess);
			}
		}
		
		return result;
	};
	
	public static Predicate<String> isValidSSL = (ip) -> 
	{
		boolean result = false;
		Predicate<String> ABA_CHECK = (s) -> s.charAt(0) == s.charAt(2);
		
		List<String> validABAs = new ArrayList<String> ();
		StringBuilder bracketCode = new StringBuilder();
		
		String ipToProcess = ip;
		while(ipToProcess.length() > 0)
		{
			String preBracketString = "";
			int nextBracketOpen = ipToProcess.indexOf("[");
			if(nextBracketOpen > 0)
			{
				int nextBracketClose = ipToProcess.indexOf("]");
				bracketCode.append(ipToProcess.substring(nextBracketOpen+1,nextBracketClose));
				
				preBracketString = ipToProcess.substring(0,nextBracketOpen);
				ipToProcess = ipToProcess.substring(nextBracketClose+1);
			}
			else 
			{
				preBracketString = ipToProcess;
				ipToProcess = "";
			}
			
			for(int i = 0; i <preBracketString.length()-2;i++)
				if(ABA_CHECK.test(preBracketString.substring(i,i+3))) validABAs.add(preBracketString.substring(i,i+3));
		}
		
		for(String s : validABAs)
		{
			String bab = s.substring(1,2)+s.substring(2)+s.substring(1,2);
			if(bracketCode.toString().contains(bab)) 
			{
			//	logger.debug("Valid SSL String found: " + ip);
				return true;
			}
		}
		
		
		return result;
	};

}
