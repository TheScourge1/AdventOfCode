package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise5Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise5 test = new Excercise5();
		  
		  String testInput = "abc";
		  
		  String result = test.decode(testInput);
		  String advancedResult = test.decodeAdvanced(testInput);
		  String EXPECTED_RESULT = "18f47a30";
		  String EXPECTED_ADVANCED = "05ace8e3";
		  
		  assertTrue("Expected: "+ EXPECTED_RESULT + " but found "+ result,result.equals(EXPECTED_RESULT));
		  assertTrue("Expected: "+ EXPECTED_ADVANCED + " but found "+ advancedResult,advancedResult.equals(EXPECTED_ADVANCED));
		
		  
		  test.executeTest();
	   }
	   
}
