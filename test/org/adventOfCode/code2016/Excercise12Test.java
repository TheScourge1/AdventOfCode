package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise12Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise12 test = new Excercise12();
		  
		  String testInput = "resource/Excercise12Test.txt";

		  int result = test.processData(testInput);
		  
		  assertTrue("Expected: "+ 42+ " but found "+ result,result == 42);
		  	  
		  test.executeTest();
	   }

	   
	  
	   
}
