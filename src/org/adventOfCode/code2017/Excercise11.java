package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Excercise11 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise11.txt");

        int result = getStepCount(input.get(0));
        logger.debug("Result of excercise 11A: "+result);

        result = getMaxDistance(input.get(0));
        logger.debug("Result of excercise 11B: "+result);

    }

    public int getStepCount(String input){
        int result = 0;
        String[] inputList = input.split(",");

        double x = 0, y = 0;
        for(String s : inputList){
            if(s.startsWith("n")) {if(s.length() == 1) y++; else y+=0.5d;};
            if(s.startsWith("s")) {if(s.length() == 1) y--; else y-=0.5d;};
            if(s.endsWith("e")) {if(s.length() == 1) x++; else x+=0.5d;};
            if(s.endsWith("w")) {if(s.length() == 1) x--; else x-=0.5d;};
        }

        result = (int)Math.round(Math.abs(x)+Math.abs(y));

        return result;
    }

    public int getMaxDistance(String input){
        int result = 0;
        String[] inputList = input.split(",");

        double x = 0, y = 0;
        for(String s : inputList){
            if(s.startsWith("n")) {if(s.length() == 1) y++; else y+=0.5d;};
            if(s.startsWith("s")) {if(s.length() == 1) y--; else y-=0.5d;};
            if(s.endsWith("e")) {if(s.length() == 1) x++; else x+=0.5d;};
            if(s.endsWith("w")) {if(s.length() == 1) x--; else x-=0.5d;};
            int temp = (int)Math.round(Math.abs(x)+Math.abs(y));
            if(temp > result) result = temp;
        }

        return result;
    }

}

