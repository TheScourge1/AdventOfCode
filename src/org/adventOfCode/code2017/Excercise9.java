package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Excercise9 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise9.txt");

        int result = getScore(input.get(0));
        logger.debug("Result of excercise 9A: "+result);

        result = getGarbageCount(input.get(0));
        logger.debug("Result of excercise 9B: "+result);

    }


    public int getScore(String input) {
        String noNegates = removeNegates(input);
        String noGarbage = removeGarbage(noNegates);
        return countScore(noGarbage);
    }

    public int getGarbageCount(String input){
        String noNegates = removeNegates(input);
        String garbage = getGarbage(noNegates);
        return garbage.length();
    }

    private String removeNegates(String input) {
        StringBuilder result = new StringBuilder();

        int index = 0;
        while(index < input.length()) {
            int newIndex = input.indexOf("!",index);
            if(newIndex > 0) {
                result.append(input.substring(index,newIndex));
                index = newIndex +2;
            }
            else {
                result.append(input.substring(index,input.length()));
                return result.toString();
            }
        }
        return result.toString();
    }

    private String removeGarbage(String input) {
        StringBuilder result = new StringBuilder();
        Pattern p = Pattern.compile("<[^>]*>");
        Matcher m = p.matcher(input);

        int index = 0;
        while(m.find()) {
            result.append(input.substring(index,m.start()));
            index = m.end();
        }
        if(index < input.length()) result.append(input.substring(index,input.length()));
        return result.toString();
    }

    private String getGarbage(String input)
    {
        StringBuilder result = new StringBuilder();
        Pattern p = Pattern.compile("<[^>]*>");
        Matcher m = p.matcher(input);

        while(m.find()) {
            result.append(input.substring(m.start()+1,m.end()-1));
        }
        return result.toString();
    }

    private int countScore(String input){
        int result = 0;

        int openBracketCount = 0;
        for(int i = 0; i < input.length(); i++)
        {
            if(input.substring(i,i+1).equals("{")) openBracketCount++;
            else if(input.substring(i,i+1).equals("}")){
                result += openBracketCount;
                openBracketCount--;
            }
        }

        return result;
    }
}

