package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise16Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise16 test = new Excercise16();
		  

		  String result = test.getCheckSum(20,"10000");

		  assertTrue("Expected: "+ "01100" + " but found "+ result,result.equals("01100"));
		  
		  	  
		  test.executeTest();
	   }

	   
	  
	   
}
