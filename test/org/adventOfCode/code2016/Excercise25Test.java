package org.adventOfCode.code2016;

import org.apache.log4j.Logger;

import org.junit.Test;

public class Excercise25Test 
{
	
		Logger logger = Logger.getLogger(Excercise25Test.class);

	   @Test
	   public void testResult() throws Exception{
		  Excercise25 test = new Excercise25();
		  
		  test.executeTest();
	   }

/*	   @Test
	   public void testDiv() throws Exception{
		   
		   List<String> programA = new ArrayList<String>();
		   programA.add("cpy 2 c");
		   programA.add("jnz b 2");
		   programA.add("jnz 1 6");
		   programA.add("dec b");
		   programA.add("dec c");
		   programA.add("jnz c -4");
		   programA.add("inc a");
		   programA.add("jnz 1 -7");
		   
		   List<String> programB = new ArrayList<String>();
		   programB.add("cpy 2 c");
		   programB.add("jnz 1 1");
		   programB.add("jnz b 2");
		   programB.add("jnz 1 6");
		   programB.add("dec b");
		   programB.add("dec c");
		   programB.add("jnz c -4");
		   programB.add("inc a");
		   programB.add("jnz 1 -8");
		   
		   
		   BunnyAssemblerProcessor processorA = new BunnyAssemblerProcessor();
		   BunnyAssemblerProcessor processorB = new BunnyAssemblerProcessor();
		   for(int i = 1; i < 100; i++)
		   {
			   processorA.init();
			   processorA.setVariable("a", 0);
			   processorA.setVariable("b", i);
			   processorA.executeProgram(programA);
			   processorB.init();
			   processorB.setVariable("a", 0);
			   processorB.setVariable("b", i);
			   processorB.executeProgram(programB);
			   long result = processorA.getVariable("a");
			   assertTrue("Testing div2: "+ i/2 + " but found "+ result,result == i/2);
			   
			   assertTrue("Testing equals A for i="+ i + ": " + processorA.getVariable("a") +  " but expected "+ processorB.getVariable("a"),
					   processorA.getVariable("a") == processorB.getVariable("a"));
			   assertTrue("Testing equals B for i="+ i + ": " + processorA.getVariable("b") +  " but expected "+ processorB.getVariable("b"),
					   processorA.getVariable("b") == processorB.getVariable("b"));
			   assertTrue("Testing equals C for i="+ i + ": " + processorA.getVariable("c") +  " but expected "+ processorB.getVariable("c"),
					   processorA.getVariable("c") == processorB.getVariable("c"));
		   }
		   
	   }
	  */
	   
}
