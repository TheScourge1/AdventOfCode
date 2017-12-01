package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise13Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise13 test = new Excercise13();
		  
		//  String testInput = "resource/Excercise12Test.txt";

		  int result = test.findRoute(1,1,7,4,10);
		  assertTrue("Expected: "+ 11+ " but found "+ result,result == 11);
		  
		  result = test.findLocationCount(1, 1, 1, 10);
		  assertTrue("Expected Locations: "+ 3+ " but found "+ result,result == 3);
		  result = test.findLocationCount(1, 1, 2, 10);
		  assertTrue("Expected Locations: "+ 5+ " but found "+ result,result == 5);
		  result = test.findLocationCount(1, 1, 4, 10);
		  assertTrue("Expected Locations: "+ 9+ " but found "+ result,result == 9);
		  	  
		  test.executeTest();
	   }

	   
	  
	   
}
