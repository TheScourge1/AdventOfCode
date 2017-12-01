package org.adventOfCode.code2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;





public class Excercise12 extends GenericExcercise {


	@Override
	public void executeTest() throws Exception {
		
		String testInput = "resource/Excercise12A.txt";
		List<String> input = FileUtil.readStringsFromFile(testInput);
		int result = processData(input);
		
		logger.debug("Result for excercise 12A:" + result);

		testInput = "resource/Excercise12B.txt";
		input = FileUtil.readStringsFromFile(testInput);
		result = processData(input);
		logger.debug("Result for excercise 12B:" + result);

	}
	
	public int processData(String testInput) throws Exception
	{
		List<String> input = FileUtil.readStringsFromFile(testInput);
		
		return processData(input);
	}
	
	public int processData(List<String> instructions) throws Exception
	{
		int result = 0;
		
		Memory memory = new Memory();
		
		int itterator = 0;
		while(itterator < instructions.size())
		{
			String instruction = instructions.get(itterator);

			String[] instructionParts = instruction.split(" ");
			String command = instructionParts[0];
			
		//	logger.debug(""+itterator+" Processing: "+instruction);
					
			if(command.equals("cpy"))
			{
				memory.copy(instructionParts[1], instructionParts[2]);
				
			} else if(command.equalsIgnoreCase("inc"))
			{
				memory.inc(instructionParts[1]);
				
			} else if(command.equals("dec"))
			{
				memory.dec(instructionParts[1]);
				
			} else if(command.equals("jnz"))
			{
				int jump = memory.jnz(instructionParts[1], instructionParts[2]);
				if(jump != 0)
				{
					itterator += jump;
					continue;
				}
				
			} else throw new Exception("Unknown command found: "+instruction);
			
			itterator++;
		}
		
		return memory.getRegister("a");
		
	}
	
	
	private class Memory
	{
		Map<String,Integer> registers = new HashMap<String,Integer> ();
		
		public Memory()
		{
			registers.put("a",0);
			registers.put("b",0);
			registers.put("c",0);
			registers.put("d",0);
		}
		
		
		public void copy(String x, String y) throws Exception
		{
			if(!registers.containsKey(y)) throw new Exception("Unknown register found at y: "+y);
			
			int xVal = 0;
			if(registers.containsKey(x)) xVal = registers.get(x);
			else xVal = Integer.parseInt(x);
			
			registers.put(y, xVal);
		}
		
		public void inc(String x) throws Exception
		{
			if(!registers.containsKey(x)) throw new Exception("Unknown register found at x: "+x);
			int xVal = registers.get(x);
			xVal++;
			registers.put(x, xVal);
		}
		
		public void dec(String x) throws Exception
		{
			if(!registers.containsKey(x)) throw new Exception("Unknown register found at x: "+x);
			int xVal = registers.get(x);
			xVal--;
			registers.put(x, xVal);
		}
		
		public int jnz(String x, String y)
		{
			int xVal = 0;
			if(registers.containsKey(x)) xVal = registers.get(x);
			else xVal = Integer.parseInt(x);
			if (xVal == 0) return 0;
			else 
			{
				int yVal = 0;
				if(registers.containsKey(y)) yVal = registers.get(y);
				else yVal = Integer.parseInt(y);
				return yVal;
			}
		}
		
		public int getRegister(String register)
		{
			return registers.get(register);
		}
	}
		
}