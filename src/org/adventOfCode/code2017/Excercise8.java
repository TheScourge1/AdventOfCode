package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Excercise8 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise8.txt");

        int result = getMaxRegisterValue(input);
        logger.debug("Result of excercise 8A: "+result);

        result = getAbsoluteMaxRegisterValue(input);
        logger.debug("Result of excercise 8B: "+result);

    }

    private Pattern numberPattern = Pattern.compile("-?\\d+");

    Map<String,Integer> registers = new HashMap<String,Integer>();

    Command dec = (a,b) -> {return a - b;};
    Command inc = (a,b) -> {return a + b;};

    public void reset()
    {
        registers = new HashMap<String,Integer>();
    }

    public int getMaxRegisterValue(List<String> input) throws Exception
    {
        reset();
        executeProgram(input);
        return registers.values().stream().max(Comparator.naturalOrder()).get();
    }

    public int getAbsoluteMaxRegisterValue(List<String> input) throws Exception
    {
        int result = 0;
        reset();
        for(String command : input) {
            List<String> tempList = new ArrayList<String>();
            tempList.add(command);
            executeProgram(tempList);
            if(registers.size() > 0) {
                int maxValue =  registers.values().stream().max(Comparator.naturalOrder()).get();
                if(maxValue > result) result = maxValue;
            }

        }

        return result;
    }

    private void executeProgram(List<String> input) throws Exception
    {
        for(String s : input)
        {
            String[] operation = s.split(" ");
            Command command;
            if(operation[1].equals("dec")) command = dec;
            else if(operation[1].equals("inc")) command = inc;
            else throw new Exception("Unknown command found: "+ operation[1]);

            if(validateCondition(operation[4],operation[6],operation[5]))
                executeCommand(operation[0],command,operation[0],operation[2]);
        }
    }

    private boolean validateCondition(String variableA,String variableB,String operator) throws Exception
    {
        int a = convertToIntValue(variableA);
        int b = convertToIntValue(variableB);

        if(operator.equals(">")) return a > b;
        if(operator.equals(">=")) return a >= b;
        if(operator.equals("<")) return a < b;
        if(operator.equals("<=")) return a <= b;
        if(operator.equals("==")) return a == b;
        if(operator.equals("!=")) return a != b;

        throw new Exception("Unknown comparator: "+operator);
    }

    private void executeCommand(String target,Command command,String variableA,String variableB)
    {
        int a = convertToIntValue(variableA);
        int b = convertToIntValue(variableB);

        int result = command.execute(a,b);
        registers.put(target,result);
    }

    private interface Command{
        public int execute(Integer a,Integer b);
    }

    private int convertToIntValue(String variable)
    {
        int result = 0;
        if(numberPattern.matcher(variable).matches()) result = Integer.parseInt(variable);
        else {
            if(registers.containsKey(variable)) result = registers.get(variable);
            else result = 0;
        }

        return result;
    }

}

