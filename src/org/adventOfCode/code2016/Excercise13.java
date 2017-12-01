package org.adventOfCode.code2016;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;





public class Excercise13 extends GenericExcercise {


	private Map<Long,Boolean> hammingWeightMap = new HashMap<Long,Boolean>();
	
	@Override
	public void executeTest() throws Exception {
		
		int result = findRoute(1,1,31,39,1364);
		
		logger.debug("Result for excercise 13A:" + result);

		result = findLocationCount(1,1,50,1364);
		logger.debug("Result for excercise 13B:" + result);

	}
	
	
	public int findRoute(int xStart, int yStart, int xEnd, int yEnd,int magicNumber)
	{
		int result = 1;
		Coord startC = new Coord(xStart,yStart);
		Coord endC = new Coord(xEnd,yEnd);
		
		Set<Coord> visitHistory = new HashSet<Coord>(); //contains all allready visited locations
		Set<Coord> currentBorder = new HashSet<Coord>(); //all locations reachable in count steps
		
		currentBorder.add(startC);
		
		while(result < 1000)
		{
			Set<Coord> newBorder = new HashSet<Coord>();
			
			for(Coord location : currentBorder)
			{
				List<Coord> newLocationList = location.getAdjacentCoords().stream()
											.filter(c -> isOpenSpace(c.getX(),c.getY(),magicNumber))
											.filter(c -> !visitHistory.contains(c))
											.collect(Collectors.toList());		
			
				if(newLocationList.contains(endC)) return result;
				else 
				{
					newBorder.addAll(newLocationList);
					visitHistory.add(location);
				}
			}
			
			currentBorder = newBorder;
			result++;
		}
		
		return result;
	}
	
	public int findLocationCount(int xStart, int yStart,int steps,int magicNumber)
	{
		int result = 0;
		Coord startC = new Coord(xStart,yStart);
		
		Set<Coord> visitHistory = new HashSet<Coord>(); //contains all allready visited locations
		Set<Coord> currentBorder = new HashSet<Coord>(); //all locations reachable in count steps
		
		currentBorder.add(startC);
		
		while(result <= steps)
		{
			Set<Coord> newBorder = new HashSet<Coord>();
			
			for(Coord location : currentBorder)
			{
				List<Coord> newLocationList = location.getAdjacentCoords().stream()
											.filter(c -> isOpenSpace(c.getX(),c.getY(),magicNumber))
											.filter(c -> !visitHistory.contains(c))
											.collect(Collectors.toList());		
			

				newBorder.addAll(newLocationList);
				visitHistory.add(location);

			}
			
			currentBorder = newBorder;
			result++;
		}
		
		/*StringBuilder locs = new StringBuilder();
		for(Coord c : visitHistory) locs.append(c);
		logger.debug(locs.toString());*/
		
		return visitHistory.size();
	}
	

	public boolean isOpenSpace(int x, int y,int magicNumber)
	{
		long calc = x*x +3*x + 2*x*y + y + y*y + magicNumber;
		
		if(hammingWeightMap.containsKey(calc)) return hammingWeightMap.get(calc);
		else
		{
			boolean result = true;
			int count = Long.toBinaryString(calc).replaceAll("0", "").length();
			if(count % 2 == 1) result = false;
			
			hammingWeightMap.put(calc, result);
			return result;	
		}
	}
	
	
	private class Coord
	{
		int x = 0;
		int y = 0;
		
		public Coord(int x,int y)
		{
			this.x = x;
			this.y = y;
		}
			
		public boolean equals(Object o)
		{
			Coord c2 = (Coord)o;
			return x == c2.x && y == c2.y;
		}
		
		public int hashCode()
		{
			return x *31 + y;
		}
		
		public List<Coord> getAdjacentCoords()
		{
			List<Coord> result = new ArrayList<Coord>();
			
			result.add(new Coord(x+1,y));
			result.add(new Coord(x, y+1));
			if(x > 0) result.add(new Coord(x-1,y));
			if(y > 0) result.add(new Coord(x,y-1));

			return result;
		}
		
		public int getX() { return x;}
		public int getY() { return y;}
		
		public String toString()
		{
			return "("+x+","+y+"),";
		}
	}
	
}