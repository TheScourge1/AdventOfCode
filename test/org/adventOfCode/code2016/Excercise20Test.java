package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import java.util.List;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

public class Excercise20Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise20 test = new Excercise20();
		  
		  String testInput = "resource/Excercise20Test.txt";
		  List<String> input = FileUtil.readStringsFromFile(testInput);
		  
		  long result = test.getLowestValidIp(input);
		  assertTrue("Expected: "+ "3" + " but found "+ result,result == 3);
		  
	//	  result = test.getAllowedIps(input);
	//	  assertTrue("Expected: "+ "2" + " but found "+ result,result == 2);
  
		  test.executeTest();
	   }

	   
	  
	   
}
