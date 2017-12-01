package org.adventOfCode.code2016;

import org.adventOfCode.GenericExcercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Excercise1Clean extends GenericExcercise
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
		
		ArrayList<String> stringInput = new ArrayList<String>(Arrays.asList(sequence.split(", ")));
		
		Set<Coordinate> prevVisits = new HashSet<Coordinate>();
		Direction direction = new Direction();
		Coordinate coordinate = new Coordinate();
		
		for (String instruction : stringInput)
		{
			char dir = instruction.charAt(0);
			int distance = Integer.parseInt(instruction.substring(1));
					
			if(dir != 'R' && dir != 'L')  
				throw new Exception("R or L expected but found: "+dir);
			
			if(dir == 'R') direction.turnRight();
			else direction.turnLeft();
			
			for (int d = 1; d<= distance; d++)
			{
				coordinate.move(direction,1);
				
				if(stopAtSecondVisit == true && prevVisits.contains(coordinate))
				{
					return coordinate.getDistanceFromHome();
				}
				else prevVisits.add(coordinate);
			}
		}
		
		result = coordinate.getDistanceFromHome();
		return result;
	}
	
	private class Direction
	{
		int xDir = 1;
		int yDir = 0;
		
		protected void turnRight()
		{
			if(xDir == 0) 
			{
				xDir = (yDir == 1)?1:-1;
				yDir = 0;
			}
			else
			{
				yDir = (xDir == 1)?-1:1;
				xDir = 0;
			}
		}
		
		protected void turnLeft()
		{
			turnRight();
			xDir *= -1;
			yDir *= -1;
		}
		
		protected int getXDir() { return xDir;}
		protected int getYDir() { return yDir;}
	}
	
	private class Coordinate
	{
		int x=0;
		int y=0;
		
		protected void move(Direction dir, int distance)
		{
			x += dir.getXDir()*distance; 
			y += dir.getYDir()*distance; 
		}
		
		protected int getDistanceFromHome()
		{
			return Math.abs(x)+Math.abs(y);
		}
		
		public String toString()
		{
			return ""+x+","+y;
		}
		
		public boolean equals(Object c)
		{
			Coordinate input = (Coordinate) c;
			return input.x == x && input.y == y;
		}
		
		public int hashCode()
		{
			return x+1000*y;
		}
	}
}
