package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise18Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise18 test = new Excercise18();
		  

		  int[] input = {1,0,0,1,0,1,0,0,0,0};
		  
		  int result = test.countSafeTiles(10, input);

		  assertTrue("Expected: "+ "38" + " but found "+ result,result == 38);
 
		  	  
		  test.executeTest();
	   }

	   
	  
	   
}
