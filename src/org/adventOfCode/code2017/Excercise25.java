package org.adventOfCode.code2017;

import oracle.jrockit.jfr.StringConstantPool;
import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Excercise25 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise25.txt");

        int result = getChecksum(input);
        logger.debug("Result of excercise 25A: "+result);

     /*   result = getLongestBridgeStrength(input);
        logger.debug("Result of excercise 25B: "+result);*/

    }

    public int getChecksum(List<String> input) throws Exception{
        int result = 0;

        List<TuringState> turingStates = new ArrayList<>();
        Tape tape = new Tape();
        loadInput(input,turingStates,tape);

        for(int i = 0; i < tape.maxStepCount; i++){
            tape.process();
        }
        for(int i = 0; i < tape.tapeState.length; i++){
            if(tape.tapeState[i] == 1) result++;
        }

        return result;
    }

    private void loadInput(List<String> input,List<TuringState> turingStates,Tape tape ) throws Exception{
        Pattern p = Pattern.compile("Begin in state (.).");
        Matcher m = p.matcher(input.get(0));
        m.find();
        String startState = m.group(1);
        p = Pattern.compile("Perform a diagnostic checksum after (\\d*) steps.");
        m = p.matcher(input.get(1));
        m.find();
        int stepCount = Integer.parseInt(m.group(1));
        tape.maxStepCount = stepCount;

        for(int i = 0; i < (input.size()-2)/10;i++){
            readTuringState(input.subList(2+i*10,2+(i+1)*10),turingStates);
        }

        turingStates.stream().forEach( ts -> {if(ts.stateName.equals(startState)) tape.currentState = ts;});
    }

    private void readTuringState(List<String> input,List<TuringState> turingStates) throws Exception{
        TuringState t = new TuringState();
        String name = input.get(1).substring(input.get(1).length()-2,input.get(1).length()-1);
        for(TuringState tt : turingStates){
            if(tt.stateName.equals(name)){ //if state allready in list complete data
                t = tt;
                break;
            }
        }

        if(t.stateName == null){
            t.stateName = name;
            turingStates.add(t);
        }

        Pattern p = Pattern.compile("\\s*- Write the value (\\d*).");
        Matcher m = p.matcher(input.get(3));
        m.find();
        t.valueOnWrite.put(0,Integer.parseInt(m.group(1)));
        m = p.matcher(input.get(7));
        m.find();
        t.valueOnWrite.put(1,Integer.parseInt(m.group(1)));

        p = Pattern.compile("\\s*- Move one slot to the ([a-z]+).");
        m = p.matcher(input.get(4));
        m.find();
        String dir = m.group(1);
        if(dir.equals("left")) t.moveDirection.put(0,Direction.L);
        else if(dir.equals("right")) t.moveDirection.put(0,Direction.R);
        else throw new Exception("Unexpected direction found: "+dir);
        m = p.matcher(input.get(8));
        m.find();
        dir = m.group(1);
        if(dir.equals("left")) t.moveDirection.put(1,Direction.L);
        else if(dir.equals("right")) t.moveDirection.put(1,Direction.R);
        else throw new Exception("Unexpected direction found: "+dir);

        TuringState tempState = new TuringState();
        tempState.stateName = input.get(5).substring(input.get(5).length()-2,input.get(5).length()-1);
        for(TuringState tt : turingStates){ // overwrite with existing state if the case
            if(tt.stateName.equals(tempState.stateName)){
                t.nextState.put(0,tt);
            }
        }
        if(t.nextState.get(0) == null){
            t.nextState.put(0,tempState);
            turingStates.add(tempState);
        }

        tempState = new TuringState();
        tempState.stateName = input.get(9).substring(input.get(9).length()-2,input.get(9).length()-1);
        for(TuringState tt : turingStates){ // overwrite with existing state if the case
            if(tt.stateName.equals(tempState.stateName)){
                t.nextState.put(1,tt);
            }
        }
        if(t.nextState.get(1) == null){
            t.nextState.put(1,tempState);
            turingStates.add(tempState);
        }
    }

    enum Direction {L,R};

    private class TuringState{
        String stateName;
        Map<Integer,Integer> valueOnWrite = new HashMap<Integer,Integer>();
        Map<Integer,Direction> moveDirection = new HashMap<Integer,Direction>();
        Map<Integer,TuringState> nextState = new HashMap<Integer,TuringState>();
    }

    private class Tape{
        TuringState currentState;
        private final int MAXVALUE = 1000000;
        int[] tapeState = new int[MAXVALUE];
        int position = MAXVALUE/2;
        int maxStepCount = 0;

        private void moveLeft(){
            position--;
        }
        private void moveRight(){
            position++;
        }

        private int getState(){
            return tapeState[position];
        }

        private void setState(int state){
            tapeState[position] = state;
        }

        private void process(){
            int currentValue = getState();
            setState(currentState.valueOnWrite.get(currentValue));
            if(currentState.moveDirection.get(currentValue) == Direction.L)  moveLeft();
            else moveRight();
            currentState = currentState.nextState.get(currentValue);
        }
    }
}

