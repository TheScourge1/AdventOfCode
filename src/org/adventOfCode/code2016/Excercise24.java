package org.adventOfCode.code2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;


public class Excercise24 extends GenericExcercise {
	
	@Override
	public void executeTest() throws Exception {

		String testInput = "resource/Excercise24.txt";
		List<String> input = FileUtil.readStringsFromFile(testInput);
		
		long result = findShortestPath(input,false);
		logger.debug("Result for excercise 24A:" + result);
		
		
		result = findShortestPath(input,true);
		logger.debug("Result for excercise 24B:" + result);

	}
	
	public int findShortestPath(List<String> input,boolean returnToStart)
	{
		int result = 0;
		
		String[][] inputMatrix = loadInputData(input);
		List<Point> pointList = getPointsToVisit(inputMatrix);
		pointList.stream().forEach(p -> logger.debug(p));
		
		int[][] distanceGraph = getGraph(inputMatrix,pointList);
		
		for(int i = 0; i < distanceGraph.length; i++)
		{
			StringBuilder b = new StringBuilder();
			for(int j = 0; j < distanceGraph[0].length; j++) b.append(distanceGraph[i][j]+" ");
			logger.debug(b.toString());
		}
		
		result = getShortestVisitPathCost(distanceGraph,returnToStart);
			
		
		return result;
	}
	
	
	private String[][] loadInputData(List<String> inputLines)
	{
		String[][] result = new String[inputLines.size()][inputLines.get(0).length()];
		
		for(int i = 0; i < inputLines.size(); i++)
			for(int j = 0; j < inputLines.get(0).length(); j++) result[i][j] = inputLines.get(i).substring(j,j+1);
		
		return result;
	}
	
	private List<Point> getPointsToVisit(String[][] inputArray)
	{
		List<Point> pointList = new ArrayList<Point>();
		
		for(int i = 0; i < inputArray.length; i++)
			for(int j = 0; j <inputArray[i].length; j++) 
				if(inputArray[i][j].matches("\\d")) pointList.add(new Point(i,j,Integer.parseInt(inputArray[i][j])));
		
		return pointList;
	}
	
	public int[][] getGraph(String[][] inputMatrix,List<Point> pointList)
	{
		int[][] result = new int[pointList.size()][pointList.size()];
		
		//pointList.stream().forEach( p -> findLinkedPoints(p.number,p,new HashSet<Point>(),inputMatrix,result,new int[inputMatrix.length][inputMatrix[0].length],0));
		
		pointList.stream().forEach( p -> findLinkedPointsFast(p,inputMatrix,result));
		
		return result;
	}
	
	
	private void findLinkedPointsFast(Point startPoint,String[][] inputMatrix,int[][] graph)
	{
		List<Point> lastVisitedLocations = new ArrayList<Point>();
		int[][] distanceMatrix = new int[inputMatrix.length][inputMatrix[0].length];
		lastVisitedLocations.add(startPoint);
		int currentDistance = 0;
		
		while(lastVisitedLocations.size() > 0)
		{
			List<Point> newLocations = new ArrayList<Point>();
			currentDistance++;
			for(Point p : lastVisitedLocations)
			{
				for(int i = -1; i < 2; i++)
					for(int j = -1; j < 2; j++)
					{
						if(i != 0 && j != 0) continue; // no diagonal moves
						int newX = p.x + i;
						int newY = p.y +j;
						
						if(newX > 0 && !inputMatrix[newX][newY].equals("#") && distanceMatrix[newX][newY] == 0)
						{
							if(inputMatrix[newX][newY].matches("\\d") && Integer.parseInt(inputMatrix[newX][newY]) != startPoint.number)
							{ // new shortest path found
								graph[startPoint.number][Integer.parseInt(inputMatrix[newX][newY])] = currentDistance;
							//	logger.debug("("+startPoint+","+Integer.parseInt(inputMatrix[newX][newY])+")"+"->"+currentDistance);
							}
							
							distanceMatrix[newX][newY] = currentDistance;
							newLocations.add(new Point(newX,newY));
						}
					}	
			}
			
			lastVisitedLocations = newLocations;
		}
	}
	
	
	public int getShortestVisitPathCost(int[][] visitGraph,boolean returnToStart)
	{
		int result = -1;
		Map<List<Integer>,Integer> finishPaths = new HashMap<List<Integer>,Integer>();
		Map<List<Integer>,Integer> currentPaths = new HashMap<List<Integer>,Integer>();
		Map<List<Integer>,Integer> newPaths = new HashMap<List<Integer>,Integer>();
		List<Integer> startLocation = new ArrayList<Integer>();
		startLocation.add(0);
		currentPaths.put(startLocation,0);
		while(currentPaths.size() > 0)
		{
			newPaths = new HashMap<List<Integer>,Integer>();
			for(List<Integer> path : currentPaths.keySet())
			{
				for(int i = 0; i < visitGraph.length;i++)
				{				
					int lastLocation = path.get(path.size()-1);
					
					if(visitGraph[lastLocation][i] > 0 && lastLocation != i)
					{
						List<Integer> newPath = new ArrayList<Integer>(path);
						if(!newPath.contains(i)) newPath.add(i);
						if(result != -1 &&  currentPaths.get(path)+visitGraph[lastLocation][i] > result) continue;
						else if(newPath.size() < visitGraph.length)
						{
							newPaths.put(newPath, currentPaths.get(path)+visitGraph[lastLocation][i]);
							lastLocation = i;
						}
						else 
						{
							finishPaths.put(newPath, currentPaths.get(path)+visitGraph[lastLocation][i]);
							int newResult = currentPaths.get(path)+visitGraph[lastLocation][i];
							if(returnToStart) newResult += visitGraph[i][0];
							if(result == -1 || result > newResult) result = newResult;
						}
					}
				}
			}
			currentPaths = newPaths;
		}
		

		return result;
	}
	
	protected class Point
	{
		int x = 0;
		int y = 0;
		
		int number = -1;

		private  Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		private  Point(int x, int y,int number)
		{
			this.x = x;
			this.y = y;
			this.number = number;
		}
		
		@Override
		public boolean equals(Object o)
		{
			Point p = (Point)o;
			return p.x == x && p.y == y;
		}
		
		@Override 
		public int hashCode()
		{
			return x + 31*y;
		}
		
		@Override
		public String toString()
		{
			if(number != -1) return ""+number+" ("+x+","+y+")";
			else return "("+x+","+y+")";
		}
	}
	
}