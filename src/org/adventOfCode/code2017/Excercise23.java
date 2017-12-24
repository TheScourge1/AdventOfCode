package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Excercise23 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise23.txt");

     /*   int result = countMulInstructions(input);
        logger.debug("Result of excercise 23A: "+result);*/

        input = FileUtil.readStringsFromFile("resource/2017/Excercise23B.txt");
        int result2 = getHValue(input);
        logger.debug("Result of excercise 22B: "+result2);

    }

    public int countMulInstructions(List<String> input) throws Exception{
        Program program = new Program(null,null,input);
        int result = 0;

        program.setRegister("a",0);
        while(program.hasMoreInstructions()){
            if(program.getNextCommand().startsWith("mul")) result++;
          //  logger.debug(program.debug(program.getNextCommand()));
            program.executeCommand();
        }

        return result;
    }

    public int getHValue(List<String> input) throws Exception{
        Program program = new Program(null,null,input);
        int result = 0;

        program.setRegister("a",1);
        while(program.hasMoreInstructions()){
            if(program.getNextCommand().startsWith("mul")) result++;
            program.executeCommand();
            if(result != (int)(long)program.getRegister("h"))
                logger.debug(program.debug(program.getNextCommand()));
            result = (int)(long)program.getRegister("h");
        }

        return (int)(long)program.getRegister("h");
    }
}

