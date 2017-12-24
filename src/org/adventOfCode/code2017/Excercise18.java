package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;
import org.adventOfCode.code2017.Program;

import java.util.*;

public class Excercise18 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise18.txt");

        int result = getLastFrequency(input);
        logger.debug("Result of excercise 18A: "+result);

        result = getSendCount(input);
        logger.debug("Result of excercise 18B: "+result);

    }

   public int getLastFrequency(List<String> input) throws Exception{
        Queue<Long> messages = new LinkedList<>();
        Program program = new Program(messages,messages,input);
        int maxMessages = 0;
        int lastMessage = -1;

        while(program.hasMoreInstructions()){
            program.executeCommand();
            if(messages.size() < maxMessages) return lastMessage;
            else if (maxMessages < messages.size()) {
                maxMessages = messages.size();
                lastMessage = (int)(long)messages.peek();
            }
        }

        return -1;
    }

    public int getSendCount(List<String> input)throws Exception{
        Queue<Long> messagesToA = new LinkedList<>();
        Queue<Long> messagesToB = new LinkedList<>();
        Program programA = new Program(messagesToA,messagesToB,input);
        Program programB = new Program(messagesToB,messagesToA,input);
        programA.setRegister("p",0);
        programB.setRegister("p",1);
        programA.setName("PROG_A ");
        programB.setName("PROG_B ");

        int result = 0;

        while(programA.hasMoreInstructions() || programB.hasMoreInstructions()){
            if(programA.hasMoreInstructions() ) programA.executeCommand();
            int lastAQueueSize = messagesToA.size();
            if(programB.hasMoreInstructions() ) programB.executeCommand();
            if(messagesToA.size() > lastAQueueSize) result++;
        }

        return result;
    }



}

