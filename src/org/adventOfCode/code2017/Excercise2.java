package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

public class Excercise2 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise2.txt");

        long result = calculateChecksum(input);
        logger.debug("Result of excercise 2A: "+result);

        result = calculateEvenChecksum(input);
        logger.debug("Result of excercise 2B: "+result);

    }


    public long calculateChecksum(List<String> input)
    {
        long result = 0;

        for(String s : input)
        {
           List<Integer> intList = Arrays.stream(s.split("\\s")).map( i -> Integer.parseInt(i))
                                                                    .sorted()
                                                                    .collect(Collectors.toList());
           result += intList.get(intList.size()-1) - intList.get(0);
        }

        return result;
    }

    public long calculateEvenChecksum(List<String> input)
    {
        long result = 0;

        for(String s : input)
        {
            List<Integer> intList = Arrays.stream(s.split("\\s")).map( i -> Integer.parseInt(i))
                    .sorted()
                    .collect(Collectors.toList());


            for(int i = 0; i < intList.size(); i++)
            {
                int div = 0;

                for(int j = i+1; j < intList.size();j++)
                {
                    if(intList.get(j)%intList.get(i) == 0)
                    {
                        div = intList.get(j)/intList.get(i);
                        result += div;
                        break;
                    }
                }
                if(div > 0) break;
            }
        }

        return result;
    }



}
