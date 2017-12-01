package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import org.junit.Test;

public class Excercise17Test 
{

	   @Test
	   public void testResult() throws Exception{
		  Excercise17 test = new Excercise17();
		  

		  String result = test.getPath("ihgpwlah");

		  assertTrue("Expected: "+ "DDRRRD" + " but found "+ result,result.equals("DDRRRD"));
		  result = test.getPath("kglvqrro");
		  assertTrue("Expected: "+ "DDUDRLRRUDRD" + " but found "+ result,result.equals("DDUDRLRRUDRD"));
		  result = test.getPath("ulqzkmiv");
		  assertTrue("Expected: "+ "DRURDRUDDLLDLUURRDULRLDUUDDDRR" + " but found "+ result,result.equals("DRURDRUDDLLDLUURRDULRLDUUDDDRR"));
		  
		  int maxPath = test.getMaxpPathLength("ihgpwlah");

		  assertTrue("Expected: "+ "370" + " but found "+ result,maxPath == 370);
		  maxPath = test.getMaxpPathLength("kglvqrro");
		  assertTrue("Expected: "+ "492" + " but found "+ result,maxPath == 492);
		  maxPath = test.getMaxpPathLength("ulqzkmiv");
		  assertTrue("Expected: "+ "830" + " but found "+ result,maxPath == 830);
		  
		  	  
		  test.executeTest();
	   }

	   
	  
	   
}
