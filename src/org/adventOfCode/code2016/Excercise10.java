package org.adventOfCode.code2016;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;





public class Excercise10 extends GenericExcercise {

	int BOTLISTSIZE = 1000;
	List<Bot> botList = new ArrayList<Bot>();
	
	
	@Override
	public void executeTest() throws Exception {
		
		String testInput = "resource/Excercise10.txt";
		procesBotData(testInput);
		int result = findComparatorBot(17,61);
		
		logger.debug("Result for excercise 10A:" + result);
		result = findOutPutMultiplier();
		logger.debug("Result for excercise 10B:" + result);

	}
	
	
	public void procesBotData(String fileName) throws Exception
	{
		List<String> botCommands = FileUtil.readStringsFromFile(fileName);
		botList = new ArrayList<Bot>();
		for(int i = 0; i < botCommands.size(); i++) botList.add(new Bot(i));
		
		for(String command : botCommands)
		{
			String[] words = command.split(" "); 
					
			if(words[0].equals("value"))
			{
				int value = Integer.parseInt(words[1]);
				int botIndex = Integer.parseInt(words[5]);
				
				botList.get(botIndex).setValue(value);
			}
			else if(words[0].equals("bot"))
			{
				int botIndex = Integer.parseInt(words[1]);
				
				if(words[5].equals("bot")) botList.get(botIndex).setLowTo(Integer.parseInt(words[6]),false);
				if(words[5].equals("output")) botList.get(botIndex).setLowTo(Integer.parseInt(words[6]),true);
				if(words[10].equals("bot")) botList.get(botIndex).setHighTo(Integer.parseInt(words[11]),false);
				if(words[10].equals("output")) botList.get(botIndex).setHighTo(Integer.parseInt(words[11]),true);
			}
			else throw new Exception("Unknown command found:" + command);
		}
		
		boolean botChanged = false;
		
		do
		{
			//botList.stream().forEach( b -> logger.debug(b.toString()+" "));
			botChanged = false;
			List<Bot> botsToProcess = botList.stream().filter(b -> b.isBotFull())
								  .collect(Collectors.toList());
			
			for(Bot b : botsToProcess)
			{
				if(b.getLowTo() == -1 || b.getHighTo() == -1) throw new Exception("Missing to value");
				else
				{	
					if(!b.isLowToOutput() && !botList.get(b.getLowTo()).isBotFull()) 
					{
						botList.get(b.getLowTo()).setValue(b.getLowValue());
						botChanged = true;
					}
					if(!b.isHighToOutput() && !botList.get(b.getHighTo()).isBotFull()) 
					{
						botList.get(b.getHighTo()).setValue(b.getHighValue());
						botChanged = true;
					}
				}
			}
		}while(botChanged);
	}
	
	public int findComparatorBot(int lowVal, int highVal)
	{
		for(Bot b : botList)
		{
			if(b.getLowValue() == lowVal && b.getHighValue() == highVal) return b.getBotIndex();
		}
		
		return -1;
	}
	
	public int findOutPutMultiplier()
	{
		int result = 1;
		
		for(Bot b : botList)
		{
			if(b.isLowToOutput() && b.getLowTo() >=0 && b.getLowTo()<3) result *=b.getLowValue();
			if(b.isHighToOutput() && b.getHighTo() >=0 && b.getHighTo()<3) result *=b.getHighValue();
		}
		
		return result;
	}
	
	
	 private class Bot{
		   int lowValue = -1;
		   int highValue = -1;
		   int lowTo = -1;
		   int highTo = -1;
		   int botIndex = -1;
		   
		

		boolean lowToOutput = false;
		   boolean highToOutput = false;
		   
		 
		private Bot(int index,int value) throws Exception
		{
			botIndex = index;
			this.setValue(value);
		}
		
		private Bot(int index) 
		{
			botIndex = index;
		}
		   
		public int getLowValue() {
			return lowValue;
		}

		public int getHighValue() {
			return highValue;
		}
		
		public void setValue(int value) throws Exception
		{
			if(highValue != -1) throw new Exception("Error: bot fields allready full");
			if(lowValue == -1 && highValue == -1) lowValue = value;
			else
			{
				if(lowValue <= value) highValue = value;
				else
				{
					highValue = lowValue;
					lowValue = value;
				}
			}
		}
		
		public int getLowTo() {
			return lowTo;
		}
		public void setLowTo(int lowTo, boolean toOutput) {
			this.lowTo = lowTo;
			lowToOutput = toOutput;
		}
		public int getHighTo() {
			return highTo;
		}
		public void setHighTo(int highTo,boolean toOutput) {
			this.highTo = highTo;
			highToOutput = toOutput;
		}
		    
		public boolean isBotFull()
		{
			return lowValue != -1 && highValue != -1;
		}

		public int getBotIndex() {
			return botIndex;
		}

	    public boolean isLowToOutput() {
			return lowToOutput;
		}

		public boolean isHighToOutput() {
			return highToOutput;
		}
		
		 public String toString()
		 {
			 return ""+this.botIndex+"["+this.lowValue+","+this.highValue+"]";
		 }
		
	   }
	 
	
}
