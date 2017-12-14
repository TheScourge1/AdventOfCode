package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Excercise10 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise10.txt");

        int result = getMultiplyValue(input.get(0),255);
        logger.debug("Result of excercise 10A: "+result);

        String hash = getDenseHash(input.get(0));
        logger.debug("Result of excercise 10B: "+hash);

    }

    private static List<Integer> CODE_SUFFIX = Arrays.asList(17, 31, 73, 47, 23);

    public int getMultiplyValue(String input,int maxValue){

        List<Integer> intList = new ArrayList<Integer>();
        for(int i = 0; i<=maxValue; i++) intList.add(i);

        List<Integer> inputValues = Arrays.stream(input.split(","))
                .mapToInt(s -> Integer.parseInt(s))
                .boxed()
                .collect(Collectors.toList());

        List<Integer> resultList = getHashValue(inputValues,intList,1);
        return resultList.get(0)*resultList.get(1);
    }


    public String getDenseHash(String input)
    {
        List<Integer> inputValues = input.chars().boxed().collect(Collectors.toList());
        inputValues.addAll(CODE_SUFFIX);

        List<Integer> resultList = new ArrayList<Integer>();
        for(int i = 0; i<=255; i++) resultList.add(i);

        resultList = getHashValue(inputValues,resultList,64);

        List<Integer> denseList = new ArrayList<Integer>();

        for(int offset = 0; offset<16;offset++){
            int result = resultList.get(offset*16);
            for(int i = 1; i < 16; i++) result = result ^ resultList.get(i+16*offset);
            denseList.add(result);
        }

        StringBuilder b = new StringBuilder();
        denseList.stream().forEach( value -> {
            if(Integer.toHexString(value).length() == 1) b.append("0");
            b.append(Integer.toHexString(value));
        });

        return b.toString();
    }


    private List<Integer> getHashValue(List<Integer> inputValues,List<Integer> hashList,int itterations){
        List<Integer> intList = new ArrayList(hashList);

        int skipSize = 0;
        int index = 0;
        for(int itt = 0; itt < itterations; itt++) {
            for (Integer inputValue : inputValues) {
                List<Integer> reverseList = new ArrayList<Integer>();

                reverseList.addAll(intList.subList(index, Math.min(index + inputValue, intList.size())));
                if (inputValue + index > intList.size())
                    reverseList.addAll(intList.subList(0, inputValue + index - intList.size()));
                Collections.reverse(reverseList);
                for (int i = index; i < index + inputValue; i++)
                    intList.set(i % intList.size(), reverseList.get(i - index));

                index = (index + inputValue + skipSize) % intList.size();
                skipSize++;
            }
        }

        return intList;
    }

}

