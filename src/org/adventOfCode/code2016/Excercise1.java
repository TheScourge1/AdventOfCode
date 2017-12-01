package org.adventOfCode.code2016;

import org.adventOfCode.GenericExcercise;

import java.util.HashSet;
import java.util.Set;

public class Excercise1 extends GenericExcercise
{
	public static String testinput = "R1, R3, L2, L5, L2, L1, R3, L4, R2, L2, L4, R2, L1, R1, L2, R3, L1, L4, R2, L5, R3, R4, L1, R2, L1, R3, L4, R5, L4, L5, R5, L3, R2, L3, L3, R1, R3, L4, R2, R5, L4, R1, L1, L1, R5, L2, R1, L2, R188, L5, L3, R5, R1, L2, L4, R3, R5, L3, R3, R45, L4, R4, R72, R2, R3, L1, R1, L1, L1, R192, L1, L1, L1, L4, R1, L2, L5, L3, R5, L3, R3, L4, L3, R1, R4, L2, R2, R3, L5, R3, L1, R1, R4, L2, L3, R1, R3, L4, L3, L4, L2, L2, R1, R3, L5, L1, R4, R2, L4, L1, R3, R3, R1, L5, L2, R4, R4, R2, R1, R5, R5, L4, L1, R5, R3, R4, R5, R3, L1, L2, L4, R1, R4, R5, L2, L3, R4, L4, R2, L2, L4, L2, R5, R1, R4, R3, R5, L4, L4, L5, L5, R3, R4, L1, L3, R2, L2, R1, L3, L5, R5, R5, R3, L4, L2, R4, R5, R1, R4, L3";
	
	public void executeTest() throws Exception{
		
		int result = executeTest(testinput,false);
		logger.info("Exercise 1 result A: " + result);
		result = executeTest(testinput,true);
		logger.info("Exercise 1 result B: " + result);
	}
	
	
	public int executeTest(String sequence, boolean stopAtSecondVisit) throws Exception
	{
		int result = 0;
		
		int xval = 0;
		int yval = 0;
		int walkx = 1;
		int walky = 0;
		
		Set<String> prevVisits = new HashSet<String>();
		
		String[] stringInput = sequence.split(", ");
		
		
		for (int i = 0; i < stringInput.length; i++)
		{
			if(stringInput[i].charAt(0) != 'R' && stringInput[i].charAt(0) != 'L')  
				throw new Exception("R or L expected but found: "+stringInput[i].charAt(0));
			
			if(walkx == 0) 
			{
				if(walky == 1) walkx = 1;
				else walkx = -1;
				walky = 0;
			}
			else
			{
				if(walkx == 1) walky = -1;
				else walky = 1;
				walkx = 0;
			}
			
			if(stringInput[i].charAt(0) == 'L')
			{
				walky *= -1;
				walkx *= -1;
			}
			
			int distance = Integer.parseInt(stringInput[i].substring(1));
			for (int d = 1; d<= distance; d++)
			{
				if(walkx != 0) xval += walkx; 
				else yval += walky; 
				String resultString = ""+xval+","+yval;
				
				if(stopAtSecondVisit == true && prevVisits.contains(resultString))
				{
					return result = Math.abs(xval)+Math.abs(yval);
				}
				else prevVisits.add(resultString);
			}

		}
		
		result = Math.abs(xval)+Math.abs(yval);
		
		return result;
	}

	
}
