package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise14Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise14 test = new Excercise14();
		  
		//  String testInput = "resource/Excercise12Test.txt";

		  int result = test.getIndex(1,"abc",1);
		  assertTrue("Expected: "+ 39 + " but found "+ result,result == 39);
		  
		  result = test.getIndex(2,"abc",1);
		  assertTrue("Expected: "+ 92 + " but found "+ result,result == 92);
		  
		  result = test.getIndex(1,"abc",2017);
		  assertTrue("Expected: "+ 10 + " but found "+ result,result == 10);
		  
		  result = test.getIndex(64,"abc",1);
		  assertTrue("Expected: "+ 22728 + " but found "+ result,result == 22728);
		  
		  result = test.getIndex(64,"abc",2017);
		  assertTrue("Expected: "+ 22551 + " but found "+ result,result == 22551);
		  
		  	  
		  test.executeTest();
	   }

	   
	  
	   
}
