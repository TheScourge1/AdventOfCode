package org.adventOfCode.code2016;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.adventOfCode.GenericExcercise;
import org.apache.commons.codec.digest.DigestUtils;


public class Excercise14 extends GenericExcercise {

	@Override
	public void executeTest() throws Exception {

		
		 int result = getIndex(64,"yjdafjpo",1);
		  
		 logger.debug("Result for excercise 14A:" + result);
		 
		 result = getIndex(64,"yjdafjpo",2017);
		 logger.debug("Result for excercise 14B:" + result);
	}

	public int getIndex(int number, String salt,int stretchCount) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("md5");
		
		Map<String,List<Integer>> tripletsMap = new HashMap<String,List<Integer>>();
		List<Integer> resultIndexes = new ArrayList<Integer>();
		int thousandPlusCounter = 0;

		for (int hashCounter = 0; hashCounter < 50000; hashCounter++) {
			byte[] byteMessage = (salt + hashCounter).getBytes();
			byte[] hashBytes = md5.digest(byteMessage);
			String hash = byteArrayToHex(hashBytes);
			
			for(int i = 1; i<stretchCount;i++)
				hash = DigestUtils.md5Hex(hash); //faster algorithm

			

			boolean firstHash = true;
			
			for(int i = 0; i < hash.length()-2;i++)
			{
				if(hash.charAt(i) == hash.charAt(i+1) && 
						hash.charAt(i+1) == hash.charAt(i+2)) // tripplet found
				{
					String s = hash.substring(i, i+1);

					if(i < hash.length()-4 && 
						hash.charAt(i) == hash.charAt(i+3) && 
						hash.charAt(i) == hash.charAt(i+4)) //5let found
					{
						List<Integer> tripletList = tripletsMap.get(s);
						final int currentCounter = hashCounter;
						if(tripletList != null)
						{
							tripletList = tripletList.stream().filter( index -> (currentCounter - index) < 1000)
															  .sorted()
															  .collect(Collectors.toList());
							for(int ind : tripletList)
							{
								if(!resultIndexes.contains(ind))
									resultIndexes.add(ind);
								if(resultIndexes.size() == number) thousandPlusCounter = hashCounter;
								if(thousandPlusCounter != 0 && (hashCounter > thousandPlusCounter + 1000))
								{
									resultIndexes = resultIndexes.stream().sorted().collect(Collectors.toList()).subList(0, number);
									StringBuilder b = new StringBuilder();
									resultIndexes.stream().forEach(ss -> b.append(ss+","));
									logger.debug("ResultList: " + b.toString());
									return resultIndexes.get(number-1);
								}
									
							}
						}
					}
					else
					{
						if(firstHash)
						{
							if(tripletsMap.containsKey(s))
								tripletsMap.get(s).add(hashCounter);
							else
							{
								ArrayList<Integer> intList = new ArrayList<Integer>();
								intList.add(hashCounter);
								tripletsMap.put(s, intList);
							}
							firstHash = false;
						}
					}
				}
				
			}
			
		}
		
		return 0;
	}

	
	
	public static String byteArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder(a.length * 2);
		for (byte b : a)
			sb.append(String.format("%02x", b));
		return sb.toString();
	}
	

}