package org.adventOfCode.code2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;


public class Excercise23Old extends GenericExcercise {
	
	@Override
	public void executeTest() throws Exception {

		String testInput = "resource/Excercise23.txt";
		List<String> input = FileUtil.readStringsFromFile(testInput);
		
		long result = executeProgram(input,7);
		logger.debug("Result for excercise 23A:" + result);
		
		input = FileUtil.readStringsFromFile(testInput);
		result = executeProgram(input,12);
		logger.debug("Result for excercise 23B:" + result);

	}
	
	Command inc = (a,b,variables) -> { if(b != null) return 1;
									   if(!variables.keySet().contains(a)) variables.put(a,0L); 
							           variables.put(a, variables.get(a)+1); return 1;};
	Command dec = (a,b,variables) -> { if(b != null) return 1;
										if(!variables.keySet().contains(a)) variables.put(a,0L); 
    								   variables.put(a, variables.get(a)-1);return 1;};
	Command cpy = (a,b,variables) -> { variables.put(b, variables.get(a)); return 1;};
	
	Command jnz = (a,b,variables) -> { 
		return (variables.get(a) == 0)? 1 : variables.get(b);};
	
	
	
	public long executeProgram(List<String> inputCommands, long aValue) throws Exception
	{
		Map<String,Long> variables = new HashMap<String,Long>();
		variables.put("a", aValue);
		int index = 0;
		int count = 0;
		while(index >= 0 && index < inputCommands.size())
		{
			String[] command = inputCommands.get(index).split(" ");
			//logger.debug(""+index+inputCommands.get(index) + " "+variables.toString());
			if(count++ %1000 == 0) logger.debug(count);
			if(command[0].equals("inc")) index+=execute(command[1],null,inc,variables);
			else if(command[0].equals("dec")) index+=execute(command[1],null,dec,variables);
			else if(command[0].equals("cpy")) index+=execute(command[1],command[2],cpy,variables);
			else if(command[0].equals("jnz")) index+= execute(command[1],command[2],jnz,variables);
			else if(command[0].equals("tgl")) 
			{
				
				long targetInd = 0;
				if(command[1].matches("(-)?\\d+"))
					targetInd = index + Long.parseLong(command[1]);
				else targetInd = index + variables.get(command[1]);
				if(targetInd >= inputCommands.size() || targetInd < 0) index++;
				else
				{
					//logger.debug(inputCommands.get(targetInd) + "\t"+toggle(inputCommands.get(targetInd)));
					inputCommands.set((int)targetInd, toggle(inputCommands.get((int)targetInd)));
					index++;
				}
				
				
			}
			else throw new Exception("Unknown command found"+command);
			
		}

		return variables.get("a");
	}
	
	
	interface Command{
		long execute(String a,String b,Map<String,Long> variables);
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 * Returns the jump towards the next action
	 */
	public long execute(String a,String b,Command command,Map<String,Long> variables)
	{
		long result = 0;
		
		if(a.matches("(-)?\\d+"))
			variables.put(a, Long.parseLong(a));
		if(b != null  && b.matches("(-)?\\d+"))
			variables.put(b, Long.parseLong(b));
		
		result = command.execute(a,b,variables);
		return result;
	}
	
	public String toggle(String inputCommand)
	{
		String[] command = inputCommand.split(" ");
		if(command.length == 2)
		{
			if(command[0].equals("inc")) return "dec " + command[1];
			else return "inc " + command[1];
		}
		else
		{
			if(command[0].equals("jnz")) return "cpy "+command[1]+" "+ command[2];
			else return "jnz "+command[1]+" " + command[2];
		}
	}
	
}