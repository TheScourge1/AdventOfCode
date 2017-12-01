package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import java.util.List;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

public class Excercise24Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise24 test = new Excercise24();
		  
		  String testInput = "resource/Excercise24Test.txt";
		  List<String> input = FileUtil.readStringsFromFile(testInput);
		  
		  long result = test.findShortestPath(input,false);
		  assertTrue("Testing program expect: "+ "14" + " but found "+ result,result == 14);
		  
		/*  int moveCount = test.getMinimumMoves(result);
		  
		  assertTrue("Testing moves expected: "+ "7" + " but found "+ moveCount,moveCount == 7);*/
		  
		  
		  test.executeTest();
	   }

	   
	  
	   
}
