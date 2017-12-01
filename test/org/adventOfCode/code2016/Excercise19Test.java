package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise19Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise19 test = new Excercise19();
		  
		  
		  int result = test.getLastPositionA(5);
		  assertTrue("Expected: "+ "3" + " but found "+ result,result == 3);
		  
		  result = test.getLastPositionB(5);
		  assertTrue("Expected: "+ "2" + " but found "+ result,result == 2);
		  
		  result = test.getLastPositionB(6);
		  assertTrue("Expected: "+ "3" + " but found "+ result,result == 3);
  
		  test.executeTest();
	   }

	   
	  
	   
}
