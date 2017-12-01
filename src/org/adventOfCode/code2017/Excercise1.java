package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.List;

public class Excercise1 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise1.txt");

        long result = calculateCaptcha(input.get(0));
        logger.debug("Result of excercise 1A: "+result);

        result = calculateCircularCaptcha(input.get(0));
        logger.debug("Result of excercise 1B: "+result);

    }


    public long calculateCaptcha(String input)
    {
        long result = 0;

        for(int i = 0; i < input.length();i++)
        {
            int nextIndex = (i+1)%(input.length());
            if(input.substring(i,i+1).equals(input.substring(nextIndex,nextIndex+1)))
                result += Long.parseLong(input.substring(i,i+1));
        }

        return result;
    }

    public long calculateCircularCaptcha(String input)
    {
        long result = 0;

        for(int i = 0; i < input.length();i++)
        {
            int nextIndex = (i+input.length()/2)%(input.length());
            if(input.substring(i,i+1).equals(input.substring(nextIndex,nextIndex+1)))
                result += Long.parseLong(input.substring(i,i+1));
        }

        return result;
    }

}
