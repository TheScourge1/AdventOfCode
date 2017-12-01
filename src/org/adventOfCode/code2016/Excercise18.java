package org.adventOfCode.code2016;

import org.adventOfCode.GenericExcercise;

import java.util.ArrayList;
import java.util.List;


public class Excercise18 extends GenericExcercise {

	@Override
	public void executeTest() throws Exception {

		int[] input = {1,1,1,0,0,0,0,0,1,1,0,1,1,1,0,1,1,1,0,0,0,0,0,0,1,1,1,0,1,0,0,0,1,0,1,0,1,0,0,1,0,0,0,1,1,1,1,1,0,1,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,1,1,0,1,0,0,1,1,1,0,0,0,0,0,1,1,1,0,1,0,0,0,1,0,0,1,1,1,1,1,1,0,0,0,0};
		

		int result = countSafeTiles(40, input); 
		logger.debug("Result for excercise 18A:" + result);
		
		result = countSafeTiles(400000, input); 
		logger.debug("Result for excercise 18B:" + result);

	}
 
	
	public int countSafeTiles(int size,int[] firstRow)
	{
		int result = 0;
		
		List<int[]> rowList = new ArrayList<int[]>();
		rowList.add(firstRow);
		
		for(int i = 0; i < size-1; i++)
		{
			int[] nextRow = new int[firstRow.length];
			int[] currentRow = rowList.get(i);
			
			for(int rowId = 0; rowId < nextRow.length; rowId++)
			{
				int[] checkData = new int[3];
				
				if(rowId > 0) checkData[0] = currentRow[rowId-1];
				else checkData[0] = 1;
					
				checkData[1] = currentRow[rowId];
				
				if(rowId < nextRow.length - 1) checkData[2] = currentRow[rowId+1];
				else checkData[2] = 1;
				
				if(checkData[0]==0 && checkData[1]==0 &&checkData[2]==1
					|| checkData[0]==1 && checkData[1]==0 &&checkData[2]==0
					|| checkData[0]==0 && checkData[1]==1 &&checkData[2]==1
					|| checkData[0]==1 && checkData[1]==1 &&checkData[2]==0
					)
				{
					nextRow[rowId] = 0;
				}else
				{
					nextRow[rowId] = 1;
				}
			}
			
			rowList.add(nextRow);
			
		/*	StringBuilder b = new StringBuilder();
			for(int ii = 0; ii < nextRow.length; ii++) 
				if(nextRow[ii]==0) b.append("^");
				else b.append(".");
			logger.debug(b.toString()); */
		}
		
		for(int[] row : rowList) 
			for(int i = 0; i < row.length; i++) 
				if(row[i] ==1) result++;
		
		return result;
	}

}