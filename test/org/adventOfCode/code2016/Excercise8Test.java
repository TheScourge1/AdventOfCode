package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise8Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise8 test = new Excercise8();
		  
		  String testInput = "resource/Excercise8Test.txt";

		  
		  long result = test.getPixelCount(7,3,testInput);
		  assertTrue("Expected: "+ 6 + " but found "+ result,result == 6L);
		  
		/*  testInput = "resource/Excercise7TestB.txt";
		  result = test.getValidIPCount(testInput,test.isValidSSL);
		  assertTrue("Expected: "+ 3 + " but found "+ result,result == 3L);
		*/
		  test.executeTest();
	   }
	   
}
