package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise11Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise11 test = new Excercise11();
		  
		  String testInput = "resource/Excercise11Test.txt";

		  test.readTestData(testInput);
		  int result = test.searchValidResult();
		  
		  assertTrue("Expected: "+ 11+ " but found "+ result,result == 11);
		  	  
		  test.executeTest();
	   }

	   
	  
	   
}
