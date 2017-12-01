package org.adventOfCode.code2016;


import org.adventOfCode.GenericExcercise;

public class Excercise16 extends GenericExcercise {

	@Override
	public void executeTest() throws Exception {


		String result = getCheckSum(272,"10111100110001111");  
		logger.debug("Result for excercise 15A:" + result);
		
		result = getCheckSum(35651584,"10111100110001111");  
		logger.debug("Result for excercise 15B:" + result);
	}

	
	public String getCheckSum(int size,String input)
	{
		StringBuilder data = new StringBuilder(input);
		
		while(data.length() < size)
		{
			String a = data.toString();
			StringBuilder b = new StringBuilder(data.length());
			for(int i = a.length()-1; i>=0;i--)
			{
				if(a.charAt(i)== '1') b.append("0");
				else b.append("1");
			}
			
			data = new StringBuilder();
			data.append(a);
			data.append("0");
			data.append(b);
		}
		
		String resultHash = data.subSequence(0, size).toString();
		
		while(resultHash.length()%2 == 0) resultHash = calcCheckSum(resultHash);
		
		return resultHash;
	}


	private String calcCheckSum(String input)
	{
		StringBuilder result = new StringBuilder();
		
		for(int i = 0; i< input.length()/2; i++)
		{
			if(input.charAt(2*i) == input.charAt(2*i+1)) result.append("1");
			else result.append("0");
		}
				
		return result.toString();
	}
	
	
	
}