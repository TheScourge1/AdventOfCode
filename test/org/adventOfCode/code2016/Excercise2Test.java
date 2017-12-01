package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise2Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise2 test = new Excercise2();
		  
		  String testString = "ULL\nRRDDD\nLURDL\nUUUUD";
		  
		  String code = test.getCode(testString,test.keyPad1,'5');
		  assertTrue("Expecting code: 1985 but found: "+code,code.equalsIgnoreCase("1985"));
		  
		  code = test.getCode(testString,test.keyPad2,'5');
		  assertTrue("Expecting code: 5DB3 but found: "+code,code.equalsIgnoreCase("5DB3"));
		   
		  test.executeTest();
	   }
	   
}
