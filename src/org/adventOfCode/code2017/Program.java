package org.adventOfCode.code2017;

import java.util.*;
import java.util.regex.Pattern;

public class Program{
    private static Pattern numberPattern = Pattern.compile("-?(\\d)*");


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
    Command sub = (a,b,v) -> {v.put(a,convertVar(a,v)-convertVar(b,v)); return 1;};
    Command mul = (a,b,v) -> {v.put(a,convertVar(a,v)*convertVar(b,v));return 1;};
    Command mod = (a,b,v) -> {v.put(a,convertVar(a,v)%convertVar(b,v));return 1;};
    Command rcv = (a,b,v) -> {v.put(a,messagesIn.poll()) ; return 1;};
    Command jgz = (a,b,v) -> {return (convertVar(a,v) > 0L)? (int)convertVar(b,v): 1;};
    Command jnz = (a,b,v) -> {return (convertVar(a,v) != 0L)? (int)convertVar(b,v): 1;};

    public Program(Queue<Long> messagesIn,Queue<Long> messagesOut,List<String> commands){
        this.commands = commands;
        this.messagesIn = messagesIn;
        this.messagesOut = messagesOut;
    }

    public void setRegister(String reg,long value){
        register.put(reg,value);
    }

    public void executeCommand() throws Exception{
        String command = commands.get(index);
        String[] commandParts = command.split(" ");
        if(commandParts[0].equals("snd")) index +=snd.execute(commandParts[1],"",register);
        else if(commandParts[0].equals("set")) index +=set.execute(commandParts[1],commandParts[2],register);
        else if(commandParts[0].equals("add")) index +=add.execute(commandParts[1],commandParts[2],register);
        else if(commandParts[0].equals("sub")) index +=sub.execute(commandParts[1],commandParts[2],register);
        else if(commandParts[0].equals("mul")) index +=mul.execute(commandParts[1],commandParts[2],register);
        else if(commandParts[0].equals("mod")) index +=mod.execute(commandParts[1],commandParts[2],register);
        else if(commandParts[0].equals("rcv"))
            if(messagesIn.size() == 0) locked = true;
            else index +=rcv.execute(commandParts[1],"",register);
        else if(commandParts[0].equals("jgz")) index +=jgz.execute(commandParts[1],commandParts[2],register);
        else if(commandParts[0].equals("jnz")) index +=jnz.execute(commandParts[1],commandParts[2],register);
        else throw new Exception("Unexpected command found: "+commandParts[0]);
        //   debug(command);
    }

    public boolean hasMoreInstructions(){
        if(locked && messagesIn.size() > 0) locked = false;
        return index >=0 && index < commands.size() && !locked;
    }

    public String getNextCommand(){
        return commands.get(index);
    }

    private long convertVar(String input,Map<String,Long> register){
        if(numberPattern.matcher(input).matches()) return Long.parseLong(input);
        else if(register.keySet().contains(input)) return register.get(input);
        else{
            register.put(input,0L);
            return 0;
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getRegister(String name){
        if(!register.containsKey(name)) return 0L;
        else return register.get(name);
    }

    public String debug(String command) {
            StringBuilder b = new StringBuilder();
            b.append(name + " ");
            b.append(command);
            b.append(" \t");
            register.keySet().stream().forEach(s -> b.append(s + ":" + register.get(s) + ","));
            return b.toString();
        }

    private interface Command{
        int execute(String a,String b,Map<String,Long> variables);
    }
}