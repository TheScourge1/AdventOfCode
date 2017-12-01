package org.adventOfCode.code2016;

import java.util.List;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;


public class Excercise21 extends GenericExcercise {
	
	@Override
	public void executeTest() throws Exception {

		String testInput = "resource/Excercise21.txt";
		List<String> input = FileUtil.readStringsFromFile(testInput);
		
		String result = scramble(input,"abcdefgh"); 
		logger.debug("Result for excercise 21A:" + result);
		
		result = unScramble(input,"fbgdceah");
		logger.debug("Result for excercise 21B:" + result);

	}
	
	
	public String unScramble(List<String> inputInstructions,String code) throws Exception
	{
		String result = code;
		
		for(int i = inputInstructions.size()-1;i>=0;i--)
		{
			String s = inputInstructions.get(i);
			
			String[] ssplit = s.split(" ");
			if(ssplit[0].equals("swap") && ssplit[1].equals("position"))
			{
				result = swapPosition(Integer.parseInt(ssplit[2]),Integer.parseInt(ssplit[5]),result);
			}
			else if(ssplit[0].equals("swap") && ssplit[1].equals("letter"))
			{
				result = swapLetter(ssplit[2],ssplit[5],result);
			}
			else if(ssplit[0].equals("rotate") && ssplit[1].equals("left"))
			{
				result = rotateSteps(1, Integer.parseInt(ssplit[2]), result);
			}
			else if(ssplit[0].equals("rotate") && ssplit[1].equals("right"))
			{
				result = rotateSteps(-1, Integer.parseInt(ssplit[2]), result);
			}		
			else if(ssplit[0].equals("rotate") && ssplit[1].equals("based"))
			{
				result = inverseRotatePositionBased(ssplit[6], result);
			}
			else if(ssplit[0].equals("reverse"))
			{
				result = reverse(Integer.parseInt(ssplit[2]),Integer.parseInt(ssplit[4]),result);
			}
			else if(ssplit[0].equals("move"))
			{
				result = move(Integer.parseInt(ssplit[5]),Integer.parseInt(ssplit[2]),result);
			}
			else throw new Exception("Unexpected command found: "+s);	
			
		//	logger.debug(result+ "\t("+s+")");
		}
		
		
		return result;
	}
	
	
	public String scramble(List<String> inputInstructions,String code) throws Exception
	{
		String result = code;
		
		for(String s : inputInstructions)
		{
			String[] ssplit = s.split(" ");
			if(ssplit[0].equals("swap") && ssplit[1].equals("position"))
			{
				result = swapPosition(Integer.parseInt(ssplit[2]),Integer.parseInt(ssplit[5]),result);
			}
			else if(ssplit[0].equals("swap") && ssplit[1].equals("letter"))
			{
				result = swapLetter(ssplit[2],ssplit[5],result);
			}
			else if(ssplit[0].equals("rotate") && ssplit[1].equals("left"))
			{
				result = rotateSteps(-1, Integer.parseInt(ssplit[2]), result);
			}
			else if(ssplit[0].equals("rotate") && ssplit[1].equals("right"))
			{
				result = rotateSteps(1, Integer.parseInt(ssplit[2]), result);
			}		
			else if(ssplit[0].equals("rotate") && ssplit[1].equals("based"))
			{
				result = rotatePositionBased(ssplit[6], result);
			}
			else if(ssplit[0].equals("reverse"))
			{
				result = reverse(Integer.parseInt(ssplit[2]),Integer.parseInt(ssplit[4]),result);
			}
			else if(ssplit[0].equals("move"))
			{
				result = move(Integer.parseInt(ssplit[2]),Integer.parseInt(ssplit[5]),result);
			}
			else throw new Exception("Unexpected command found: "+s);	
			
			//logger.debug(result+ "\t("+s+")");
		}
		
		
		return result;
	}
	

	public String swapPosition(int xIn,int yIn,String code)
	{
		int x = Math.min(xIn, yIn);
		int y = Math.max(xIn, yIn);
		
		StringBuilder b = new StringBuilder();
		char xChar = code.charAt(x);
		char yChar = code.charAt(y);
		b.append(code.substring(0,x));
		b.append(yChar);
		b.append(code.substring(x+1,y));
		b.append(xChar);
		b.append(code.substring(y+1,code.length()));
		
		return b.toString();
	}
	
	public String swapLetter(String x,String y,String code)
	{
		return swapPosition(code.indexOf(x),code.indexOf(y),code);
	}
	
	/**
	 * @param direction
	 * Left = -1, right = 1
	 * @param steps
	 * @param code
	 * @return
	 */
	public String rotateSteps(int direction,int steps,String code)
	{
		if(direction == 1)
			return code.substring(code.length()-steps,code.length()) + code.substring(0,code.length()-steps);
		else
			return code.substring(steps,code.length()) + code.substring(0,steps);
		
	}
	
	public String rotatePositionBased(String s,String code)
	{
		int rotatePosition = code.indexOf(s)+1;
		if(rotatePosition > 4) rotatePosition++;
		return rotateSteps(1,rotatePosition%code.length(),code);
	}
	
	public String inverseRotatePositionBased(String s,String code)
	{
		String result = code;
		while(!rotatePositionBased(s,result).equals(code)) result = rotateSteps(-1,1,result);
		
		return result;
	}
	
	public String reverse(int xIn,int yIn,String code)
	{
		int x = Math.min(xIn, yIn);
		int y = Math.max(xIn, yIn);
		
		StringBuilder b = new StringBuilder();
		b.append(code.substring(0,x));
		for(int i = y;i>=x ; i--) b.append(code.charAt(i));
		b.append(code.substring(y+1,code.length()));
		
		return b.toString();
	}
	
	public String move(int xIn,int yIn,String code)
	{
		StringBuilder b = new StringBuilder();
		char charToMove = code.charAt(xIn);
		b.append(code.substring(0,xIn));
		b.append(code.substring(xIn+1,code.length()));
		b.insert(yIn, charToMove);
		
		return b.toString();
	}
}