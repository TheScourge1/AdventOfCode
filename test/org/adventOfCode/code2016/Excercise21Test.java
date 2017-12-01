package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import java.util.List;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

public class Excercise21Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise21 test = new Excercise21();
		  
		  String testInput = "resource/Excercise21Test.txt";
		  List<String> input = FileUtil.readStringsFromFile(testInput);
		  
		  String result = test.swapPosition(4, 0, "abcde");
		  assertTrue("Testing sswapPosition: "+ "ebcda" + " but found "+ result,result.equals("ebcda"));
		  
		  result = test.swapLetter("d", "b", "ebcda");
		  assertTrue("Testing swapLetter: "+ "edcba" + " but found "+ result,result.equals("edcba"));
		  
		  result = test.rotateSteps(-1, 1, "abcde");
		  assertTrue("Testing rotateSteps: "+ "bcdea" + " but found "+ result,result.equals("bcdea"));
		  
		  result = test.rotateSteps(1, 1, "abcde");
		  assertTrue("Testing rotateSteps: "+ "eabcd" + " but found "+ result,result.equals("eabcd"));
		  
		  result = test.rotatePositionBased("b", "abdec");
		  assertTrue("Testing rotatePositionBased: "+ "ecabd" + " but found "+ result,result.equals("ecabd"));
		  
		  result = test.rotatePositionBased("d", "ecabd");
		  assertTrue("Testing rotatePositionBased: "+ "decab" + " but found "+ result,result.equals("decab"));
		  
		  result = test.reverse(0,4, "edcba");
		  assertTrue("Testing reverse: "+ "abcde" + " but found "+ result,result.equals("abcde"));
		  
		  result = test.move(1,4, "bcdea");
		  assertTrue("Testing move: "+ "bdeac" + " but found "+ result,result.equals("bdeac"));
		  
		  result = test.move(3,0, "bdeac");
		  assertTrue("Testing move: "+ "abdec" + " but found "+ result,result.equals("abdec"));
		  
		  
		  result = test.scramble(input,"abcde");
		  assertTrue("Expected: "+ "decab" + " but found "+ result,result.equals("decab"));
		  
		  result = test.unScramble(input,"decab");
		  assertTrue("Expected: "+ "abcde" + " but found "+ result,result.equals("abcde"));
		  
		  
		  testInput = "resource/Excercise21.txt";
		  input = FileUtil.readStringsFromFile(testInput);
		  result = test.unScramble(input,"hcdefbag");
		  assertTrue("Expected: "+ "abcdefgh" + " but found "+ result,result.equals("abcdefgh"));
		  
		  test.executeTest();
	   }

	   
	  
	   
}
