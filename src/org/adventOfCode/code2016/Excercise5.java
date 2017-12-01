package org.adventOfCode.code2016;

import java.security.MessageDigest;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;



public class Excercise5 extends GenericExcercise {

	String PUZZLE_INPUT = "ffykfhsq";
	
	@Override
	public void executeTest() throws Exception {
		
		logger.info("Result for 5A: "+decode(PUZZLE_INPUT));
		logger.info("Result for 5B: "+decodeAdvanced(PUZZLE_INPUT));
	}
	
	
	public String decode(String input) throws Exception
	
	{
		StringBuilder result = new StringBuilder();
		MessageDigest md5Generator = MessageDigest.getInstance("MD5");
		
		int length = 0;
		
		for(long i = 0; length < 8 && i >= 0;i++)
		{
			byte[] hash = md5Generator.digest((input+i).getBytes());
		//	if(i%1000000 == 0 )logger.debug("At value: "+i);
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < hash.length; j++) {
		          sb.append(Integer.toString((hash[j] & 0xff) + 0x100, 16).substring(1));
		        }
			
			if(sb.toString().startsWith("00000")) 
			{
				result.append(sb.charAt(5));
			//	logger.debug("Code element found: "+sb.charAt(5)+ " for input: "+"abc"+i);
				length++;
			}
		}
	
		return result.toString();
	}
	
	public String decodeAdvanced(String input) throws Exception
	
	{
		String[] result = new String[8];
		for(int i=0; i < result.length;i++) result[i]  = "x";
		
		MessageDigest md5Generator = MessageDigest.getInstance("MD5");
		
		int length = 0;
		
		for(long i = 0; length < 8 && i >= 0;i++)
		{
			byte[] hash = md5Generator.digest((input+i).getBytes());
		//	if(i%1000000 == 0 )logger.debug("At value: "+i);
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < hash.length; j++) {
		          sb.append(Integer.toString((hash[j] & 0xff) + 0x100, 16).substring(1));
		        }
			
			if(sb.toString().startsWith("00000")) 
			{
				int index = sb.charAt(5) - '0';
				if(index < result.length && result[index].equals("x"))
				{
					result[index] = sb.substring(6,7);
				//	logger.debug("Advanced Code element found: "+sb.charAt(6)+ " for input: "+"abc"+i);
					length++;
				}
			}
		}
		
		
		
		StringBuilder sb = new StringBuilder();
		for(String s : result) sb.append(s);

		return sb.toString();
	}
	
	
}
