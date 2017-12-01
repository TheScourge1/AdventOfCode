package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise10Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise10 test = new Excercise10();
		  
		  String testInput = "resource/Excercise10Test.txt";

		  test.procesBotData(testInput);
		  int resultIndex = test.findComparatorBot(2, 5);
		  assertTrue("Expected: "+ 2+ " but found "+ resultIndex,resultIndex == 2);
		  
		  int result = test.findOutPutMultiplier();
		  assertTrue("Expected: "+ (2*3*5)+ " but found "+ result,result == (2*3*5));
		  
		  test.executeTest();
	   }

	   
	  
	   
}
