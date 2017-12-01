package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise7Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise7 test = new Excercise7();
		  
		  String testInput = "resource/Excercise7TestA.txt";

		  
		  long result = test.getValidIPCount(testInput,test.isValidIP);
		  assertTrue("Expected: "+ 2 + " but found "+ result,result == 2L);
		  
		  testInput = "resource/Excercise7TestB.txt";
		  result = test.getValidIPCount(testInput,test.isValidSSL);
		  assertTrue("Expected: "+ 3 + " but found "+ result,result == 3L);
		
		  test.executeTest();
	   }
	   
}
