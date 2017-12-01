package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise6Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise6 test = new Excercise6();
		  
		  String testInput = "resource/Excercise6ATest.txt";

		  
		  String result = test.decodeFile(testInput,test.MOST_COMMON);
		  String advancedResult = test.decodeFile(testInput,test.LEAST_COMMON);
		  
		  String EXPECTED_RESULT = "easter";
		  String EXPECTED_ADVANCED = "advent";
		  
		  assertTrue("Expected: "+ EXPECTED_RESULT + " but found "+ result,result.equals(EXPECTED_RESULT));
		  assertTrue("Expected: "+ EXPECTED_ADVANCED + " but found "+ advancedResult,advancedResult.equals(EXPECTED_ADVANCED));
		
		  
		  test.executeTest();
	   }
	   
}
