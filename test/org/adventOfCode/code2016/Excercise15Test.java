package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Excercise15Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise15 test = new Excercise15();
		  
		//  String testInput = "resource/Excercise12Test.txt";
		  
		  ArrayList<int[]> testInput = new ArrayList<int[]>();
		  testInput.add(new int[] {5,4});
		  testInput.add(new int[] {2,1});

		  int result = test.getValidTime(testInput);

		  assertTrue("Expected: "+ 5 + " but found "+ result,result == 5);
		  
		  	  
		  test.executeTest();
	   }

	   
	  
	   
}
