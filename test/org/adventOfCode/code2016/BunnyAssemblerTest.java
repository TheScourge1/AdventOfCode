package org.adventOfCode.code2016;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

public class BunnyAssemblerTest {
	
		static Logger logger = Logger.getLogger("AssemblerLoggerTest");
		BunnyAssemblerProcessor assembler = new BunnyAssemblerProcessor();
	
	   @Test
	   public void testAddSequence() throws Exception{
		   
		   List<String> commandList = new ArrayList<String>();
		   
		   commandList.add("cpy 3 a");
		   commandList.add("cpy 5 c");
		   commandList.add("inc a");
		   commandList.add("dec c");
		   commandList.add("jnz c -2");
		   assembler.executeProgram(commandList);
		   
		   long result = assembler.getVariable("a");
		   assertTrue("Testng addition expecting: "+ "8" + " but found "+ result,result == 8);
		   
		   logger.debug(""+result);
		   
	   }
	   
	   @Test
	   public void testMulSequence() throws Exception{
		   
		   List<String> commandList = new ArrayList<String>();
		   
		   commandList.add("cpy 3 d");
		   commandList.add("cpy 5 c");
		   commandList.add("inc a");
		   commandList.add("dec c");
		   commandList.add("jnz c -2");
		   commandList.add("dec d");
		   commandList.add("jnz d -5");
		   assembler.executeProgram(commandList);
		   long result = assembler.getVariable("a");
		   assertTrue("Testng addition expecting: "+ "15" + " but found "+ result,result == 15);
		   
		   logger.debug(""+result);
		   
	   }

}
