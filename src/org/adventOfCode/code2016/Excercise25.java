package org.adventOfCode.code2016;

import java.util.List;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.code2016.BunnyAssemblerProcessor;
import org.adventOfCode.util.FileUtil;


public class Excercise25 extends GenericExcercise {

	
	
	@Override
	public void executeTest() throws Exception {

		String testInput = "resource/Excercise25.txt";
		List<String> input = FileUtil.readStringsFromFile(testInput);

		
		BunnyAssemblerProcessor processor = new BunnyAssemblerProcessor();
		
		for(int i = 0; i < 100000; i++)
		{
			try{
				processor.init();
				processor.setVariable("a", i);
				processor.executeProgram(input,clockOut);
			}
			catch(FoundException e1)
			{
				logger.debug("Result for excercise 25A:" + i);
				return;
			}
			
			catch(NotFoundException e)
			{
				logger.debug("failed at i: "+i+" "+e.getMessage());
			}
		}
	}
	
	BunnyAssemblerProcessor.Command clockOut = (a, b , variables) ->
	{
		if(variables.get("clock") == null) variables.put("clock", (variables.get(a)==null)?0:variables.get(a));
		else
		{
			if(!(((variables.get("clock") == 0 && variables.get(a) == 1))
					|| (variables.get("clock") == 1 && variables.get(a) == 0)))
			{
				if(variables.get("counter") != null && variables.get("counter")> 1)
					throw new NotFoundException("Not a valid clock sequence: found "+variables.get("clock") + " "+variables.get(a) + " "+variables.get("counter"));
			}
			else 
			{
				if(variables.get("counter") != null && variables.get("counter")> 10000L)
					throw new FoundException();
				if(variables.get("counter") == null) variables.put("counter", 1L);
				else variables.put("counter", variables.get("counter") +1);
				variables.put("clock", variables.get(a));
			}
		}
		return 1;
	};
	
	
	class NotFoundException extends RuntimeException
	{
		public NotFoundException(String message)
		{
			super(message);
		}
	}
	
	class FoundException extends RuntimeException
	{
	}
}