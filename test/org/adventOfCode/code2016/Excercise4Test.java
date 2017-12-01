package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Excercise4Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise4 test = new Excercise4();
		  
		  List<String> codeList = new ArrayList<String>();
		  codeList.add("aaaaa-bbb-z-y-x-123[abxyz]");//OK
		  codeList.add("aaaaa-bbb-z-y-x-123[baxyz]");//SHOULD FAIL, NOT IN ORDER
		  codeList.add("a-b-c-d-e-f-g-h-987[abcde]");
		  codeList.add("not-a-real-room-404[oarel]");
		  codeList.add("totally-real-room-200[decoy]");
		  
		  assertTrue("Code sum should be: 1514, but found: "+test.getCodeSum(codeList),test.getCodeSum(codeList)==1514);
		  
		  String toDecrypt = "qzmt-zixmtkozy-ivhz-343[mizkh]";
		  String decrypted = test.decrypt(toDecrypt);
		  String resultExpected = "very encrypted name";
		  assertTrue("Expected " + resultExpected + " but found: " +decrypted,resultExpected.equals(decrypted));
		  
		  test.executeTest();
	   }
	   
}
