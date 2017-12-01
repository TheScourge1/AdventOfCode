package org.adventOfCode.code2016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

public class Excercise3 extends GenericExcercise {

	@Override
	public void executeTest() throws Exception {
		
		List<String> input = FileUtil.readStringsFromFile("resource/Excercise3.txt");
		StringBuilder b = new StringBuilder();
		for(String s : input) {b.append(s.trim());b.append("\n");}
			
		int result = countHorizontalTriangles(b.toString());
		logger.info("3A: Number of triangles found: "+result+ " out of: "+input.size());
		
		result = countVerticalTriangles(b.toString());
		logger.info("3B: Number of triangles found: "+result+ " out of: "+input.size());
	}
	
	/**
	 * Return the number of triangles. Input as tuples of 3
	 * @return
	 */
	
	public int countHorizontalTriangles(String input) throws Exception
	{
		List<List<Integer>> triangleList =  getThreeIntPairs(input);
		return countTriangles(triangleList);
		
	}
	
	public int countVerticalTriangles(String input) throws Exception
	{
		List<List<Integer>> triangleList =  getThreeIntPairs(input);
		List<List<Integer>> invertedList = new ArrayList<List<Integer>>();
		
		List<List<Integer>> bufferList = new ArrayList<List<Integer>>();
		for(int i = 0; i<3;i++) bufferList.add(new ArrayList<Integer>());
		
		//Invert the horizontal to a vertical list
		for(List<Integer> htriangle : triangleList)
		{
			for(int i = 0; i < htriangle.size();i++)
			{
				bufferList.get(i).add(htriangle.get(i));
				if(bufferList.get(i).size() == 3)
				{
					invertedList.add(bufferList.get(i));
					bufferList.set(i, new ArrayList<Integer>());
				}
			}
		}
		
		return countTriangles(invertedList);
	}
	
	
	
	private List<List<Integer>> getThreeIntPairs(String input) throws Exception
	{
		List<String> inputList = Arrays.asList(input.split("\n"));
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		for(String triangle : inputList)
		{
			try{
			List<Integer> sides = Arrays.asList(triangle.split(" "))
									   .stream()
									   .filter((s)-> !s.equals(""))
									   .mapToInt((s) -> Integer.parseInt(s))
									   .boxed()
									   .collect(Collectors.toList());
			
			result.add(sides);
			
			} catch(NumberFormatException e)
			{
				logger.error("Found invalid integer sequence: "+triangle);
				throw new Exception(e);
			}
			
		}
		
		return result;
	}
	

	private int countTriangles(List<List<Integer>> triangleList) throws Exception
	{
		int result = 0;
		for(List<Integer> triangle : triangleList)
			if(isTriangle(triangle)) result++;
		
		return result;
	}
		
	private boolean isTriangle(List<Integer> intList) throws Exception
	{
		if(intList.size() != 3) 
			throw new Exception("No triangle, expect three sides found: "+ intList.size());
		
		int a = intList.get(0);
		int b = intList.get(1);
		int c = intList.get(2);
		
		return a + b > c && a + c > b && b + c > a;
	}

}
