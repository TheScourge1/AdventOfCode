package org.adventOfCode.code2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

public class BunnyAssemblerProcessor implements Callable<String>{

	static Logger logger = Logger.getLogger("AssemblerLogger");
	
	interface Command {
		long execute(String a, String b, Map<String, Long> variables);
	}

	Command inc = (a, b, variables) -> {
		if (b != null)
			return 1;
		if (!variables.keySet().contains(a))
			variables.put(a, 0L);
		variables.put(a, variables.get(a) + 1);
		return 1;
	};
	Command dec = (a, b, variables) -> {
		if (b != null)
			return 1;
		if (!variables.keySet().contains(a))
			variables.put(a, 0L);
		variables.put(a, variables.get(a) - 1);
		return 1;
	};
	Command cpy = (a, b, variables) -> {
		variables.put(b, variables.get(a));
		return 1;
	};
	Command add = (a, b, variables) -> {
		variables.put(b, variables.get(a) + variables.get(b));
		return 1;
	};

	Command mul = (a, b, variables) -> {
		variables.put(b, variables.get(a) * variables.get(b));
		return 1;
	};
	
	//HACK, Writes div in register a and mod in register b!!!
	Command div = (a, b, variables) -> {
		long tmpa = variables.get(a);
		long tmpb = variables.get(b);
		variables.put(a, tmpa / tmpb);
		variables.put(b, (tmpa % tmpb == 0)?tmpb:tmpa % tmpb);
		return 1;
	};
	Command out = (a, b, variables) -> {
		logger.debug(variables.get(a)); 
		return 1;
	};
	
	Command jnz = (a, b, variables) -> {
		return (variables.get(a) == 0) ? 1 : variables.get(b);
	};
	
	Map<String, Long> variables = new HashMap<String, Long>();
	
	
	Command outCommand = out;
	
	List<String> commandList = new ArrayList<String>();
	

	public void executeProgram(List<String> inputCommands,Command outCommand) throws Exception {
		int index = 0;
		int count = 0;
		while (index >= 0 && index < inputCommands.size()) {
			String[] command = inputCommands.get(index).split(" ");
			if (++count % 1000 == 0)
				logger.debug("Loop: "+ count);
			if (command[0].equals("inc"))
				index += aggregatedAdd(index,inputCommands);	
			else if (command[0].equals("dec"))
				index += execute(command[1], null, dec, variables);
			else if (command[0].equals("cpy")) 
			{
				long tmp = this.aggregatedMul(index, inputCommands);
				if(tmp == 0)  tmp = aggregatedDiv(index,inputCommands);
				if(tmp > 0 ) index += tmp;
				else index += execute(command[1], command[2], cpy, variables);
			}
				
			else if (command[0].equals("jnz"))
				index += execute(command[1], command[2], jnz, variables);
			else if (command[0].equals("tgl")) {

				long targetInd = 0;
				if (command[1].matches("(-)?\\d+"))
					targetInd = index + Long.parseLong(command[1]);
				else
					targetInd = index + variables.get(command[1]);
				if (targetInd >= inputCommands.size() || targetInd < 0)
					index++;
				else {
					inputCommands.set((int) targetInd, toggle(inputCommands.get((int) targetInd)));
					index++;
				}
			} 
			else if (command[0].equals("out"))
			{
				index+=execute(command[1], null, outCommand, variables);
			}
			else
				throw new Exception("Unknown command found" + command);

		}
	}
	
	public void executeProgram(List<String> inputCommands) throws Exception {
		
		executeProgram(inputCommands,out);
	}
	
	
	/**
	 * Reset stack
	 */
	public void init()
	{
		variables = new HashMap<String, Long>();
	}
	
	public void setVariable(String name, long value)
	{
		this.variables.put(name, value);
	}
	
	public long getVariable(String name)
	{
		return this.variables.get(name);
	}
	
	private long aggregatedAdd(int index, List<String> inputCommands)
	{
		String[] command = inputCommands.get(index).split(" ");
		
		if (index + 2 < inputCommands.size()) {
			String[] command1 = inputCommands.get(index + 1).split(" ");
			String[] command2 = inputCommands.get(index + 2).split(" ");
			if (command1[0].equals("dec") && command2[0].equals("jnz") && command1[1].equals(command2[1])
					&& command2[2].equals("-2")) {
				execute(command1[1], command[1], add, variables);
				execute("0", command1[1], cpy, variables);
				return 3;
			}
		}
		return execute(command[1], null, inc, variables);
	}
	
	
	private long aggregatedMul(int index, List<String> inputCommands)
	{
		String[] command = inputCommands.get(index).split(" ");
		
		if(index + 5 < inputCommands.size())
		{
			String[] command1 = inputCommands.get(index + 1).split(" ");
			String[] command2 = inputCommands.get(index + 2).split(" ");
			String[] command3 = inputCommands.get(index + 3).split(" ");
			String[] command4 = inputCommands.get(index + 4).split(" ");
			String[] command5 = inputCommands.get(index + 5).split(" ");
			if( command1[0].equals("inc") && command2[0].equals("dec") & command3[0].equals("jnz") 
					&& command4[0].equals("dec") && command5[0].equals("jnz") && command5[2].equals("-5")
					&& command3[2].equals("-2") && command5[1].equals(command4[1]) && command3[1].equals(command2[1]))
			{
				execute(command[1], command5[1], mul, variables);
				execute(command5[1], command1[1], add, variables);
				execute("0", command5[1], cpy, variables);
				execute("0", command3[1], cpy, variables);
				return 6;
			}
			
		}
		return 0;
	}
	
	private long aggregatedDiv(int index, List<String> inputCommands)
	{
		String[] command = inputCommands.get(index).split(" ");
		
		if(index + 7 < inputCommands.size())
		{
			String[] command1 = inputCommands.get(index + 1).split(" ");
			String[] command2 = inputCommands.get(index + 2).split(" ");
			String[] command3 = inputCommands.get(index + 3).split(" ");
			String[] command4 = inputCommands.get(index + 4).split(" ");
			String[] command5 = inputCommands.get(index + 5).split(" ");
			String[] command6 = inputCommands.get(index + 6).split(" ");
			String[] command7 = inputCommands.get(index + 7).split(" ");
			if( command[0].equals("cpy") && command1[0].equals("jnz") 
					&& command2[0].equals("jnz") & command3[0].equals("dec") 
					&& command4[0].equals("dec") && command5[0].equals("jnz") 
					&& command6[0].equals("inc") && command7[0].equals("jnz")
					&& command1[2].equals("2") && command2[2].equals("6")
					&& command5[2].equals("-4") && command7[2].equals("-7"))
			{
				execute(command[1],command[2],cpy,variables);
				execute(command3[1],"a", cpy, variables);
				execute("a", command[2], div, variables);
				execute("0", command3[1], cpy, variables);
				return 8;
			}
			
		}
		return 0;
	}
	
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return Returns the jump towards the next action
	 */
	private long execute(String a, String b, Command command, Map<String, Long> variables) {
		long result = 0;

		if (a.matches("(-)?\\d+"))
			variables.put(a, Long.parseLong(a));
		if (b != null && b.matches("(-)?\\d+"))
			variables.put(b, Long.parseLong(b));

		result = command.execute(a, b, variables);
		return result;
	}

	private String toggle(String inputCommand) {
		String[] command = inputCommand.split(" ");
		if (command.length == 2) {
			if (command[0].equals("inc"))
				return "dec " + command[1];
			else
				return "inc " + command[1];
		} else {
			if (command[0].equals("jnz"))
				return "cpy " + command[1] + " " + command[2];
			else
				return "jnz " + command[1] + " " + command[2];
		}
	}
	
	public void setOutCommand(Command out)
	{
		this.outCommand = out;
	}
	
	public void setCommandList(List<String> commands)
	{
		this.commandList = commands;
	}
	
	
    @Override 
    public String call() throws Exception {
    	executeProgram(this.commandList,this.outCommand);
    	return "";
    } 
}
