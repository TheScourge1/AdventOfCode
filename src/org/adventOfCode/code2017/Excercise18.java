package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;
import java.util.regex.Pattern;

public class Excercise18 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise18.txt");

        int result = getLastFrequency(input);
        logger.debug("Result of excercise 18A: "+result);

        result = getSendCount(input);
        logger.debug("Result of excercise 18B: "+result);

    }

    private static Pattern numberPattern = Pattern.compile("-?(\\d)*");

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
        programA.name = "PROG_A ";
        programB.name = "PROG_B ";

        int result = 0;

        while(programA.hasMoreInstructions() || programB.hasMoreInstructions()){
            if(programA.hasMoreInstructions() ) programA.executeCommand();
            int lastAQueueSize = messagesToA.size();
            if(programB.hasMoreInstructions() ) programB.executeCommand();
            if(messagesToA.size() > lastAQueueSize) result++;
        }

        return result;
    }


    private class Program{
        private String name;
        private Queue<Long> messagesIn = new LinkedList<>();
        private Queue<Long> messagesOut = new LinkedList<>();
        Map<String,Long> register = new HashMap<>();
        List<String> commands = new ArrayList<>();
        boolean locked = false;

        int index=0;

        Command snd = (a,b,v) -> {messagesOut.add(convertVar(a,v)); return 1;};
        Command set = (a,b,v) -> {v.put(a,convertVar(b,v)); return 1;};
        Command add = (a,b,v) -> {v.put(a,convertVar(a,v)+convertVar(b,v)); return 1;};
        Command mul = (a,b,v) -> {v.put(a,convertVar(a,v)*convertVar(b,v));return 1;};
        Command mod = (a,b,v) -> {v.put(a,convertVar(a,v)%convertVar(b,v));return 1;};
        Command rcv = (a,b,v) -> {v.put(a,messagesIn.poll()) ; return 1;};
        Command jgz = (a,b,v) -> {return (convertVar(a,v) > 0L)? (int)convertVar(b,v): 1;};

        private Program(Queue<Long> messagesIn,Queue<Long> messagesOut,List<String> commands){
            this.commands = commands;
            this.messagesIn = messagesIn;
            this.messagesOut = messagesOut;
        }

        private void setRegister(String reg,long value){
            register.put(reg,value);
        }

        private void executeCommand() throws Exception{
            String command = commands.get(index);
            String[] commandParts = command.split(" ");
            if(commandParts[0].equals("snd")) index +=snd.execute(commandParts[1],"",register);
            else if(commandParts[0].equals("set")) index +=set.execute(commandParts[1],commandParts[2],register);
            else if(commandParts[0].equals("add")) index +=add.execute(commandParts[1],commandParts[2],register);
            else if(commandParts[0].equals("mul")) index +=mul.execute(commandParts[1],commandParts[2],register);
            else if(commandParts[0].equals("mod")) index +=mod.execute(commandParts[1],commandParts[2],register);
            else if(commandParts[0].equals("rcv"))
                if(messagesIn.size() == 0) locked = true;
                else index +=rcv.execute(commandParts[1],"",register);
            else if(commandParts[0].equals("jgz")) index +=jgz.execute(commandParts[1],commandParts[2],register);
            else throw new Exception("Unexpected command found: "+commandParts[0]);
         //   debug(command);
        }

        private boolean hasMoreInstructions(){
            if(locked && messagesIn.size() > 0) locked = false;
            return index >=0 && index < commands.size() && !locked;
        }

        private long convertVar(String input,Map<String,Long> register){
            if(numberPattern.matcher(input).matches()) return Long.parseLong(input);
            else if(register.keySet().contains(input)) return register.get(input);
            else{
                logger.debug("New register: "+input);
                register.put(input,0L);
                return 0;
            }
        }

/*        private void debug(String command) {
            StringBuilder b = new StringBuilder();
            b.append(name + " ");
            b.append(command);
            b.append(" \t");
            register.keySet().stream().forEach(s -> b.append(s + ":" + register.get(s) + ","));
            logger.debug(b.toString());
        }*/
    }

    private interface Command{
        int execute(String a,String b,Map<String,Long> variables);
    }
}

