package org.adventOfCode.code2016;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.adventOfCode.GenericExcercise;
import org.apache.commons.codec.digest.DigestUtils;

public class Excercise17 extends GenericExcercise {

	@Override
	public void executeTest() throws Exception {


		String result = getPath("qljzarfv");  
		logger.debug("Result for excercise 17A:" + result);
		
		int maxPath = getMaxpPathLength("qljzarfv");
		logger.debug("Result for excercise 17B:" + maxPath);

	}
 
	
	public String getPath(String input) 
	{
		return getPathList(input).get(0);
	}
	
	public int getMaxpPathLength(String input) 
	{
		List<String> allPaths = getPathList(input);
		
		return allPaths.get(allPaths.size()-1).length();
	}
	
	
	public List<String> getPathList(String input) 
	{
		List<String> resultList = new ArrayList<String>();
		
		List<String> possibleList = new ArrayList<String>();
		List<int[]> possibleStartLocations = new ArrayList<int[]>();
		
		Set<Character> validDoors = new HashSet<Character>();
		validDoors.add('b');validDoors.add('c');validDoors.add('d');validDoors.add('e');validDoors.add('f');
		
		possibleList.add(input);
		possibleStartLocations.add(new int[] {0,0});
		while(possibleList.size() > 0)
		{
			List<String> nextPossibleList = new ArrayList<String>();
			List<int[]> nextPossibleStartLocations = new ArrayList<int[]>();
			
			for(int i = 0; i < possibleList.size();i++)
			{
				int[] position = possibleStartLocations.get(i);
				String hash = DigestUtils.md5Hex(possibleList.get(i));
				
				for(int j = 0; j< 4; j++)
				{
					if(validDoors.contains(hash.charAt(j)))
					{ //j = up - down - left - righ
						int[] newPosition = new int[2];
						newPosition[0] = position[0];
						newPosition[1] = position[1];
						String newCode = possibleList.get(i);
						
						if(j == 0 && position[1] > 0) 
						{
							newPosition[1]--;
							newCode+="U";
						}
						else if(j == 1 && position[1] < 3)
						{
							newPosition[1]++;
							newCode+="D";
						}
						else if(j == 2 && position[0] > 0)
						{
							newPosition[0]--;
							newCode+="L";
						}
						else if(j == 3 && position[0] < 3)
						{
							newPosition[0]++;
							newCode+="R";
						}
						
						if(newPosition[0] == 3 && newPosition[1] == 3 ) resultList.add(newCode.substring(input.length(),newCode.length()));
						else
						{
							if(!possibleList.get(i).equals(newCode))
							{
								nextPossibleStartLocations.add(newPosition);
								nextPossibleList.add(newCode);
							}
						}
					}
				}
			}
			
			
			possibleList = nextPossibleList;
			possibleStartLocations = nextPossibleStartLocations;
		}
		
		return resultList;
	}
	

}