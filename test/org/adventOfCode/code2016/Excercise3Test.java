package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise3Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise3 test = new Excercise3();
		  
		  String testInput = "5 10 25\n25 5 10\n25 5 10\n10 20 30\n30 10 20\n30 20 10\n11 20 30\n29 10 20\n30 21 10";
		  int result = test.countHorizontalTriangles(testInput);
		  assertTrue("Expecting 3 triangles, found: "+result,result == 3);
		  
		  String testInput2 = "101 301 501\n102 302 502\n103 303 503\n201 401 601\n202 402 602\n203 403 603";
		  result = test.countVerticalTriangles(testInput2);
		  assertTrue("Expecting 6 triangles, found: "+result,result == 6);
		   
		  test.executeTest();
	   }
	   
}
