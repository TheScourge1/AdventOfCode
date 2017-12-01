package org.adventOfCode.code2016;

import java.util.List;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;





public class Excercise8 extends GenericExcercise {

	
	
	
	@Override
	public void executeTest() throws Exception {
		
		long resultCount = getPixelCount(50,6,"resource/Excercise8.txt");
		logger.info("Number of lit pixels in part A: "+resultCount);
		
	}
	
	public int getPixelCount(int width,int height,String inputFile) throws Exception
	{
		Display display = new Display(width,height);
		
		List<String> commandList = FileUtil.readStringsFromFile(inputFile);
		
		for(String s : commandList)
		{
			if(s.startsWith("rect "))
			{
				int[] params = parseCommand(s,"rect ","x");
				display.rectangleOn(params[0], params[1]);
			}
			else if(s.startsWith("rotate column x="))
			{
				int[] params = parseCommand(s,"rotate column x="," by ");
				display.rotateColumn(params[0], params[1]);
			}
			else if(s.startsWith("rotate row y="))
			{
				int[] params = parseCommand(s,"rotate row y="," by ");
				display.rotateRow(params[0], params[1]);
			}
			else throw new Exception("Unexpected command found: "+s);
		}
		
		display.printScreen();
		return display.getLitPixelCount();
	}
	
	
	private int[] parseCommand(String s,String prefix, String splitter) throws Exception
	{
		int[] result= new int[2];
		
		String[] params = s.substring(prefix.length()).split(splitter);
		if(params.length != 2) throw new Exception("Expected two parameters, found: "+params.length);
		result[0] = Integer.parseInt(params[0]);
		result[1] = Integer.parseInt(params[1]);
		
		return result;
	}
	
	

	public class Display
	{
		private int width=0;
		private int height=0;
		
		int[][] displayMatrix = null;
		
		public Display(int width,int height)
		{
			this.width=width;
			this.height=height;
			
			displayMatrix = new int[height][width];
		}
		
		public void rectangleOn(int width,int height)
		{
			for(int i = 0; i < height; i++)
				for(int j = 0; j< width;j++)
					displayMatrix[i][j]=1;
		}
		
		public void rotateColumn(int column,int shift)
		{
			int[] tempColumn = new int[height];
			for(int i = 0;i<height;i++) tempColumn[(i+shift)%height] = displayMatrix[i][column];
			for(int i = 0;i<height;i++) displayMatrix[i][column] = tempColumn[i];
		}
		
		public void rotateRow(int row,int shift)
		{
			int[] tempRow = new int[width];
			for(int i=0; i<width;i++) tempRow[(i+shift)%width] = displayMatrix[row][i];
			for(int i=0; i<width;i++) displayMatrix[row][i] = tempRow[i];
		}
		
		public int getLitPixelCount()
		{
			int result = 0;
			for(int i = 0; i < height;i++)
				for(int j = 0; j < width; j++)
					if(displayMatrix[i][j] == 1) result++;
			
			
			return result;
		}
		
		public void printScreen()
		{
			for(int i = 0; i < height; i++)
			{
				StringBuilder row = new StringBuilder();
				for(int j = 0; j< width;j++)
				{
					if(displayMatrix[i][j] == 1) row.append("x");
					else row.append(" ");
				}
				
				logger.info(row.toString());
			}
		}
	}
	
}
