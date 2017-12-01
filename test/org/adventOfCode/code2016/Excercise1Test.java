package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise1Test 
{

	   @Test
	   public void testResult() throws Exception{
		   Excercise1 test = new Excercise1();
		   int result = test.executeTest("R2, L3",false);
		   assertTrue("R2L3 should be 5, found: "+result,result==5);
		   result = test.executeTest("R2, R2, R2",false);
		   assertTrue("R2R2R2 should be 2, found: " +result,result==2);
		   result = test.executeTest("R5, L5, R5, R3",false);
		   assertTrue("R5L5R5R3 should be 12, found: "+result,result==12);
		   result = test.executeTest("R8, R4, R4, R8",true);
		   assertTrue("R8, R4, R4, R8 should be 4, found: "+result,result==4);
		   
		  test.executeTest();
	   }
	   
	   @Test
	   public void testLambdaResult() throws Exception{
		   Excercise1Clean test = new Excercise1Clean();
		   int result = test.executeTest("R2, L3",false);
		   assertTrue("R2L3 should be 5, found: "+result,result==5);
		   result = test.executeTest("R2, R2, R2",false);
		   assertTrue("R2R2R2 should be 2, found: " +result,result==2);
		   result = test.executeTest("R5, L5, R5, R3",false);
		   assertTrue("R5L5R5R3 should be 12, found: "+result,result==12);
		   result = test.executeTest("R8, R4, R4, R8",true);
		   assertTrue("R8, R4, R4, R8 should be 4, found: "+result,result==4);
		   
		  test.executeTest();
	   }
	   
}
