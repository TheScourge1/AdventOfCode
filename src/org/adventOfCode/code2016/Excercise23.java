package org.adventOfCode.code2016;

import java.util.List;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.code2016.BunnyAssemblerProcessor;
import org.adventOfCode.util.FileUtil;


public class Excercise23 extends GenericExcercise {
	
	@Override
	public void executeTest() throws Exception {

		String testInput = "resource/Excercise23.txt";
		List<String> input = FileUtil.readStringsFromFile(testInput);
		
		long result = executeProgram(input,7);
		logger.debug("Result for excercise 23A:" + result); //13776
		
		input = FileUtil.readStringsFromFile(testInput);
		result = executeProgram(input,12);
		logger.debug("Result for excercise 23B:" + result); //result 479010336 in 1472728ms for 3501380001 loops (no optimisation)
															//				   in 867414ms  for 1376233001 loops (adding an add function)
															//				   in 10ms (mul) implementation
	}
	
	BunnyAssemblerProcessor processor = new BunnyAssemblerProcessor();
	
	
	public long executeProgram(List<String> inputCommands, long aValue) throws Exception
	{
		processor.init();
		processor.setVariable("a",aValue);
		processor.executeProgram(inputCommands);

		return processor.getVariable("a");
	}	
}