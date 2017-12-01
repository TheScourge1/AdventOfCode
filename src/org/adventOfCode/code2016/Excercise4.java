package org.adventOfCode.code2016;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;



public class Excercise4 extends GenericExcercise {

	private final String RESOURCE_FILE = "resource/Excercise4.txt";
	
	@Override
	public void executeTest() throws Exception {
		
		List<String> fileInput =  FileUtil.readStringsFromFile(RESOURCE_FILE);
		if(fileInput == null || fileInput.size() == 0)
			throw new Exception(RESOURCE_FILE);
		
		logger.info("Result of 4A: "+getCodeSum(fileInput));
		
		List<RoomCode> codeList = getValidCodeList(fileInput);
		for(RoomCode c : codeList)
		{
			if(c.decrypt().contains("northpole")) 
				logger.debug(c.decrypt()+" ID: "+c.getCode());
		}

	}
	

	
	public long getCodeSum(List<String> input)
	{
		AtomicLong result = new AtomicLong(0);
		
		getValidCodeList(input).stream()
				.forEach(r -> result.addAndGet(r.getCode()));	
		
		return result.get();
	}
	
	public List<RoomCode> getValidCodeList(List<String> input)
	{
		List<RoomCode> result = input.stream()
		.map((String s) -> new RoomCode(s))
		.filter(r -> r.isValidCode()).collect(Collectors.toList());
		
		return result;
	}
	
	public String decrypt(String code)
	{
		RoomCode c = new RoomCode(code);
		return c.decrypt();
	}

	public class RoomCode
	{
		String oriCode;
		String input;
		long code;
		String checkDigits;
		
		public RoomCode(String s) throws RuntimeException
		{
			oriCode = s;
			int numberSequenceStart = s.lastIndexOf("-");
			int numberSequenceEnd = s.lastIndexOf("[");
			
			try{
			input = s.substring(0,numberSequenceStart);
			code = Integer.parseInt(s.substring(numberSequenceStart+1,numberSequenceEnd));
			checkDigits = s.substring(numberSequenceEnd+1,s.length()-1);
			}catch(Exception e)
			{
				throw new RuntimeException("Invalid code: "+oriCode,e);
			}
			
			
		}
		
		public boolean isValidCode()
		{
			int[] charArray = new int[26];
			
			
			if(input != null)
			{
				/*counting all digits*/
				input.chars().filter( c -> c >= 'a'&& c <= 'z')
							 .forEach((c) -> charArray[c-'a']+=1);

				/*get the 5 highest ones*/
				AtomicInteger lowestValue = new AtomicInteger(0);
				checkDigits.chars().forEach((c) -> 
											 {if(lowestValue.get()== 0 ||  lowestValue.get()>charArray[c-'a'])
											  	lowestValue.set(charArray[c-'a']);});
				
				//check if no char occurs more than the least occuring check digit
				for(int i = 0; i < charArray.length;i++) 
					if(charArray[i] > lowestValue.get() && checkDigits.indexOf('a'+i) < 0) return false;
				
				//check order
				for(int i = 0; i < checkDigits.length()-1;i++) 
					if(charArray[checkDigits.charAt(i)-'a'] < charArray[checkDigits.charAt(i+1)-'a'] ) return false;
				
				//check alphabetical ties
				
				
				
				return (lowestValue.get() == 0)?false:true;
			}
			else return false;
			
		}
		
		public String decrypt()
		{
			StringBuilder result = new StringBuilder();
			
			input.chars().map(c -> (c == '-'? ' ' : (int)('a'+((c+code-'a') % 26))))
			             .forEach( c -> result.append((char)c));
						 
			
			return result.toString();
		}
		
		public long getCode()
		{
			return code;
		}
		
		public String toString(){ return oriCode;}
	}
	
}
