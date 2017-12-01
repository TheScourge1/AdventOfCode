package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise9Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise9 test = new Excercise9();
		  
		  String testInput = "resource/Excercise9.txt";

		  
		  String result = test.decodeString("ADVENT");
		  String expectedResult = "ADVENT";
		  assertTrue("Expected: "+ expectedResult + " but found "+ result,result.equals(expectedResult));
		  
		  result = test.decodeString("A(1x5)BC");
		  expectedResult = "ABBBBBC";
		  assertTrue("Expected: "+ expectedResult + " but found "+ result,result.equals(expectedResult));
		  
		  result = test.decodeString("(3x3)XYZ");
		  expectedResult = "XYZXYZXYZ";
		  assertTrue("Expected: "+ expectedResult + " but found "+ result,result.equals(expectedResult));
		  
		  result = test.decodeString("A(2x2)BCD(2x2)EFG");
		  expectedResult = "ABCBCDEFEFG";
		  assertTrue("Expected: "+ expectedResult + " but found "+ result,result.equals(expectedResult));
		  
		  result = test.decodeString("(6x1)(1x3)A");
		  expectedResult = "(1x3)A";
		  assertTrue("Expected: "+ expectedResult + " but found "+ result,result.equals(expectedResult));
		  
		  result = test.decodeString("X(8x2)(3x3)ABCY");
		  expectedResult = "X(3x3)ABC(3x3)ABCY";
		  assertTrue("Expected: "+ expectedResult + " but found "+ result,result.equals(expectedResult));
		  
		  testInput = "resource/Excercise9.txt";
		  long size = test.getBasicFileLength(testInput);
		  assertTrue("Expected: "+ 150914 + " but found "+ size,size == 150914L);
		
		  size = test.getAdvancedStringLength("(3x3)XYZ");
		  assertTrue("Expected: "+ 9 + " but found "+ size,size == 9L);
		  
		  size = test.getAdvancedStringLength("X(8x2)(3x3)ABCY");
		  assertTrue("Expected: "+ "XABCABCABCABCABCABCY".length() + " but found "+ size,size == "XABCABCABCABCABCABCY".length());
		  
		  size = test.getAdvancedStringLength("(27x12)(20x12)(13x14)(7x10)(1x12)A");
		  assertTrue("Expected: "+ 241920 + " but found "+ size,size == 241920L);
		  
		  size = test.getAdvancedStringLength("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN");
		  assertTrue("Expected: "+ 445 + " but found "+ size,size == 445L);
		  
		  test.executeTest();
	   }
	   
}
