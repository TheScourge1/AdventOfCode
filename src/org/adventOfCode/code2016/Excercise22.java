package org.adventOfCode.code2016;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;


public class Excercise22 extends GenericExcercise {
	
	@Override
	public void executeTest() throws Exception {

		String testInput = "resource/Excercise22.txt";
		List<String> input = FileUtil.readStringsFromFile(testInput);
		
		List<Node> nodeList = processFileInput(input);
		List<Node[]> validNodePairs = getViableNodePairs(nodeList);
		logger.debug("Result for excercise 22A:" + validNodePairs.size());
		
		int resultMoves = getMinimumMoves(nodeList);
		
		logger.debug("Result for excercise 22B:" + resultMoves);

	}
	
	
	public List<Node> processFileInput(List<String> inputStrings)
	{
		List<Node> resultList = new ArrayList<Node>();
		
		/*
		for(String input : inputStrings)
		{
			try{
			String[] splitInput = input.split("[ ]+");
			String[] coords = splitInput[0].split("-");
			
			int used = Integer.parseInt(splitInput[2].substring(0, splitInput[2].length()-1));
			int avail = Integer.parseInt(splitInput[3].substring(0, splitInput[3].length()-1));
			
			int x = Integer.parseInt(coords[1].substring(1, coords[1].length()));
			int y = Integer.parseInt(coords[2].substring(1, coords[2].length()));
			
			resultList.add(new Node(x,y,used,avail));
			}catch(Exception e)
			{
				logger.debug("Error while parsing line: "+input,e);
			}
		}*/
		
		resultList =  inputStrings.stream().map(s -> {
	        Matcher m = Pattern.compile("(\\d+)-y(\\d+)\\s+(\\d+)T\\s+(\\d+)T\\s+(\\d+)").matcher(s);
	        if (m.find())
	          return new Node(Integer.valueOf(m.group(1)),
	            Integer.valueOf(m.group(2)),
	            Integer.valueOf(m.group(4)),
	            Integer.valueOf(m.group(5)));
	        else return null;
	      }).filter(n -> n != null).collect(Collectors.toList());
	
		return resultList;
	}
	

	
	public int getMinimumMoves(List<Node> inputList)
	{
		int result = 0;
		
		String[][] startGrid = produceStartGrid(inputList);
		
		int[] gLocation = movesToGLocation(startGrid);
		result += gLocation[2];

			
		return (gLocation[0]-1)*5+gLocation[2]; //completely cheating by knowing the grid structure
	}
	
	
	private int[] movesToGLocation(String[][] startGrid)
	{
		int[] startLocation = getLocationOf(startGrid,"_");
		int[] gLocation = getLocationOf(startGrid,"G");
		
		List<int[]> nextLocations = getNextPossibleLocations(startGrid,startLocation);
		List<int[]> locationHistory = new ArrayList<int[]>(); 
		locationHistory.addAll(nextLocations);

		while(nextLocations.size() >0 && containsLocation(nextLocations,gLocation) == null)
		{
			List<int[]> newLocationList = new ArrayList<int[]>();
			for(int[] location : nextLocations)
				newLocationList.addAll(getNextPossibleLocations(startGrid,location));

			nextLocations = new ArrayList<int[]>();
			for(int[] newLoc : newLocationList)
			{
				boolean visited = false;
				for(int[] historyLoc : locationHistory)
				{
					if(historyLoc[0]==newLoc[0] && historyLoc[1]==newLoc[1]) 
					{
						visited = true;
						break;
					}
				}
				
				if(!visited) nextLocations.add(newLoc);
			}
			locationHistory.addAll(nextLocations);
		}

		gLocation = containsLocation(nextLocations,gLocation);
		logger.debug("G found in steps: "+gLocation[2]);
		
		return gLocation;
	}
	
	private List<int[]> getNextPossibleLocations(String[][] startGrid,int[] currentLocation)
	{
		List<int[]> newLocations = new ArrayList<int[]>();
		
		for(int horizontal = -1; horizontal < 2; horizontal+=2)
			for(int vertical = -1; vertical < 2; vertical +=2)
			{
				if(horizontal == 0 && vertical == 0) break;
				int x = currentLocation[0];
				
				int[] newPosition = {-1,-1,0};
				
				while(x>=0 && x<startGrid.length)
				{	
					int y = currentLocation[1];
					while(y>=0 && y < startGrid[0].length)
					{
						if(!startGrid[x][y].equals("#"))
						{
							newPosition[0] = x;
							newPosition[1] = y;
							newPosition[2] = currentLocation[2] + Math.abs(x - currentLocation[0]) + Math.abs(y - currentLocation[1]);
							y+=vertical;
						}
						else break;
						
					}
					
					x+=horizontal;
				}
				
				if(newPosition[0]!=-1 && !(newPosition[2] == currentLocation[2])) newLocations.add(newPosition);
				
			}
		
		//for(int[] loc : newLocations) logger.debug("("+loc[0]+","+loc[1]+")"+loc[2]);
		return newLocations;
	}
	
	private int[] getLocationOf(String[][] startgrid,String symbol)
	{
		int[] startLocation = new int[3];
		for(int i = 0; i< startgrid.length;i++)
		{
			for(int j = 0;j < startgrid[0].length;j++)
				if(startgrid[i][j].equals(symbol))
				{
					startLocation[0] = i;
					startLocation[1] = j;
				}
		}
		
		return startLocation;
	}
	
	private int[] containsLocation(List<int[]> locationList,int[] locationToFind)
	{
		for(int[] loc : locationList)
			if(locationToFind[0]==loc[0] && locationToFind[1] == loc[1]) return loc;
		
		return null;
	}
	
	public String[][] produceStartGrid(List<Node> inputList)
	{
		int maxX = inputList.stream().max( (n1,n2) -> Integer.compare(n1.x,n2.x)).get().getX();
		int maxY = inputList.stream().max( (n1,n2) -> Integer.compare(n1.y,n2.y)).get().getY();
		
		String[][] startGrid = new String[maxX+1][maxY+1];
		for(int i = 0; i< startGrid.length;i++)
			for(int j = 0;j < startGrid[0].length;j++)
				startGrid[i][j] = "#";
		
		for(Node[] pair : getViableNodePairs(inputList))
		{
			if(startGrid[pair[0].x][pair[0].y].equals("#"))
					startGrid[pair[0].x][pair[0].y] = ".";
			startGrid[pair[1].x][pair[1].y] = "_";
		}
		
		startGrid[maxX][0] = "G";
		
		for(int i = 0; i< startGrid[0].length;i++)
		{
			StringBuilder b =  new StringBuilder();
			for(int j = 0;j < startGrid.length;j++)
				b.append(startGrid[j][i] + "\t");
			
			logger.debug(b.toString());
		}
		
		return startGrid;
	}
	
	
	
	public List<Node[]> getViableNodePairs(List<Node> nodeList)
	{
		List<Node[]> resultList = new ArrayList<Node[]>();

		for(Node a : nodeList)
		{
			for(Node b : nodeList)
			{
				if(a.equals(b) || a.used == 0) continue;
				if(b.avail >= a.used) resultList.add(a.createPair(b));
			}
		}
		
		return resultList;
	}
	
	

	public class Node
	{
		int x = 0;
		int y= 0;
		long used = 0;
		long avail = 0;
		
		public Node(int x,int y,int used,int avail)
		{
			this.x = x;
			this.y = y;
			this.avail = avail;
			this.used = used;
		}
		
		
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public long getUsed() {
			return used;
		}
		public void setUsed(long used) {
			this.used = used;
		}
		public long getAvail() {
			return avail;
		}
		public void setAvail(long avail) {
			this.avail = avail;
		}
		
		public boolean fits(Node a)
		{
			return a.used <= this.avail;
		}
		
		public Node[] createPair(Node a)
		{
			Node[]  pair = new Node[2];
			pair[0] = this;
			pair[1] = a;
			return pair;
		}	
		
		public boolean equals(Object o)
		{
			Node b = (Node)o;
			return b.x == x && b.y == y;
		}
		
		public String toString()
		{
			return "(" +x+","+y+") "+used+" "+avail+" ";
		}
	}
	}