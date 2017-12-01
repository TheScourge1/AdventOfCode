package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import java.util.List;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

public class Excercise22Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise22 test = new Excercise22();
		  
		  String testInput = "resource/Excercise22Test.txt";
		  List<String> input = FileUtil.readStringsFromFile(testInput);
		  
		  List<Excercise22.Node> result = test.processFileInput(input);
		  assertTrue("Testing file size: "+ "9" + " but found "+ result.size(),result.size() == 9);
		  
		  int moveCount = test.getMinimumMoves(result);
		  
		  assertTrue("Testing moves expected: "+ "7" + " but found "+ moveCount,moveCount == 7);
		  
		  
		  test.executeTest();
	   }

	   
	  
	   
}
