package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;

public class Excercise4 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise4.txt");

        long result = countParafrases(input);
        logger.debug("Result of excercise 4A: "+result);

        result = countAnagrams(input);
        logger.debug("Result of excercise 4B: "+result);

    }

    public int countParafrases(List<String> input)
    {
        int result = 0;

        for(String s : input)
        {
            Map<String,Boolean> words = new HashMap<String,Boolean>();

            long doubles = Arrays.stream(s.split(" "))
                    .filter( w -> words.putIfAbsent(w,true) != null)
                    .count();
            if(doubles == 0) result++;
        }

        return result;
    }

    public int countAnagrams(List<String> input)
    {
        int result = 0;

        for(String s : input)
        {
            Map<String,Boolean> words = new HashMap<String,Boolean>();

            long doubles = Arrays.stream(s.split(" "))
                    .map( w -> {
                        char[] chars = w.toCharArray();
                        Arrays.sort(chars);
                        return new String(chars);
                    })
                    .filter( w -> words.putIfAbsent(w,true) != null)
                    .count();
            if(doubles == 0) result++;
        }

        return result;
    }

}
