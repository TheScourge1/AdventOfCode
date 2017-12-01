package org.adventOfCode.code2016;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.adventOfCode.GenericExcercise;
import org.apache.commons.codec.digest.DigestUtils;


public class Excercise15 extends GenericExcercise {

	@Override
	public void executeTest() throws Exception {

		
		ArrayList<int[]> testInput = new ArrayList<int[]>();
		testInput.add(new int[] {17,15});
		testInput.add(new int[] {3,2});
		testInput.add(new int[] {19,4});
		testInput.add(new int[] {13,2});
		testInput.add(new int[] {7,2});
		testInput.add(new int[] {5,0});

		int result = getValidTime(testInput);  
		logger.debug("Result for excercise 15A:" + result);
		
		testInput.add(new int[] {11,0});
		result = getValidTime(testInput);  
		logger.debug("Result for excercise 15B:" + result);
	}

	
	/**
	 * @param inputList
	 *  List of [positionCount,StartPosition]
	 * @return
	 */
	
	public int getValidTime(List<int[]> inputList)
	{
		int result = 0;
		
		while(result >= 0)
		{
			boolean resultOK = true;
			int timer = 0;
			for(int[] pair : inputList)
			{
				timer++;
				if((pair[1] + result + timer)%pair[0] != 0 ) resultOK = false;
			}
			
			if(resultOK) return result;
			result++;
		}
		
		return -1;
	}
	

}