package org.adventOfCode.code2016;

import org.adventOfCode.GenericExcercise;

import java.util.ArrayList;
import java.util.List;


public class Excercise19 extends GenericExcercise {

	@Override
	public void executeTest() throws Exception {

		int result = getLastPositionA(3005290); 
		logger.debug("Result for excercise 19A:" + result);
		
		result = getLastPositionB(3005290); 
		logger.debug("Result for excercise 19B:" + result);

	}
 
	
	
	public int getLastPositionA(int startPositions)
	{
		List<Integer> resultList = new ArrayList<Integer>();
		for(int i = 1;i < startPositions+1;i++) resultList.add(i);
		
		while(resultList.size() > 1)
		{
			List<Integer> newList = new ArrayList<Integer>();
			
			if(resultList.size()%2 ==1) newList.add(resultList.get(resultList.size()-1)); //in case of unpair start with the last in the circle
			for(int j = 0; j< resultList.size()-1;j+=2) newList.add(resultList.get(j));
			resultList = newList;
		}
		
		
		return resultList.get(0);
	}
	
	//Optimize by creating own datastructure where keeping opposing pointer as a link
	public int getLastPositionB(int startPositions)
	{
		List<Integer> resultList = new ArrayList<Integer>();
		
		for(int i = 1;i < startPositions+1;i++) resultList.add(i);
		int currentPosition = 0;
		int numberOfresults = resultList.size();
		while(numberOfresults > 1)
		{
			if(currentPosition >= numberOfresults) currentPosition = 0;
			
			resultList.remove((currentPosition+numberOfresults/2)%numberOfresults);
			if((currentPosition+numberOfresults/2)%numberOfresults > currentPosition) currentPosition++; // do not increase if record before position has been removed.
			numberOfresults--;
			//if(numberOfresults% 1000 == 0) logger.debug("Counter: "+numberOfresults);

		}
		
		
		return resultList.toArray(new Integer[1])[0];
	}
	

}