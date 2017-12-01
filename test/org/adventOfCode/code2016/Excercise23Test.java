package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import java.util.List;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

public class Excercise23Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise23 test = new Excercise23();
		  
		  String testInput = "resource/Excercise23Test.txt";
		  List<String> input = FileUtil.readStringsFromFile(testInput);
		  
		  long result = test.executeProgram(input,0);
		  assertTrue("Testing program expect: "+ "3" + " but found "+ result,result == 3);
		  
		/*  int moveCount = test.getMinimumMoves(result);
		  
		  assertTrue("Testing moves expected: "+ "7" + " but found "+ moveCount,moveCount == 7);*/
		  
		  
		  test.executeTest();
	   }

	   
	  
	   
}
